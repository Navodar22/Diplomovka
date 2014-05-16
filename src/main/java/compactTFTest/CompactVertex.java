package compactTFTest;

public class CompactVertex {
	
	private String name;
	private Integer id;
	private boolean isTopVertex = false;
	private Integer LPOVertexId = 0;
	
	
	
	public Integer getLPOVertexId() {
		return LPOVertexId;
	}
	public void setLPOid(Integer lPOVertexid) {
		LPOVertexId = lPOVertexid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public boolean isTopVertex() {
		return isTopVertex;
	}
	public void setTopVertex(boolean isTopVertex) {
		this.isTopVertex = isTopVertex;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ID: " + id + ", LPOId: " + LPOVertexId + ", Name: " + name + ", isTop " + isTopVertex + " \n";
	}
	
}
