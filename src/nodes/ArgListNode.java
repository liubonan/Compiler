package nodes;

public class ArgListNode extends Node{

	public ArgListNode(){
		
	}
	
	public ArgListNode(Node leftChild, Node rightChild){
		this.setType("argList1");
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);		
	}
	
	public ArgListNode(Node leftChild){
		this.setValue(null);
		this.setType("argList2");
		this.setLeftChild(leftChild);
	}

}

