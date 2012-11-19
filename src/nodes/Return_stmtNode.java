package nodes;

public class Return_stmtNode extends Node {
	public Return_stmtNode(){};
	public Return_stmtNode(Node leftChild){
		this.setType("return");
		this.setLeftChild(leftChild);
	}
}
