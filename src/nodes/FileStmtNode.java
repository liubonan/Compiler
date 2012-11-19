package nodes;

public class FileStmtNode extends Node {

	public FileStmtNode(){
		
	}
	public FileStmtNode(String type, Node expr) {
 		this.setType(type);
 		this.setLeftChild(expr);
	}
	
	public FileStmtNode(Node leftChild){
		this.setType(leftChild.getType());
		this.setLeftChild(leftChild);

	}
	
}
