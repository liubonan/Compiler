package nodes;

public class FunDeclarationNode extends Node{
	
	private String identifier = null;
	private Node returnTypeNode = null;
	
	public FunDeclarationNode(Node leftChild, Node rightChild, String identifier ){
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setIdentifier(identifier);
		this.setType("void_func_declare");
	}
	
	public FunDeclarationNode(Node type, Node leftChild, Node rightChild, String identifier){
		this.setLeftChild(leftChild);
		this.setRightChild(rightChild);
		this.setIdentifier(identifier);
		this.setType("func_declare");
		this.setReturnTypeNode(type);
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Node getReturnTypeNode() {
		return returnTypeNode;
	}

	public void setReturnTypeNode(Node returnTypeNode) {
		this.returnTypeNode = returnTypeNode;
	}

}
