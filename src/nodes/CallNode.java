package nodes;

public class CallNode extends Node{
	protected String identifier;
	public CallNode(){
		
	}
	
	public CallNode(String identifier, Node leftChild){
		
		this.setIdentifier(identifier);
		this.setLeftChild(leftChild);
		this.setType("call");
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

}
