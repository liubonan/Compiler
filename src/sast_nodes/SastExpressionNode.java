package sast_nodes;

import java.util.LinkedList;
import java.util.List;

public class SastExpressionNode extends SastNode {
	
	private List<String> expr= null;
	private String exprType = null;
	
	public SastExpressionNode(){
		this.type="expr";
	}
	public List<String> getExpr() {
		return expr;
	}
	public void setExpr(List<String> expr) {
		this.expr = expr;
	}
	public String getExprType() {
		return exprType;
	}
	public void setExprType(String exprType) {
		this.exprType = exprType;
	}

}
