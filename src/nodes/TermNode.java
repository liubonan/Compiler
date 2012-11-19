package nodes;

public class TermNode extends Node{
	private Node midChild = null;
	public TermNode(){
		
	}
	
	public TermNode(Node leftChild){
		this.setType("term1");
		this.setLeftChild(leftChild);
	}
	public TermNode(Node leftChild, Node midChild, Node rightChild){
		this.setType("term2");
		this.setLeftChild(leftChild);
		this.setMidChild(midChild);
		this.setRightChild(rightChild);
	}

	public Node getMidChild() {
		return midChild;
	}

	public void setMidChild(Node midChild) {
		this.midChild = midChild;
	}
}
