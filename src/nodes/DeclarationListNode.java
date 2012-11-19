package nodes;

public class DeclarationListNode extends Node{
	public DeclarationListNode( Node left, Node right){
		this.setLeftChild(left);
		this.setRightChild(right);
		this.setType("dl_two");
	}
	
	public DeclarationListNode( Node left){
		this.setLeftChild(left); 
		this.setType("dl_one");
	}
	

}
