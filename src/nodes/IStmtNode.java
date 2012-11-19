package nodes;

public class IStmtNode extends Node{

	public IStmtNode(){
		
	}
	
	public IStmtNode(Node identifier1, Node identifier2){
		this.setType("input");

		this.setLeftChild(identifier1);
		this.setRightChild(identifier2);
	}


	
}
