package nodes;

public class ParamNode extends Node{
	
	private String identifier = null;
	private String small = null;
	
	public ParamNode (Node leftChild, String identifier)
	{
		this.setLeftChild(leftChild);
		this.setIdentifier(identifier);
	}
	
	public ParamNode (Node leftChild, String identifier, String small)
	{
		this.setLeftChild(leftChild);
		this.setIdentifier(identifier);
		this.setSmall(small);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

}
