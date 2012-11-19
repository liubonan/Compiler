package nodes;

public class For_stmtNode extends Node{
	private Node var;
	private Node condition;
	private Node change;
	public For_stmtNode(){}
	public For_stmtNode(Node var, Node condition, Node change, Node leftChild){
		this.setType("for");
		this.setVar(var);
		this.setCondition(condition);
		this.setChange(change);
		this.setLeftChild(leftChild);
	}
	public Node getVar() {
		return var;
	}
	public void setVar(Node var) {
		this.var = var;
	}
	public Node getCondition() {
		return condition;
	}
	public void setCondition(Node condition) {
		this.condition = condition;
	}
	public Node getChange() {
		return change;
	}
	public void setChange(Node change) {
		this.change = change;
	}
}
