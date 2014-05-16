/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package analyz;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author navodar
 */
public class PtfsLPO {
    private List<PtfsPlaceName> ptfsmiesta;
    private TreeSet<PtfsVertex> ptfsvrcholi;
    private HashSet<PtfsEdge> ptfshrany;
    
    private Set<PtfsVertex> selectVertexes  = new HashSet<PtfsVertex>();
    private Set<PtfsVertex> mouseoverVertexes = new HashSet<PtfsVertex>();
    
    private Set<PtfsEdge> selectEdges  = new HashSet<PtfsEdge>();
    private Set<PtfsEdge> mouseoverEdges = new HashSet<PtfsEdge>();
    
    
    public List<PtfsVertex> getStartVertexes(){		
		List<PtfsVertex> startVertexes = new ArrayList<PtfsVertex>();		
		
		for( PtfsVertex vertex : ptfsvrcholi ){
			if( isStartVertex(vertex) ){
				startVertexes.add(vertex);
			}				
		}
				
		return startVertexes;		
	}
	
    boolean finded = false;
    
    public boolean pathExist(PtfsVertex source, PtfsVertex destination){
		List<PtfsEdge> connectEdges = getVertexConnectEdges(source);
    	
		for( PtfsEdge edge : connectEdges ){
			if( finded ){
				break;
			}
			
			if( edge.getSourceId() == source.getId()  ){
//				System.out.println(edge);
				if( edge.getDestinationId() == destination.getId() ){
					finded = true;
					break;
				}else{
					pathExist(edge.getDestination(), destination );
				}
			}			
		}
		
		if( finded ){
			return true;
		}
		return false;
	}
    
    private List<PtfsEdge> getVertexConnectEdges( PtfsVertex vertex ){
    	List<PtfsEdge> connectEdges = new ArrayList<PtfsEdge>();
    	for( PtfsEdge edge : ptfshrany ){
    		if( vertex.getId() == edge.getSourceId() ){
    			connectEdges.add(edge);
    		}
    	}
    	
    	return connectEdges;
    }
    
	public boolean isStartVertex(PtfsVertex vertex){
		for( PtfsEdge edge : ptfshrany ){
			if( edge.getDestinationId() == vertex.getId() ){
				return false;
			}			
		}		
		return true;
	}

    public void addVertex(PtfsVertex vertex){
    	int vertex_id = getMaxIdFromVertexes();
    	vertex.setId(++vertex_id);
    	ptfsvrcholi.add(vertex);    	
    }
    
    public void addVertexWithId(PtfsVertex vertex){
    	ptfsvrcholi.add(vertex); 
    }
    
    public void addEdge(PtfsEdge edge){
    	ptfshrany.add(edge);    	
    } 
        
    public void moveElements(Point relativePosition) {    	
    	for (PtfsVertex vertex : selectVertexes) {
    		// Pripocitaj relativne suradnice k existujucim
			vertex.moveBy(relativePosition);
		}
	}
    
    public void deleteElements(){
    	deleteVertexes();
    	deleteEdges();
    }
    
    public void renameVertexes(String name) {
    	for( PtfsVertex vertex : selectVertexes ){
    		vertex.setName(name);
    	}
		// TODO Auto-generated method stub
		
	}
    
    public void deleteVertexes(){
    	Set<PtfsEdge> edgesToDelete = new HashSet<PtfsEdge>();
    	
    	for( PtfsVertex vertex : selectVertexes ){
    		for(PtfsEdge edge : ptfshrany ){
    			if( edge.getSource() == vertex || edge.getDestination() == vertex ){
    				edgesToDelete.add(edge);
    			}    			
    		}
    	}
    	
    	deleteEdges(edgesToDelete);
    	
    	TreeSet<PtfsVertex> nove = new TreeSet<PtfsVertex>();
    	
    	for( PtfsVertex vertex : ptfsvrcholi ){
    		if( !selectVertexes.contains(vertex) ){
    			nove.add(vertex);
    		}
    	}
    	
    	ptfsvrcholi = nove;
    }
    
    public void deleteEdges(){
    	for(PtfsEdge edge : selectEdges){
    		deleteEdge ( edge );
    	}    	
    }
    
    public void deleteEdges(Set<PtfsEdge> edges){
    	for(PtfsEdge edge : edges){
    		deleteEdge ( edge );
    	}    	
    }
    
    public void deleteEdge( PtfsEdge edge ){
    	ptfshrany.remove(edge);    	
    }


    public PtfsLPO(TreeSet<PtfsVertex> ptfsvertexes, HashSet<PtfsEdge> ptfsedges) {
        this.ptfsvrcholi = ptfsvertexes;
        this.ptfshrany = ptfsedges;        
    }

    
    
    public boolean isFromSelectedVertexes( PtfsVertex vertex){
    	if( selectVertexes.contains(vertex)  ){
    		return true;
    	}    	
    	return false; 
    }
    
    
    private int getMaxIdFromVertexes(){
    	int max_value = 0;
    	for(PtfsVertex vertex : ptfsvrcholi ){
    		if( vertex.getId() > max_value ){
    			max_value = vertex.getId();
    		}    		
    	}
    	
    	return max_value;    	
    }
    
    public PtfsLPO createDeepCopy(){
    	TreeSet<PtfsVertex> copyVertexes = createCopyVertexes( ptfsvrcholi );
    	
    	HashSet<PtfsEdge> copyEdges = createCopyEdges(ptfshrany, copyVertexes);
    	PtfsLPO copyLpo = new PtfsLPO(copyVertexes, copyEdges);
    	
    	return copyLpo;
    }
    
    
    public TreeSet<PtfsVertex> createCopyVertexes(TreeSet<PtfsVertex> vertexes ){
    	TreeSet<PtfsVertex> copyVertexes = new TreeSet<PtfsVertex>();
    	for(PtfsVertex vertex : vertexes ){
    		copyVertexes.add( cloneVertex( vertex ) );
    	}
    	
    	return copyVertexes;
    }
    
    public HashSet<PtfsEdge> createCopyEdges(HashSet<PtfsEdge> edges, TreeSet<PtfsVertex> copyVertexes ){
    	HashSet<PtfsEdge> copyEdges = new HashSet<PtfsEdge>();
    	for(PtfsEdge edge : edges ){
    		int id_source = edge.getSourceId();
    		int id_destination = edge.getDestinationId();
    		PtfsVertex source = getPtfsVertexById(id_source, copyVertexes);
    		PtfsVertex destination = getPtfsVertexById(id_destination, copyVertexes);
    		PtfsEdge copy_edge = new PtfsEdge(source, destination, 0, null);
    		copyEdges.add(copy_edge);
    	}    	
    	return copyEdges;
    }
    
    private PtfsVertex getPtfsVertexById(int id, TreeSet<PtfsVertex> ptfsvertexes){
    	for( PtfsVertex vertex :  ptfsvertexes ){
    		if( vertex.getId() == id ){
    			return vertex;    			
    		}    		
    	}
    	
    	return null;
    }
    
    // selectVertexes
    
	public Set<PtfsVertex> getSelectVertexes() {
		return selectVertexes;
	}

	public void setSelectVertexes(Set<PtfsVertex> vertexes_select) {
		this.selectVertexes = vertexes_select;
	}
	
	public void addToSelectedVertexes(PtfsVertex vertex){
    	selectVertexes.add(vertex);    
    }
    
    public void clearSelectedVertexes(){
    	selectVertexes.clear();    	
    }

    // mouseOver Vertexes
    
	
	public Set<PtfsVertex> getMouseoverVertexes() {
		return mouseoverVertexes;
	}

	public void setMouseoverVertexes(TreeSet<PtfsVertex> vertexes_mouseover) {
		this.mouseoverVertexes = vertexes_mouseover;
	}  

	public void addToMouseOverVertexes(PtfsVertex vertex){
    	mouseoverVertexes.add(vertex);    	
    }
        
    public void clearMouseOverVertexes(){
    	mouseoverVertexes.clear();    	
    }
    
    // select Edges
	
	public Set<PtfsEdge> getSelectEdges() {
		return selectEdges;
	}

	public void setSelectEdges(Set<PtfsEdge> selectEdges) {
		this.selectEdges = selectEdges;
	}
	
	public void addToSelectedEdges(PtfsEdge edge){
    	selectEdges.add(edge);    
    }
    
    public void clearSelectedEdges(){
    	selectEdges.clear();    	
    }
    
    // mouseOver Edges

	public Set<PtfsEdge> getMouseoverEdges() {
		return mouseoverEdges;
	}

	public void setMouseoverEdges(Set<PtfsEdge> mouseoverEdges) {
		this.mouseoverEdges = mouseoverEdges;
	}

	public void addToMouseOverEdges(PtfsEdge edge){
    	mouseoverEdges.add(edge);    	
    }
        
    public void clearMouseOverEdges(){
    	mouseoverEdges.clear();    	
    }
	 
    
    // main Getters and Setters
 
	public HashSet<PtfsEdge> getPtfshrany() {
        return ptfshrany;
    }

    public void setPtfshrany(HashSet<PtfsEdge> ptfshrany) {
        this.ptfshrany = ptfshrany;
    }   
	
    public List<PtfsPlaceName> getPtfsmiesta() {
        return ptfsmiesta;
    }

    public void setPtfsmiesta(List<PtfsPlaceName> ptfsmiesta) {
        this.ptfsmiesta = ptfsmiesta;
    }

    public TreeSet<PtfsVertex> getPtfsvrcholi() {
        return ptfsvrcholi;
    }

    public void setPtfsvrcholi(TreeSet<PtfsVertex> ptfsvrcholi) {
        this.ptfsvrcholi = ptfsvrcholi;
    }
    
    private PtfsVertex cloneVertex( PtfsVertex vertex ){
    	
    	PtfsVertex clone = new PtfsVertex(vertex.getId(), vertex.getCenterX(), vertex.getCenterY(), 
    			vertex.getName(), vertex.getVertexWidth(), vertex.getIsinput(), vertex.getIsoutput() );
    	
    	return clone;    	
    }
    
    @Override
    public String toString() {
        return "Vertexes: \n" + ptfsvrcholi + ",\n Edges: \n" + ptfshrany + " \n";
    }

	public void move(Point relativePosition) {
		for( PtfsVertex vertex : getPtfsvrcholi() ){
			vertex.moveBy(relativePosition);
		}
		// TODO Auto-generated method stub
		
	}

	

	

	

    

}
