/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import analyz.PflowArc;
import analyz.PflowPetriNet;
import analyz.PflowPlace;
import analyz.PflowTransition;
import design.MainFrame;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author navodar
 */
public final class Loadpflow {

            
    public Loadpflow() {
    }

    public Hashtable<File, PflowPetriNet> getPflowPnetsFromFiles(MainFrame mainFrame, List<File> files) throws ParserConfigurationException, SAXException, IOException {

        if (!files.isEmpty()) {
            Hashtable<File, PflowPetriNet> pnets = new Hashtable<File, PflowPetriNet>();
            Loadpflow pflow_loader = new Loadpflow();
            for (File file : files) {
                if (FileOperations.getExtension(file).equalsIgnoreCase("pflow") && !mainFrame.isFileInLoadedPNets(file) ) {

                    PflowPetriNet pflow_pnet = pflow_loader.getPflowPnetFromFile(file);
                    if( pflow_pnet != null  ){
                        pnets.put(file, pflow_pnet);
                    }
                }
            }
            return pnets;
        }
        return null;
    }
    
    
    public PflowPetriNet getPflowPnetFromFile(File file) throws ParserConfigurationException, SAXException, IOException {
        List<PflowPlace> pflowPlaces = new ArrayList<PflowPlace>();
        List<PflowArc> pflowArcs = new ArrayList<PflowArc>();
        List<PflowTransition> pflowTransition = new ArrayList<PflowTransition>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);
        doc.getDocumentElement().normalize();


        pflowPlaces.addAll(loadpflowplaces(doc));
        pflowArcs.addAll(loadpflowarcs(doc));
        pflowTransition.addAll(loadpflowtransitions(doc));
        
        
        PflowPetriNet petrinet = new PflowPetriNet(pflowPlaces, pflowTransition, pflowArcs);
        
        return petrinet;        
    }
    

    private List<PflowPlace> loadpflowplaces(Document doc) {
        String[] placeAttributes = {"id", "x", "y", "label", "tokens", "isStatic"};
        String[][] elements = loadelements("place", doc, placeAttributes);
        List<PflowPlace> pflowPlaces = new ArrayList<PflowPlace>();

        for (int i = 0; i < elements.length; i++) {
        	
            // vytvorenie PflowPlace a jeho pridanie do kolekcie...
            // poradie pridavaneho id, x, y, label, tokens, isStatic
            pflowPlaces.add(new PflowPlace(
                    Integer.parseInt(elements[i][0]),
                    Integer.parseInt(elements[i][1]),
                    Integer.parseInt(elements[i][2]),
                    elements[i][3],
                    Integer.parseInt(elements[i][4]),
                    Boolean.parseBoolean(elements[i][5])
                    ));
        }
//        System.out.println(pflowPlaces);
        return pflowPlaces;
    }

    private List<PflowTransition> loadpflowtransitions(Document doc) {
        List<PflowTransition> pflowTransition = new ArrayList<PflowTransition>();

        String[] placeatributes = {"id", "x", "y", "label"};
        String[][] elements = loadelements("transition", doc, placeatributes);

        for (int i = 0; i < elements.length; i++) {
//          id, x, y, label
            pflowTransition.add(new PflowTransition(
                    Integer.parseInt(elements[i][0]),
                    Integer.parseInt(elements[i][1]),
                    Integer.parseInt(elements[i][2]),
                    elements[i][3]
                    ));
        }
        return pflowTransition;
    }

    private List<PflowArc> loadpflowarcs(Document doc) {
        List<PflowArc> pflowArcs = new ArrayList<PflowArc>();

        String[] placeatributes = {"sourceId", "destinationId", "multiplicity"};
        String[][] elements = loadelements("arc", doc, placeatributes);

        for (int i = 0; i < elements.length; i++) {
//          id, x, y, label
            pflowArcs.add(new PflowArc(
                    Integer.parseInt(elements[i][0]),
                    Integer.parseInt(elements[i][1]),
                    Integer.parseInt(elements[i][2])
                    ));
        }
        return pflowArcs;
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
        if( fstNm.item(0) != null ){
        	atribut = ((Node) fstNm.item(0)).getNodeValue();
        }else{
        	atribut = "";
        }
        return atribut;
    }

    
}
