package nodes;

public class VarDeclarationNode extends Node {
	private String identifier = null;
	public VarDeclarationNode(Node leftChild, String identifier, Node rightChild){
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setIdentifier(identifier);
		this.setType("var_declaration");
		
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
