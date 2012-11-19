package nodes;

public class ParamListNode extends Node {
	
	public ParamListNode(Node leftChild, Node rightChild)
	{
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setType("param_list");
	}
	
	public ParamListNode(Node leftChild)
	{
		this.setLeftChild(leftChild);
	}

}
