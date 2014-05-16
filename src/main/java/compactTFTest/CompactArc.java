package compactTFTest;

public class CompactArc implements Comparable<CompactArc> {
	
	private CompactVertex source;
	private CompactVertex destination;
	private Integer capacity;
	private Integer flow;
	
	public boolean marked = false;
	
	@Override
	public int compareTo(CompactArc arc) {
		CompactVertex destinationArc = arc.getDestination();
		
		
		// TODO Auto-generated method stub
//		if( destination.getId() == 2 && destinationArc.getId() == 2 ){
//			return 0;			
//		}
		

		if( destination.getId() == 2 && destinationArc.getId() != 2 ){
			return -1;			
		}
		
		if( destination.getId() != 2 && destinationArc.getId() == 2 ){
			return 1;			
		}
		
		
		return 1;
	}
	
	public Integer getPossibleFlow(){
		return capacity - flow;
	}
	
	public CompactVertex getSource() {
		return source;
	}
	public void setSource(CompactVertex source) {
		this.source = source;
	}
	public CompactVertex getDestination() {
		return destination;
	}
	public void setDestination(CompactVertex destination) {
		this.destination = destination;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public Integer getFlow() {
		return flow;
	}
	public void setFlow(Integer flow) {
		this.flow = flow;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Source: " + source.getName() + ",  " + source.getId() +
				"; destination: " + destination.getName() + ",  " + destination.getId() +
				"; capacity: " + capacity + ", flow: " + flow + " \n";
	}
	
	

}
