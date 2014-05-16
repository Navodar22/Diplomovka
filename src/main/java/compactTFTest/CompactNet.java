package compactTFTest;



import analyz.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CompactNet {
	
	private List<CompactVertex> vertexes = new ArrayList<CompactVertex>();
	private Set<CompactArc> arcs = new TreeSet<CompactArc>();
	
	
	private Integer startVertexId = 1;	
	private Integer sourceVertexId = 0;	
	private Integer sinkVertexId = Integer.MAX_VALUE;	
	private Integer minVerIndex = 2;
	
	private Integer possibleMaxFlow = 0;
	
	private CompactVertex startVertex = null;
	private CompactVertex sourceVertex = null;
	private CompactVertex sinkVertex = null;
	
	public CompactNet(){
		
	}
	
	public CompactNet( PtfsLPO lpo, PflowPetriNet pNet, PflowPlace petriPlace ){
		createCompactVertexes(lpo);		
		
//		System.out.println(vertexes);
		
		createCompactArcs( lpo, pNet, petriPlace );		
	}
	
	public Set<CompactArc> getVertexConnectEdges( CompactVertex vertex ){
    	Set<CompactArc> connectEdges = new TreeSet<CompactArc>();
    	for( CompactArc arc : arcs ){
    		if( vertex.getId() == arc.getSource().getId() 
    				&& !arc.marked 
    				&& ( arc.getCapacity() - arc.getFlow() > 0 ) )
    		{
    			connectEdges.add(arc);
    		}
    	}
    	
    	return connectEdges;
    }
	
	private void createCompactArcs(PtfsLPO lpo, PflowPetriNet pNet, PflowPlace petriPlace) {
		// TODO Auto-generated method stub
		for( PtfsEdge edge : lpo.getPtfshrany() ){
			createCompactArc(edge);			
		}
		
		addInnerVertexArcs();
		addSourceArcs(pNet, petriPlace);
		addDestinationArcs(pNet, petriPlace);	
		addStartVertexArc(petriPlace);
		
//		System.out.println(arcs);
	}

	private void addStartVertexArc(PflowPlace petriPlace){
		CompactArc arc = new CompactArc();
		arc.setCapacity(petriPlace.getTokens());
		arc.setFlow(0);
		arc.setSource(sourceVertex);
		arc.setDestination(startVertex);			
		arcs.add(arc);		
	}
	

	private void addDestinationArcs(PflowPetriNet pNet, PflowPlace petriPlace) {
		// TODO Auto-generated method stub
		for( CompactVertex vertex : vertexes ){
			if( vertex.isTopVertex() && vertex.getId() >= minVerIndex ){
				CompactArc arc = new CompactArc();
				
				Integer capacity = pNet.getOutputTransitionWeight(petriPlace, vertex.getName() );
				
				arc.setSource(vertex);
				arc.setDestination(sinkVertex);
				arc.setFlow(0);
				arc.setCapacity(capacity);				
				arcs.add(arc);
				
				possibleMaxFlow += capacity;
			}
		}		
	}


	private void addSourceArcs(PflowPetriNet pNet, PflowPlace petriPlace) {
		// TODO Auto-generated method stub
		
//		CompactArc startArc = new CompactArc();
//		startArc.setSource(sourceVertex);
//		startArc.setDestination(startVertex);
//		startArc.setFlow(0);
//		startArc.setCapacity(20);
		
		
		for( CompactVertex vertex : vertexes ){
			if(  !vertex.isTopVertex() && vertex.getId() >= minVerIndex 
					&& vertex.getId() != sinkVertexId ){
				CompactArc arc = new CompactArc();
								
				Integer capacity = pNet.getInputTransitionWeight(petriPlace, vertex.getName() );
				
				arc.setSource(sourceVertex);
				arc.setDestination(vertex);
				arc.setFlow(0);
				arc.setCapacity(capacity);				
				arcs.add(arc);				
			}
		}			
	}


	private void createCompactArc(PtfsEdge edge) {
		CompactVertex source = getSourceVertex(edge.getSource() );
		CompactVertex destination = getDestinationVertex(edge.getDestination());
		
		CompactArc arc = new CompactArc();
		arc.setSource(source);
		arc.setDestination(destination);
		arc.setFlow(0);
		arc.setCapacity(Integer.MAX_VALUE);		
		arcs.add(arc);
		
		
		
//		System.out.println(edge.getSource().getName() + ", " + edge.getDestination().getName());
		
		// TODO Auto-generated method stub
		
	}
	
	private void addInnerVertexArcs(){
		CompactVertex topVertex =  null;
		
		for( CompactVertex vertex : vertexes ){
			if( vertex.getId() >= minVerIndex && topVertex != null && !vertex.isTopVertex()
					&& vertex.getId() != sinkVertexId ){
				CompactArc arc = new CompactArc();
				arc.setSource(topVertex);
				arc.setDestination(vertex);
				arc.setFlow(0);
				arc.setCapacity(Integer.MAX_VALUE);		
				arcs.add(arc);				
			}			
			topVertex = vertex;
		}		
	}

	private CompactVertex getSourceVertex( PtfsVertex ptfsVertex ){
		for( CompactVertex vertex : vertexes ){
			if( ( ptfsVertex.getId() ==  vertex.getLPOVertexId() ) && !vertex.isTopVertex() ){
				return vertex;
			}			
		}
		
		return null;
	}
	
	private CompactVertex getDestinationVertex( PtfsVertex ptfsVertex ){
		for( CompactVertex vertex : vertexes ){
			if( ( ptfsVertex.getId() ==  vertex.getLPOVertexId() ) && vertex.isTopVertex() ){
				return vertex;
			}			
		}
		
		return null;
	}

	private void createCompactVertexes(PtfsLPO lpo){
		int index = minVerIndex;
		
		for( PtfsVertex lpoVertex : lpo.getPtfsvrcholi() ){
			if( lpoVertex.getId() > 0 ){				
				CompactVertex topVertex = new CompactVertex();
				topVertex.setTopVertex(true);
				topVertex.setId(index);
				topVertex.setName(lpoVertex.getName());
				topVertex.setLPOid(lpoVertex.getId());
				vertexes.add(topVertex);
				index++;
				
				CompactVertex bottomVertex = new CompactVertex();
				bottomVertex.setTopVertex(false);
				bottomVertex.setId(index);
				bottomVertex.setName(lpoVertex.getName());
				bottomVertex.setLPOid(lpoVertex.getId());
				vertexes.add(bottomVertex);
				index++;
			}			
		}
		
		
		startVertex = new CompactVertex();
		startVertex.setId(startVertexId);
		startVertex.setName("v0");
		startVertex.setTopVertex(false);
		vertexes.add(startVertex);
		
		sourceVertex = new CompactVertex();
		sourceVertex.setId(sourceVertexId);
		sourceVertex.setName("source");
		sourceVertex.setTopVertex(false);
		vertexes.add(sourceVertex);
				
		sinkVertexId = vertexes.size();
		
		sinkVertex = new CompactVertex();
		sinkVertex.setId(sinkVertexId);
		sinkVertex.setName("sink");
		sinkVertex.setTopVertex(false);
		vertexes.add(sinkVertex);		
		
//		System.out.println(vertexes);
	}


	public List<CompactVertex> getVertexes() {
		return vertexes;
	}


	public void setVertexes(List<CompactVertex> vertexes) {
		this.vertexes = vertexes;
	}


	public Set<CompactArc> getArcs() {
		return arcs;
	}


	public void setArcs(Set<CompactArc> arcs) {
		this.arcs = arcs;
	}

	public CompactVertex getSourceVertex() {
		return sourceVertex;
	}

	public void setSourceVertex(CompactVertex sourceVertex) {
		this.sourceVertex = sourceVertex;
	}

	public CompactVertex getSinkVertex() {
		return sinkVertex;
	}

	public void setSinkVertex(CompactVertex sinkVertex) {
		this.sinkVertex = sinkVertex;
	}

	public Integer getPossibleMaxFlow() {
		return possibleMaxFlow;
	}

	public void setPossibleMaxFlow(Integer possibleMaxFlow) {
		this.possibleMaxFlow = possibleMaxFlow;
	}	

	
	
}
