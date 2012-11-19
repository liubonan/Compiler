package nodes;

public class VarNode extends Node{
	private String identifier;
      public VarNode(){
    	  
      }
      
      public VarNode(String identifier){
    	 this.setIdentifier(identifier);
    	 this.setType("var");
      }
      
      public VarNode(String identifier, Node leftChild){
    	  this.setIdentifier(identifier);
    	  this.setLeftChild(leftChild);
    	  this.setType("var_array");    	  
      }

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
}
