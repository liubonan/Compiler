package sast_nodes;

public class SastReturnStatementNode  extends SastNode {
	
	private SastExpressionNode expr=null;
	
	public SastReturnStatementNode(){
		this.type = "return_stmt";
	}

	public SastExpressionNode getExpr() {
		return expr;
	}

	public void setExpr(SastExpressionNode expr) {
		this.expr = expr;
	}

}
