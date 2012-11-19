package nodes;

public class OStmtNode extends Node{
	private ExpressionNode expr1 = null;
	private ExpressionNode expr2 = null;
	private ExpressionNode expr3 = null;
	public OStmtNode(){
		
	}
	
	public OStmtNode(Node expr){
		this.setType("ostmt1");
		this.setExpr1(expr);
	}
	public OStmtNode(Node expr1, Node expr2, int type){
		if(type == 1){
			this.setType("ostmt2");
			this.setExpr1(expr1);
			this.setExpr2(expr2);	
		}
		else if(type == 2){
			this.setType("ostmt3");
			this.setExpr1(expr1);
			this.setExpr2(expr2);
		}
	}
	public OStmtNode(Node expr1, Node expr2, Node expr3){
		this.setType("ostmt4");
		this.setValue(value);
		this.setExpr1(expr1);
		this.setExpr2(expr2);
		this.setExpr3(expr3);
	}

	public ExpressionNode getExpr1() {
		return expr1;
	}

	public void setExpr1(Node expr1) {
		this.expr1 = (ExpressionNode)expr1;
	}

	public ExpressionNode getExpr2() {
		return expr2;
	}

	public void setExpr2(Node expr2) {
		this.expr2 = (ExpressionNode)expr2;
	}

	public ExpressionNode getExpr3() {
		return expr3;
	}

	public void setExpr3(Node expr3) {
		this.expr3 = (ExpressionNode)expr3;
	}


}
