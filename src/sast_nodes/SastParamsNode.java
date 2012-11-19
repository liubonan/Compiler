package sast_nodes;

public class SastParamsNode extends SastNode{
	private String paramType  = null;
	private String identifier = null;
	private int arrayTail 	  = -1;
	
	public SastParamsNode(){
		this.type = "params";
	}
	
	public String getParamType() {
		return paramType;
	}
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getArrayTail() {
		return arrayTail;
	}
	public void setArrayTail(int arrayTail) {
		this.arrayTail = arrayTail;
	}
	

}
