package sast_nodes;

public class SastFileStatementNode extends SastNode{
	
	private SastExpressionNode expr1 = null;
	private SastExpressionNode expr2 = null;
	private SastExpressionNode expr3 = null;
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
	public SastExpressionNode getExpr3() {
		return expr3;
	}
	public void setExpr3(SastExpressionNode expr3) {
		this.expr3 = expr3;
	}
	
	

}
