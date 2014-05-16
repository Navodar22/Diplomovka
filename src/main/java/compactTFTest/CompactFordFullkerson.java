package compactTFTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class CompactFordFullkerson {
	
	public CompactFordFullkerson(){}

	private CompactNet compactNet;
	boolean finded = false;
	
	
	private List<CompactArc> path = new ArrayList<CompactArc>();
	
	private Stack< List<CompactArc> > paths = new Stack<List<CompactArc>>();
	
	public Integer computeMaxFlow(CompactNet compactNet) {		
		
		this.compactNet = compactNet;
		
//		System.out.println(compactNet.getArcs());
		

//		for( int i = 0; i < 50; i++){
			
			
		while( findPath( compactNet.getSourceVertex(), compactNet.getSinkVertex()  )  ){
			
			setPathFlow();
			
			System.out.println("Cesta \n" + path);
			
			finded = false;
			unmarkPath(compactNet);
			path = new ArrayList<CompactArc>();
			
		}
		
		System.out.println(paths.size());
		
//		for( int i = 0; i < paths.size(); i++ ){
//			System.out.println("Cesta \n" + paths.get(i));
//		}
		
		System.out.println(compactNet.getArcs());
//		System.out.println(paths);
		
		
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	private void setPathFlow(){
		Integer pathFlow = getMaxPathFlow();
		
		for( CompactArc arc : path ){
			arc.setFlow( arc.getFlow() + pathFlow  );
		}
		
	}
	
	private Integer getMaxPathFlow(){
		Integer pathFlow = Integer.MAX_VALUE;
		
		for( CompactArc arc : path ){
			if( arc.getPossibleFlow() < pathFlow ){
				pathFlow = arc.getPossibleFlow();
			}			
		}
		
		return pathFlow;
	}
	
	
	private void unmarkPath(CompactNet compactNet){
		for( CompactArc arc : compactNet.getArcs() ){
			arc.marked = false;
		}
	}
	
	private boolean findPath(CompactVertex source, CompactVertex destination ){
//		System.out.println("Source: " + source + "Destination: " + destination);
		
		Set<CompactArc> connectArc = compactNet.getVertexConnectEdges(source);
		
//		System.out.println(connectArc);
		
		for( CompactArc arc : connectArc ){
			if( finded ){
				break;
			}
			
			if( arc.getSource().getId() == source.getId() && !arc.marked){
//				System.out.println("Hrana je " +arc);
				path.add(arc);
				arc.marked = true;
				if( arc.getDestination().getId() == destination.getId() ){
//					System.out.println("najdene " + arc);
//					System.out.println(path);
					
					finded = true;
					paths.add(path);
					break;
				}else{					
					findPath( arc.getDestination(), destination );					
				}
			}			
		}
		
		if( finded ){
			return true;
		}
		return false;		
	}

}
