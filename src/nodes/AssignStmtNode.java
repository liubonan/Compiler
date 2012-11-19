package nodes;

public class AssignStmtNode extends Node{
	public AssignStmtNode(){
		
	}
	
	public AssignStmtNode(Node leftChild, Node rightChild){
		this.setValue(null);
		this.setType("AssignStmt");
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
	}
}
