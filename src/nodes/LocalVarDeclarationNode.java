package nodes;

public class LocalVarDeclarationNode extends Node {
	
	public LocalVarDeclarationNode()
	{
		
	}
	
	public LocalVarDeclarationNode(Node leftChild, Node rightChild)
	{
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);		
	}
	
	
}
