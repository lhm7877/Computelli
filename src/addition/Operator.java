package addition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class Operator {
	Stack<Object> inputStack = new Stack<>();
	Stack<Object> outputStack = new Stack<>();


	public Stack<Object> getInputStack() {
		return inputStack;
	}


	public void setInputStack(Stack<Object> inputStack) {
		this.inputStack = inputStack;
	}


	public Stack<Object> getOutputStack() {
		return outputStack;
	}


	public void setOutputStack(Stack<Object> outputStack) {
		this.outputStack = outputStack;
	}


	public Operator(ArrayList<Edge2> inEdge2, ArrayList<Edge2> outEdge2, Node2 this_Node2) {
		Iterator<Edge2> itrIn = inEdge2.iterator();
		Iterator<Edge2> itrOut = outEdge2.iterator();
		while (itrIn.hasNext()) {
			Edge2 edge = itrIn.next();
			if(!edge.from_Node2.getOutput().isEmpty())
			inputStack.push(edge.from_Node2.getOutput());
		}
		while (itrOut.hasNext()) {
			Edge2 edge = itrOut.next();
			if(!edge.to_Node2.outEdge2.isEmpty())
			outputStack.push(edge.to_Node2.getOutput());
		}
		
	}

//	public void setOutput(ArrayList<Object> inputAr, Node2 this_Node2) {
//		if (this_Node2.value.equals("typeOf")) {
//			//DB���� �� InArgType�� �޾ƿ´�.
//			//InArgType�� �°� typeOf�� �Ѱ��ش�.
//			//typeOf�� InArgType�� String 1��
//			TypeOf typeOf = new TypeOf();
//			typeOf.run((String)inputAr.get(0),this_Node2);
//		}
//		if (this_Node2.value.equals("propertyOf")) {
//
//		}
//	}
}
