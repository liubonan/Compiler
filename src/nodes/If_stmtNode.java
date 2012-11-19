package nodes;

public class If_stmtNode extends Node {
	private Node det=null;
	public If_stmtNode(){}
	public If_stmtNode(Node det, Node ifs){
		this.setType("if");
		this.setDet(det);
		this.setLeftChild(ifs);
	}
	public If_stmtNode(Node det, Node ifs, Node elses){
		this.setType("if_else");
		this.setDet(det);
		this.setLeftChild(ifs);
		this.setRightChild(elses);
	}
	public Node getDet() {
		return det;
	}
	public void setDet(Node det) {
		this.det = det;
	}
}
