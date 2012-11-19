package nodes;

public class ExpressionNode extends Node{
	
	private Node relop = null;
	
	public ExpressionNode(String type, Node leftChild, Node rightChild)
	{
		this.setType(type);
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
	}
	
	public ExpressionNode(String type, Node leftChild)
	{
		this.setType(type);
		this.setLeftChild(leftChild);
	}
	
	public ExpressionNode(Node leftChild, Node rightChild, Node relop)
	{
		this.setType("relop");
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setRelop(relop);
	}

	public Node getRelop() {
		return relop;
	}

	public void setRelop(Node relop) {
		this.relop = relop;
	}
	
	
}
