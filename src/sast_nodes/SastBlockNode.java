package sast_nodes;

import java.util.LinkedList;
import java.util.List;

public class SastBlockNode extends SastNode{
	
	private List<SastVarDeclarationNode> local_var_list	= new LinkedList<SastVarDeclarationNode>();	
	private List<SastNode>	 		 stmt_list		= new LinkedList<SastNode>();
	
	public SastBlockNode(){
		this.type="block";
	}
	public List<SastVarDeclarationNode> getLocal_var_list() {
		return local_var_list;
	}
	public void setLocal_var_list(List<SastVarDeclarationNode> local_var_list) {
		this.local_var_list = local_var_list;
	}
	public List<SastNode> getStmt_list() {
		return stmt_list;
	}
	public void setStmt_list(List<SastNode> stmt_list) {
		this.stmt_list = stmt_list;
	}
	
	

}
