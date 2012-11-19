package sast_nodes;

public class SastIfStatementNode extends SastNode{
	
	private SastExpressionNode expr=null;
	private SastNode ifstmt=null;
	private SastNode elsestmt=null;
	
	public SastIfStatementNode(){
		this.type="if_stmt";
	}
	
	public SastExpressionNode getExpr() {
		return expr;
	}
	public void setExpr(SastExpressionNode expr) {
		this.expr = expr;
	}
	public SastNode getIfstmt() {
		return ifstmt;
	}
	public void setIfstmt(SastNode ifstmt) {
		this.ifstmt = ifstmt;
	}
	public SastNode getElsestmt() {
		return elsestmt;
	}
	public void setElsestmt(SastNode elsestmt) {
		this.elsestmt = elsestmt;
	}
	
	

}
