package compactTFTest;


import analyz.*;
import dinic.Dinic;
import enums.MaxFlowAlgorithmChoices;

import java.util.ArrayList;
import java.util.List;


public class CompactTokenFlowTest {
	
	
	
	public CompactTokenFlowTest(){
		
	}
	
	private PtfsVertex source;
	private PtfsVertex destination;
	private List<PtfsEdge> marked = new ArrayList<PtfsEdge>();
	private List<PtfsEdge> selected = new ArrayList<PtfsEdge>();
	boolean finded = false;
	
	public PflowPlace test(PtfsLPO lpo, PflowPetriNet petriNet, MaxFlowAlgorithmChoices algorithm){
		
		PtfsLPO test_lpo = lpo.createDeepCopy();		
		addFirstVertex(test_lpo.getStartVertexes(), test_lpo);		
		removeTransitionEdges(test_lpo);
		
		switch(algorithm){
			case DINIC :
				for( PflowPlace petriPlace : petriNet.getPlaces() ){
//						System.out.println(petriPlace);
//					for(int i = 0; i < 10000; i++)
					if( !testDinicAlgByPetriPlace(petriPlace, petriNet, test_lpo) ){
						return petriPlace;
					}										
				} 				

				break;
				
			case PREFLOWPUSH :
				
				for( PflowPlace petriPlace : petriNet.getPlaces() ){
					if ( !testPreFlowPushByPetriPlace(petriPlace, petriNet, test_lpo) ){
						return petriPlace;							
					}								
				} 		
				
				break;
				
			default:
					System.out.println("Chybny algoritmus");
				break;
		}
		
		
		return null;
	}
	
	private boolean testPreFlowPushByPetriPlace( PflowPlace petriPlace, PflowPetriNet petriNet, PtfsLPO test_lpo  ){
		Integer maxFlowPFP = 0;
			
		CompactNet compactNet = new CompactNet(test_lpo, petriNet, petriPlace);				
		Integer nodes = compactNet.getVertexes().size();
		
		MatrixCreator matrixPFP = new MatrixCreator();				
		matrixPFP.createMatrixByCompactNet(compactNet);		
		
		PreFlowPush preFlowPush = new PreFlowPush(nodes);
		maxFlowPFP = preFlowPush.getMaxFlow( matrixPFP.getMatrixNet() );	
		
//		System.out.println("Possible: " + compactNet.getPossibleMaxFlow() );		
//		System.out.println("Max flow preFlow: " + maxFlowPFP);
		if(compactNet.getPossibleMaxFlow() > maxFlowPFP){
			return false;
		}
		
		return true;
	}

	private boolean testDinicAlgByPetriPlace( PflowPlace petriPlace, PflowPetriNet petriNet, PtfsLPO test_lpo  ){
		Integer maxFlowDinic = 0;
			
		CompactNet compactNet = new CompactNet(test_lpo, petriNet, petriPlace);				
		Integer nodes = compactNet.getVertexes().size();
		
		MatrixCreator matrixDinic = new MatrixCreator();	
		matrixDinic.createMatrixToDinic(compactNet);		
		
		Dinic dinicAlg = new Dinic();
		maxFlowDinic = dinicAlg.getMaxFlow(nodes, matrixDinic.getMatrixNet());
		
//		System.out.println("Possible: " + compactNet.getPossibleMaxFlow() );
//		System.out.println("Max flow Dinic: " + maxFlowDinic);
		if(compactNet.getPossibleMaxFlow() > maxFlowDinic){
			return false;
		}
		
		return true;
	}
	
	
	private void removeTransitionEdges(PtfsLPO lpo){
		for( PtfsEdge edge : lpo.getPtfshrany() ){
			finded = false;
			source = edge.getSource();
			destination = edge.getDestination();
			marked.add(edge);
//			System.out.println("Source je " + source.getId() + " destination " + destination.getId());
			if ( find(lpo, source) ){
//				System.out.println("Tranzitivna hrana je " + edge);
				selected.add(edge);
//				System.out.println("akdsjfklasjk");
			}
			marked.clear();
		}
		
		lpo.getPtfshrany().removeAll(selected);
		
		selected.clear();
	}
	
	private void addFirstVertex(List<PtfsVertex> startVertexes, PtfsLPO lpo){
		PtfsVertex firstVertex = new PtfsVertex(0, (double) 0, (double) 0, "v0", (double) 100, true, false);
		
		lpo.addVertexWithId(firstVertex);
		
		for( PtfsVertex vertex : startVertexes ){
			PtfsEdge edge = new PtfsEdge();
			edge.setSource(firstVertex);
			edge.setDestination(vertex);			
			lpo.addEdge(edge);			
		}	
	}
	
	
	private boolean find(PtfsLPO lpo, PtfsVertex source){
		
		for( PtfsEdge edge : lpo.getPtfshrany() ){
			if( finded ){
				break;
			}
			
			if( edge.getSourceId() == source.getId() && !marked.contains(edge) && !selected.contains(edge) ){
//				System.out.println(edge);
				if( edge.getDestinationId() == destination.getId() ){
					finded = true;
					break;
				}else{
					find(lpo, edge.getDestination() );
				}
			}			
		}
		
		if( finded ){
			return true;
		}
		return false;
	}
	
	
}
