package nodes;

public class BlockNode extends Node{
	
	public BlockNode(Node leftChild, Node rightChild)
	{
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setType("Block");	
	}

}
