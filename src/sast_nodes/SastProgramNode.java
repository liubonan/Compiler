package sast_nodes;

import java.util.LinkedList;
import java.util.List;

public class SastProgramNode extends SastNode {
	
	protected List<SastNode> declaration_list  = new LinkedList<SastNode>();
	
	public SastProgramNode(){
		this.type="program";
	}

	public List<SastNode> getDeclaration_list() {
		return declaration_list;
	}

	public void setDeclaration_list(List<SastNode> declaration_list) {
		this.declaration_list = declaration_list;
	}
	
	
	

}
