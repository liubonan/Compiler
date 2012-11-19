package nodes;

public class StatementNode extends Node{
	public StatementNode(){}
	public StatementNode(String type,Node leftChild){
		this.setValue(null);
		this.setType(type);
		this.setLeftChild(leftChild);
		this.setRightChild(null);
	}
	
	
}
