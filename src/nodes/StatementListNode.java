package nodes;

public class StatementListNode extends Node{
	
	public StatementListNode(){
	
		
	}
	public StatementListNode(Node leftChild, Node rightChild)
	{
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
	}

}
