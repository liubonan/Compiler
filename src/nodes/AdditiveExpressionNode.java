package nodes;

public class AdditiveExpressionNode extends Node{
	protected Node midChild = null;
	private boolean negative = false;
	public AdditiveExpressionNode(){
		
	}
	
	public AdditiveExpressionNode(Node leftChild, Node midChild, Node rightChild){
		this.setLeftChild(leftChild);
		this.setMidChild(midChild);
		this.setRightChild(rightChild);
		this.setType("AdditiveExpression");
	}
	public AdditiveExpressionNode(String type, Node leftChild){
		this.negative=true;
		this.setType(type);
		this.setLeftChild(leftChild);
	}
	public AdditiveExpressionNode(String type, String value){
		this.negative=true;
		this.setType(type);
		this.setValue(value);
	}
	public AdditiveExpressionNode(Node leftChild){
		this.setType("additive_term");
		this.setLeftChild(leftChild);
	}

	public Node getMidChild() {
		return midChild;
	}

	public void setMidChild(Node midChild) {
		this.midChild = midChild;
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

}
