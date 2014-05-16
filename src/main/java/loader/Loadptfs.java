/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import design.MainFrame;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import analyz.PtfsEdge;
import analyz.PtfsLPO;
import analyz.PtfsVertex;

/**
 *
 * @author navodar
 */
public class Loadptfs {

//    private PtfsLPO ptfslpo;

    public Hashtable<File, PtfsLPO > getLposFromFiles(MainFrame mainFrame, List<File> files) throws ParserConfigurationException, SAXException, IOException {
        if (!files.isEmpty()) {
            Hashtable<File, PtfsLPO> lpos = new Hashtable<File, PtfsLPO >();
            Loadptfs lpo_loader = new Loadptfs();
            for (File file : files) {
                if (FileOperations.getExtension(file).equalsIgnoreCase("ptfs") && !mainFrame.isFileInLoadedLpos(file) ) {

                    PtfsLPO ptfslpo = lpo_loader.getPtfsLPOFromFile(file);
                    if( ptfslpo != null ){
                        lpos.put(file, ptfslpo);
                    }
                }
            }
            return lpos;
        }
        return null;
    }
    
    public PtfsLPO getPtfsLPOFromFile(File file) throws ParserConfigurationException, SAXException, IOException {
        HashSet<PtfsEdge> ptfsedges = new HashSet<PtfsEdge>();
        TreeSet<PtfsVertex> ptfsvertexes = new TreeSet<PtfsVertex>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();

        
        ptfsvertexes.addAll(loadptfsvertexes(doc));        
        ptfsedges.addAll(loadptfsedges(doc, ptfsvertexes));


        PtfsLPO ptfslpo = new PtfsLPO(ptfsvertexes, ptfsedges);

        int index = 0;
        for (PtfsVertex vertex : ptfsvertexes) {
            vertex.setIndex(index);
            index++;
        }
        
        return ptfslpo;
    }

    private List<PtfsEdge> loadptfsedges(Document doc, TreeSet<PtfsVertex> ptfsvertexes) {
        String[] elemntatributes = {"SourceVertexId", "DesVertexId"};
        String[][] elements = loadelements("Edge", doc, elemntatributes);
        List<PtfsEdge> ptfsedges = new ArrayList<PtfsEdge>();

        for (int i = 0; i < elements.length; i++) {
            // vytvorenie PflowPlace a jeho pridanie do kolekcie...
            // poradie pridavaneho id, x, y, label, tokens, isStatic
            ptfsedges.add(new PtfsEdge(
            		getPtfsVertexById( Integer.parseInt(elements[i][0]), ptfsvertexes ),
            		getPtfsVertexById( Integer.parseInt(elements[i][1]), ptfsvertexes ),
                    0,
                    null));
        }
        return ptfsedges;
    }
    
    
    
    private PtfsVertex getPtfsVertexById(int id, TreeSet<PtfsVertex> ptfsvertexes){
    	for( PtfsVertex vertex :  ptfsvertexes ){
    		if( vertex.getId() == id ){
    			return vertex;    			
    		}    		
    	}
    	
    	return null;
    }

    private TreeSet<PtfsVertex> loadptfsvertexes(Document doc) {
        String[] elemntatributes = {"Id", "X", "Y", "Name", "Width", "IsInput", "IsOutput"};
        String[][] elements = loadelements("Vertex", doc, elemntatributes);
        TreeSet<PtfsVertex> ptfsvertexes = new TreeSet<PtfsVertex>();

        for (int i = 0; i < elements.length; i++) {
            ptfsvertexes.add(new PtfsVertex(
                    Integer.parseInt(elements[i][0]),
                    Double.parseDouble(elements[i][1]),
                    Double.parseDouble(elements[i][2]),
                    elements[i][3],
                    Double.parseDouble(elements[i][4]),
                    Boolean.parseBoolean(elements[i][5]),
                    Boolean.parseBoolean(elements[i][6])));
        }
        return ptfsvertexes;
    }

    private String[][] loadelements(String elementname, Document doc, String[] nameatributes) {
        NodeList element = doc.getElementsByTagName(elementname);
        String[][] elements = new String[element.getLength()][nameatributes.length];

        for (int s = 0; s < element.getLength(); s++) {
            Node fstNode = element.item(s);
            elements[s] = loadelemnt(fstNode, nameatributes);
//            System.out.println(loadelemnt(fstNode, nameatributes));
        }
        return elements;
    }

    private String[] loadelemnt(Node fstNode, String[] nameatributes) {
        String[] atributes = new String[nameatributes.length];
        if (fstNode.getNodeType() == Node.ELEMENT_NODE) {
            Element fstElmnt = (Element) fstNode;
            for (int i = 0; i < nameatributes.length; i++) {
                atributes[i] = loadatribut(fstElmnt, nameatributes[i]);
            }
        }
        return atributes;
    }

    private String loadatribut(Element fstElmnt, String atributname) {
        String atribut;
        NodeList node = fstElmnt.getElementsByTagName(atributname);
        Element nodeelement = (Element) node.item(0);
        NodeList fstNm = nodeelement.getChildNodes();

        if (fstNm.item(0) == null) {
            atribut = "";
        } else {
            atribut = ((Node) fstNm.item(0)).getNodeValue();
        }
//        atribut = ((Node) fstNm.item(0)).getNodeValue();
        return atribut;
    }

    private HashSet<PtfsEdge> addtransitiveedges(HashSet<PtfsEdge> ptfsedges) {
        int pred = ptfsedges.size();
        HashSet<PtfsEdge> transitiveedges = new HashSet<PtfsEdge>();
        for (PtfsEdge firstedge : ptfsedges) {
            for (PtfsEdge secondedge : ptfsedges) {
                if (firstedge.getDestinationId() == secondedge.getSourceId()) {
//                    PtfsEdge ptfshrana = new PtfsEdge(firstedge.getSourceId(), secondedge.getDestinationId(), 0, null);
//                    transitiveedges.add(ptfshrana);
                }
            }
        }
        ptfsedges.addAll(transitiveedges);

        if (ptfsedges.size() > pred) {
            addtransitiveedges(ptfsedges);
        }
        return ptfsedges;
    }
}
