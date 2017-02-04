package addition;


public class Edge2 {
	int id;
	public Node2 from_Node2;
	public Node2 edge_Node2;	
	public Node2 to_Node2;
	public String value;
	boolean visited = false;
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public double weight;

	public Edge2(int id, String value, double weight) {
		this.id = id;
		this.value = value;
		this.weight = weight;
		this.visited = false;
	}
}