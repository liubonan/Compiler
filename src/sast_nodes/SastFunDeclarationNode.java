package sast_nodes;

import java.util.LinkedList;
import java.util.List;

public class SastFunDeclarationNode extends SastNode{
	private String funType    = null ;
	private String identifier = null;
	private List<SastParamsNode> paramnode_list	= new LinkedList<SastParamsNode>();
	private SastBlockNode blockNode = new SastBlockNode();
	
	public SastFunDeclarationNode(){
		this.type="fun_dec";
	}
	public String getFunType() {
		return funType;
	}
	public void setFunType(String funType) {
		this.funType = funType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<SastParamsNode> getParamnode_list() {
		return paramnode_list;
	}
	public void setParamnode_list(List<SastParamsNode> paramnode_list) {
		this.paramnode_list = paramnode_list;
	}
	public SastBlockNode getBlockNode() {
		return blockNode;
	}
	public void setBlockNode(SastBlockNode blockNode) {
		this.blockNode = blockNode;
	}
	
	

}
