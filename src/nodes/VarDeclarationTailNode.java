package nodes;

public class VarDeclarationTailNode extends Node {
	private String index;
	
	public VarDeclarationTailNode(){
	this.setValue("SEMI");
	this.setType("regular_tail");
	}

	public VarDeclarationTailNode(String value){
		this.setValue("SEMI");
		this.setType("array_tail");
		this.setIndex(value); 
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
}
