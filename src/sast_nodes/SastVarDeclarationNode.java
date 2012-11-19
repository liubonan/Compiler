package sast_nodes;

public class SastVarDeclarationNode extends SastNode {
	
	private String varType    = null;
	private String identifier = null;
	private int arraytail     = -1;
	
	public SastVarDeclarationNode(){
		this.type="var_declare";
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getVarType() {
		return varType;
	}
	public void setVarType(String varType) {
		this.varType = varType;
	}
	public int getArraytail() {
		return arraytail;
	}
	public void setArraytail(int arraytail) {
		this.arraytail = arraytail;
	}

}
