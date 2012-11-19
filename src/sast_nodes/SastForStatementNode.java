package sast_nodes;

public class SastForStatementNode extends SastNode {
	
	private SastAssignStatementNode initAssgin=null;
	private SastExpressionNode terminalExpr=null;
	private SastAssignStatementNode actionAssign=null;
	private SastNode bodyStmt=null;
	
	public SastForStatementNode(){
		this.type="for_stmt";
	}
	public SastAssignStatementNode getInitAssgin() {
		return initAssgin;
	}
	public void setInitAssgin(SastAssignStatementNode initAssgin) {
		this.initAssgin = initAssgin;
	}
	public SastExpressionNode getTerminalExpr() {
		return terminalExpr;
	}
	public void setTerminalExpr(SastExpressionNode terminalExpr) {
		this.terminalExpr = terminalExpr;
	}
	public SastAssignStatementNode getActionAssign() {
		return actionAssign;
	}
	public void setActionAssign(SastAssignStatementNode actionAssign) {
		this.actionAssign = actionAssign;
	}
	public SastNode getBodyStmt() {
		return bodyStmt;
	}
	public void setBodyStmt(SastNode bodyStmt) {
		this.bodyStmt = bodyStmt;
	}
	
	

}
