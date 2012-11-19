package nodes;

public class FactorNode extends Node{
	public FactorNode(){
		
	}
	
	public FactorNode(String value, String type, Node leftChild, Node rightChild){
		this.setValue(null);
		this.setType(type);
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
	}
	//The situation of factor->T where T is already a terminal
	public FactorNode(String value,String type)
	{
		this.setValue(value);
		this.setType(type);
	}
	public FactorNode(Node leftChild, String type)
	{
		this.setType(type);
		this.setLeftChild(leftChild);		
	}
	
}
