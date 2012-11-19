package sast_nodes;

public class SastAssignStatementNode extends SastNode{
	
	private SastExpressionNode expr1 = null;
	private SastExpressionNode expr2 = null;
	public SastAssignStatementNode(){
		this.type="assign_stmt";
	}
	public SastExpressionNode getExpr1() {
		return expr1;
	}
	public void setExpr1(SastExpressionNode expr1) {
		this.expr1 = expr1;
	}
	public SastExpressionNode getExpr2() {
		return expr2;
	}
	public void setExpr2(SastExpressionNode expr2) {
		this.expr2 = expr2;
	}

}
