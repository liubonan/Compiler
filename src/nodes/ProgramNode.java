package nodes;

public class ProgramNode extends Node {
	
	public ProgramNode( Node left){
		this.setLeftChild(left);
		this.setType("program");
	}
}
