package addition;

import java.util.ArrayList;


public class Node2 {
	public int id;
	public String value;
	public ArrayList<Edge2> inEdge2; // inbound
	public ArrayList<Edge2> outEdge2; // outbound
	public Node2 parent;
	public boolean visited;
	public double hScore = 0;
	public double fScore = 0;
	private String output;
	private String input;
//	public String type;
	public boolean isOperator;
	public String inArgType;
	

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public Node2(int id, String value, double hScore, boolean isOperator) {
		this.id = id;
		this.value = value;
		this.hScore = hScore;
		inEdge2 = new ArrayList<Edge2>();
		outEdge2 = new ArrayList<Edge2>();
		this.visited = false;
		this.isOperator = isOperator;
	}
}