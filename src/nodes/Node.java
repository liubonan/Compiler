package nodes;

public class Node{
	
	protected String value = null; //java code string
	protected String type = null; //flag
	protected Node leftChild = null; // first use this child
	protected Node rightChild = null;// keep this node "null" when there is only one child
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Node getLeftChild() {
		return leftChild;
	}
	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}
	public Node getRightChild() {
		return rightChild;
	}
	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	
	

}
