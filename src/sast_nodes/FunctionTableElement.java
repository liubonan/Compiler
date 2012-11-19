package sast_nodes;

import java.util.List;

public class FunctionTableElement {
	
	private String identifier=null;
	private List<String> argsType=null;
	private List<Boolean> argsArray=null;
	private String returnType=null;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public List<String> getArgsType() {
		return argsType;
	}
	public void setArgsType(List<String> argsType) {
		this.argsType = argsType;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public List<Boolean> getArgsArray() {
		return argsArray;
	}
	public void setArgsArray(List<Boolean> argsArray) {
		this.argsArray = argsArray;
	}

}
