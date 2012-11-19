package sast_nodes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import nodes.*;

public class SemanticAST {
	// private enum e {ostmt1, ostmt2, ostmt3, ostmt4};
	private Node ASTRoot = null;
	private List<HashMap<String, SymbolTableElement>> symbolTable = null;
	private HashMap<String, FunctionTableElement> functionTable = null;

	private String currentReturnType = null;
	private boolean currentReturnFlag = false;
	
	private List<SastParamsNode> param_cache= null;

	public SemanticAST(Node ASTRoot) {
		this.ASTRoot = ASTRoot;
		symbolTable = new LinkedList<HashMap<String, SymbolTableElement>>();
		symbolTable.add(new HashMap<String, SymbolTableElement>());
		functionTable = new HashMap<String, FunctionTableElement>();
		FunctionTableElement mainfunc = functionTable.get("main");

		FunctionTableElement tobig = new FunctionTableElement();
		tobig.setIdentifier("toBig");
		List<String> tobig_param = new LinkedList<String>();
		tobig_param.add("SMALL");
		tobig.setArgsType(tobig_param);
		tobig.setReturnType("BIG");
		functionTable.put(tobig.getIdentifier(), tobig);
		
		FunctionTableElement smallRand = new FunctionTableElement();
		smallRand.setIdentifier("smallRand");
		smallRand.setArgsType(null);
		smallRand.setReturnType("SMALL");
		functionTable.put(smallRand.getIdentifier(), smallRand);
		
		FunctionTableElement bigRand = new FunctionTableElement();
		bigRand.setIdentifier("bigRand");
		bigRand.setArgsType(null);
		bigRand.setReturnType("BIG");
		functionTable.put(bigRand.getIdentifier(), bigRand);
		
		
		
		

	}

	public SymbolTableElement getSymbolTableElement(String id) {
		// get the var's type from symbol table
		for (int i = symbolTable.size() - 1; i >= 0; i--) {
			if (symbolTable.get(i).get(id) != null)
				return symbolTable.get(i).get(id);
		}
		return null;
	}
	public SymbolTableElement getCurrentSymbolTableElement(String id) {
		// get the var's type from symbol table
		int i = symbolTable.size() - 1;
			if (symbolTable.get(i).get(id) != null)
				return symbolTable.get(i).get(id);
		
		return null;
	}

	public boolean checkExistFromSymbTbl(String id) {
		// whether the var has been defined
		for (int i = symbolTable.size() - 1; i >= 0; i--) {
			if (symbolTable.get(i).get(id) != null)
				return true;
		}
		return false;
	}

	public boolean checkNotExistInCurrentScopeFromSymbTbl(String id) {
		// whether the var has been defined
		for (int i = symbolTable.size() - 1; i >= 0; i--) {
			if (symbolTable.get(i).get(id) != null)
				return true;
		}
		return false;
	}

	public boolean checktypeFromSymbTbl(String id, String type) {
		for (int i = symbolTable.size() - 1; i >= 0; i--) {
			if (symbolTable.get(i).get(id) != null)
				if (symbolTable.get(i).get(id).getType().equals(type)) {
					return true;
				} else {
					System.out
							.println("type not matches with hash table, var: "
									+ id + " , type: " + type);
					return false;
				}
		}
		System.out.println("Couldn't find this variable " + id
				+ " from hash table.");
		return false;
	}

	public SastProgramNode prog_convert() {

		List<Node> d_list = new LinkedList<Node>();
		Node currentNode = ASTRoot;

		while (true) {
			if (currentNode.getRightChild() != null) {
				d_list.add(currentNode.getRightChild());
				currentNode = currentNode.getLeftChild();
			} else {
				d_list.add(currentNode.getLeftChild());
				break;
			}

		}

		List<SastNode> result = new LinkedList<SastNode>();

		for (int i = d_list.size() - 1; i >= 0; i--) {
			if (d_list.get(i).getLeftChild().getType()
					.equals("var_declaration")) {
				result.add(varDeclaration_convert(d_list.get(i).getLeftChild()));
			} else if (d_list.get(i).getLeftChild().getType()
					.equals("func_declare")) {
				result.add(funDeclaration_convert(d_list.get(i).getLeftChild()));
			} else if (d_list.get(i).getLeftChild().getType()
					.equals("void_func_declare")) {
				result.add(voidfunDeclaration_convert(d_list.get(i)
						.getLeftChild()));
			} else {
				System.out.println("Error in traversing AST program node!!");
			}
		}

		SastProgramNode sastroot = new SastProgramNode();
		sastroot.setDeclaration_list(result);

		FunctionTableElement mainfunc = functionTable.get("main");
		if (mainfunc == null) {
			System.out.println("No main function found in this program.");
			System.exit(-1);
		} else if (!mainfunc.getReturnType().equals("VOID")) {
			System.out
					.println("The return type of main function should be void");
			System.exit(-1);
		}

		return sastroot;
	}

	public SastFunDeclarationNode funDeclaration_convert(Node n) {
		SastFunDeclarationNode funNode = new SastFunDeclarationNode();
		Node currentNode = n;
		String returnType = null;
		String funName = null;
		List<SastParamsNode> params_list = new LinkedList<SastParamsNode>();
		funName = ((FunDeclarationNode) currentNode).getIdentifier();
		if (((FunDeclarationNode) currentNode).getType().equals("func_declare")) {
			returnType = ((FunDeclarationNode) currentNode).getReturnTypeNode()
					.getValue();
			currentReturnType = returnType;
			currentReturnFlag = false;

		} else {
			System.out.println("Error reading function type at function: "
					+ ((FunDeclarationNode) currentNode).getIdentifier());
		}

		if (((FunDeclarationNode) currentNode).getReturnTypeNode() != null) {
			funNode.setFunType(((FunDeclarationNode) currentNode)
					.getReturnTypeNode().getValue());
		}

		funNode.setIdentifier(((FunDeclarationNode) currentNode)
				.getIdentifier());

		// params is the left child, recursively traversing the params tree
		if (currentNode.getLeftChild() == null) {
			funNode.setParamnode_list(null);
		} else {
			currentNode = currentNode.getLeftChild();// currentNode is a params
														// node
			// params node only has left child
			currentNode = currentNode.getLeftChild();// currentNode is a
														// param_list node
			while (true) {
				if (currentNode.getRightChild() != null) {
					params_list
							.add(params_convert(currentNode.getRightChild()));
					currentNode = currentNode.getLeftChild();
				} else {
					params_list.add(params_convert(currentNode.getLeftChild()));
					break;
				}
			}

		}

		List<SastParamsNode> sastParamsList = new LinkedList<SastParamsNode>();
		for (int i = params_list.size() - 1; i >= 0; --i) {
			sastParamsList.add(params_list.get(i));
		}
		funNode.setParamnode_list(sastParamsList);

		FunctionTableElement fte = new FunctionTableElement();

		SymbolTableElement ste = new SymbolTableElement();

		if (params_list.size() > 0) {
			List<String> argsType = new LinkedList<String>();
			List<Boolean> argsArray = new LinkedList<Boolean>();
			param_cache = params_list;
			for (int i = 0; i <= sastParamsList.size() - 1; i++) {
//				ste.setIdentifier(params_list.get(i).getIdentifier());
//				ste.setType(params_list.get(i).getParamType());

				argsType.add(sastParamsList.get(i).getParamType());
				if (params_list.get(i).getArrayTail() == -1) {
					argsArray.add(i, false);
//					ste.setArray(false);
				} else {
					argsArray.add(i, true);
//					ste.setArray(true);
				}
//				// System.out.println("the id puting into symbol table is: " +
//				// params_list.get(i).getIdentifier() +
//				// ". type is: "+params_list.get(i).getParamType());
////				symbolTable.get(symbolTable.size() - 1).put(
////						params_list.get(i).getIdentifier(), ste);
//
			}
			fte.setArgsType(argsType);
			
			fte.setArgsArray(argsArray);
		} else {
			fte.setArgsType(null);
			fte.setArgsArray(null);
		}

		fte.setIdentifier(funName);
		fte.setReturnType(returnType);
		
		functionTable.put(funName, fte);
		
		// block is the right child
		currentNode = n;
		if (currentNode.getRightChild() == null) {
			System.out.println("right child is null");
		}
		funNode.setBlockNode(block_convert(currentNode.getRightChild()));
		
		if (!currentReturnFlag) {
			System.out.println("No return statement found for "+funName);
			System.exit(-1);
		}		
		
		return funNode;
	}

	public SastFunDeclarationNode voidfunDeclaration_convert(Node n) {
		SastFunDeclarationNode funNode = new SastFunDeclarationNode();
		Node currentNode = n;
		String returnType = null;
		String funName = null;
		List<SastParamsNode> params_list = new LinkedList<SastParamsNode>();

		funName = ((FunDeclarationNode) currentNode).getIdentifier();
		if (((FunDeclarationNode) currentNode).getType().equals(
				"void_func_declare")) {
			returnType = "VOID";
			currentReturnType = returnType;
			currentReturnFlag = true;

		} else {
			System.out.println("Error reading function type at function: "
					+ ((FunDeclarationNode) currentNode).getIdentifier());
		}

		if (((FunDeclarationNode) currentNode).getReturnTypeNode() != null) {
			funNode.setFunType("VOID");
		}

		funNode.setIdentifier(((FunDeclarationNode) currentNode)
				.getIdentifier());

		// params is the left child, recursively traversing the params tree
		if (currentNode.getLeftChild() == null) {
			funNode.setParamnode_list(null);
		} else {
			currentNode = currentNode.getLeftChild();// currentNode is a params
														// node
			// params node only has left child
			currentNode = currentNode.getLeftChild();// currentNode is a
														// param_list node
			while (true) {
				if (currentNode.getRightChild() != null) {
					params_list
							.add(params_convert(currentNode.getRightChild()));
					currentNode = currentNode.getLeftChild();
				} else {
					params_list.add(params_convert(currentNode.getLeftChild()));
					break;
				}
			}

		}

		List<SastParamsNode> sastParamsList = new LinkedList<SastParamsNode>();
		for (int i = params_list.size() - 1; i >= 0; --i) {
			sastParamsList.add(params_list.get(i));
		}
		funNode.setParamnode_list(sastParamsList);

		FunctionTableElement fte = new FunctionTableElement();

		SymbolTableElement ste = new SymbolTableElement();

		if (params_list.size() > 0) {
			List<String> argsType = new LinkedList<String>();
			List<Boolean> argsArray = new LinkedList<Boolean>();
			param_cache = params_list;
			for (int i = 0; i <= sastParamsList.size() - 1; i++) {
//				ste.setIdentifier(params_list.get(i).getIdentifier());
//				ste.setType(params_list.get(i).getParamType());

				argsType.add(sastParamsList.get(i).getParamType());
				if (params_list.get(i).getArrayTail() == -1) {
					argsArray.add(i, false);
//					ste.setArray(false);
				} else {
					argsArray.add(i, true);
//					ste.setArray(true);
				}
//				// System.out.println("the id puting into symbol table is: " +
//				// params_list.get(i).getIdentifier() +
//				// ". type is: "+params_list.get(i).getParamType());
////				symbolTable.get(symbolTable.size() - 1).put(
////						params_list.get(i).getIdentifier(), ste);
//
			}
			fte.setArgsType(argsType);
			
			fte.setArgsArray(argsArray);
		} else {
			fte.setArgsType(null);
			fte.setArgsArray(null);
		}

		fte.setIdentifier(funName);
		fte.setReturnType(returnType);
		
		functionTable.put(funName, fte);
		currentNode = n;
		// block is the right child
		funNode.setBlockNode(block_convert(currentNode.getRightChild()));
		
			
		
		return funNode;
	}

	public SastVarDeclarationNode varDeclaration_convert(Node n) {
		SastVarDeclarationNode varDeclNode = new SastVarDeclarationNode();
		Node currentNode = n;
		String id = ((VarDeclarationNode) currentNode).getIdentifier();
		varDeclNode.setIdentifier(id);

		// type is the left child
		String type = currentNode.getLeftChild().getValue();
		varDeclNode.setVarType(type);
		Integer tail = -1;

		// var_declaration_tail is the right child
		currentNode = currentNode.getRightChild();
		if (currentNode.getType().equals("regular_tail")) {
			;// do nothing
		} else if (currentNode.getType().equals("array_tail")) {
			tail = Integer.parseInt(((VarDeclarationTailNode) currentNode)
					.getIndex());
			varDeclNode.setArraytail(tail);
		}

//		SymbolTableElement ste = getSymbolTableElement(id);
		SymbolTableElement ste = getCurrentSymbolTableElement(id);

		if (ste == null) {
			ste = new SymbolTableElement();
			ste.setIdentifier(id);
			ste.setType(type);

			if (tail != -1)
				ste.setArray(true);
			else
				ste.setArray(false);

			symbolTable.get(symbolTable.size() - 1).put(id, ste);
		} else {
			System.out.println(id + " has been defined!");
			System.exit(-1);
		}

		return varDeclNode;
	}

	public SastBlockNode block_convert(Node n) {
		SastBlockNode sastBlockNode = new SastBlockNode();
		List<SastVarDeclarationNode> local_var_list = new LinkedList<SastVarDeclarationNode>();
		List<SastNode> stmt_list = new LinkedList<SastNode>();
		Node currentVarNode = n.getLeftChild();
		Node currentStmtListNode = n.getRightChild();
		Node currentStmtNode = null;
		symbolTable.add(new HashMap<String, SymbolTableElement>());
		
		if (param_cache != null) {
			SymbolTableElement ste = new SymbolTableElement();
			List<String> argsType = new LinkedList<String>();
			List<Boolean> argsArray = new LinkedList<Boolean>();
			for (int i = 0; i < param_cache.size(); i++) {
				ste = new SymbolTableElement();
				ste.setIdentifier(param_cache.get(i).getIdentifier());
				ste.setType(param_cache.get(i).getParamType());

				argsType.add(i, param_cache.get(i).getParamType());
				
				if (param_cache.get(i).getArrayTail() == -1) {
					argsArray.add(i, false);
					ste.setArray(false);
				} else {
					argsArray.add(i, true);
					ste.setArray(true);
				}
				// System.out.println("the id puting into symbol table is: " +
				// params_list.get(i).getIdentifier() +
				// ". type is: "+params_list.get(i).getParamType());
				 symbolTable.get(symbolTable.size() - 1).put(
						 param_cache.get(i).getIdentifier(), ste);

			}
		}

		param_cache = null;
		
		
		

		// local_var_declaration is the left child
		if (currentVarNode != null) {
			while (true) {
				if (currentVarNode.getLeftChild() == null) {
					local_var_list.add(varDeclaration_convert(currentVarNode
							.getRightChild()));
					break;
				} else {
					// var_declaration is the right child
					local_var_list.add(varDeclaration_convert(currentVarNode
							.getRightChild()));
					currentVarNode = currentVarNode.getLeftChild();
				}
			}
		}

		while (true) {
			if (currentStmtListNode != null) {

				if (currentStmtListNode.getLeftChild() == null) {
					currentStmtNode = currentStmtListNode.getRightChild();
					if (currentStmtNode.getType().equals("assign_stmt")) {
						// System.out.println("block assign_stmt");
						stmt_list.add(assignStmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("if_stmt")) {
						// System.out.println("block if stmt");
						stmt_list.add(ifstmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("for_stmt")) {
						// System.out.println("block for stmt");
						stmt_list.add(forstmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("return_stmt")) {
						// System.out.println("block return stmt");
						stmt_list.add(return_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("file_stmt")) {
						// System.out.println("block file stmt");
						stmt_list.add(filestmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("block")) {
						// System.out.println("block block");
						stmt_list.add(block_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("expression")) {
						// System.out.println("block expression");
						stmt_list.add(expr_convert(currentStmtNode
								.getLeftChild()));
					} else {
						System.out.println("error in block convert");
						System.exit(-1);
					}

					break;
				} else {

					currentStmtNode = currentStmtListNode.getRightChild();
					currentStmtListNode = currentStmtListNode.getLeftChild();

					if (currentStmtNode.getType().equals("assign_stmt")) {
						stmt_list.add(assignStmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("if_stmt")) {
						stmt_list.add(ifstmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("for_stmt")) {
						stmt_list.add(forstmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("return_stmt")) {
						stmt_list.add(return_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("file_stmt")) {
						stmt_list.add(filestmt_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("block")) {
						stmt_list.add(block_convert(currentStmtNode
								.getLeftChild()));
					} else if (currentStmtNode.getType().equals("expression")) {
						stmt_list.add(expr_convert(currentStmtNode
								.getLeftChild()));
					} else {
						System.out.println("error in block convert");
						System.exit(-1);
					}
				}
			}
			else{
				System.out.print("No statement found in block!");
				System.exit(-1);
			}
		}

		List<SastVarDeclarationNode> result_local_var_list = new LinkedList<SastVarDeclarationNode>();
		List<SastNode> result_stmt_list = new LinkedList<SastNode>();

		for (int i = local_var_list.size() - 1; i >= 0; i--) {
			result_local_var_list.add(local_var_list.get(i));
		}

		for (int i = stmt_list.size() - 1; i >= 0; i--) {
			result_stmt_list.add(stmt_list.get(i));
		}

		sastBlockNode.setLocal_var_list(result_local_var_list);
		sastBlockNode.setStmt_list(result_stmt_list);

		symbolTable.remove(symbolTable.size() - 1);

		return sastBlockNode;
	}

	public SastAssignStatementNode assignStmt_convert(Node n) {
		SastAssignStatementNode assignstmtNode = new SastAssignStatementNode();
		Node currentNode = n; // currentNode is a assignstmtNode
		assignstmtNode
				.setExpr1(varConvert((VarNode) currentNode.getLeftChild()));
		assignstmtNode.setExpr2(expr_convert(currentNode.getRightChild()));

		// type checking
		if (!assignstmtNode.getExpr1().getExprType()
				.equals(assignstmtNode.getExpr2().getExprType())) {
			System.out
					.println("Error at assignment statement convert. Expr1's type: "
							+ assignstmtNode.getExpr1().getExprType()
							+ " does not match Expr2's type: "
							+ "assignstmtNode.getExpr1().getExprType()");
			System.exit(-1);
		}

		String identifier = ((SastExpressionNode) assignstmtNode.getExpr1())
				.getExpr().get(0);
		boolean exist = checkExistFromSymbTbl(identifier);
		if (!exist) {
			System.out.println("Error, the variable: " + identifier
					+ " has not been declaried yet");
			System.exit(-1);

		}

		return assignstmtNode;

	}

	public SastReturnStatementNode return_convert(Node n) {
		SastReturnStatementNode returnStmtNode = new SastReturnStatementNode();

		if (n.getLeftChild() != null) {
			// returnStmtNode.setType(((Return_stmtNode)
			// currentNode).getLeftChild().getType());
			returnStmtNode.setExpr(expr_convert(n.getLeftChild()));
			
			if(!expr_convert(n.getLeftChild()).getExprType().equals(currentReturnType)){
				System.out.println("Return type should be "+currentReturnType);
				System.exit(-1);				
			}
			
			
			
			currentReturnFlag = true;
			return returnStmtNode;
		} else {
			// returnStmtNode.setType("VOID");
			// type checking
			if (!currentReturnType.equals("VOID")) {
				System.out
						.println("Type checking error at return stmt expected return type is not void");
				System.exit(-1);
			}
			
			
			
			
			currentReturnFlag = true;
			return returnStmtNode;
		}

	}

	public SastForStatementNode forstmt_convert(Node n) {

		SastForStatementNode result = new SastForStatementNode();
		result.setType("for");

		if (((For_stmtNode) n).getVar() != null)
			result.setInitAssgin(assignStmt_convert(((For_stmtNode) n).getVar()));
		else
			result.setInitAssgin(null);

		if (((For_stmtNode) n).getCondition() != null)
			result.setTerminalExpr(expr_convert(((For_stmtNode) n)
					.getCondition()));
		else
			result.setTerminalExpr(null);

		if (((For_stmtNode) n).getChange() != null)
			result.setActionAssign(assignStmt_convert(((For_stmtNode) n)
					.getChange()));
		else
			result.setTerminalExpr(null);

		if (((For_stmtNode) n).getLeftChild() == null) {
			result.setBodyStmt(null);
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("assign_stmt")) {
			result.setBodyStmt(assignStmt_convert(((For_stmtNode) n)
					.getLeftChild().getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("if_stmt")) {
			result.setBodyStmt(ifstmt_convert(((For_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("for_stmt")) {
			result.setBodyStmt(forstmt_convert(((For_stmtNode) n)
					.getLeftChild().getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("return_stmt")) {
			result.setBodyStmt(return_convert(((For_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("file_stmt")) {
			result.setBodyStmt(filestmt_convert(((For_stmtNode) n)
					.getLeftChild().getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType().equals("block")) {
			result.setBodyStmt(block_convert(((For_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((For_stmtNode) n).getLeftChild().getType()
				.equals("expression")) {
			result.setBodyStmt(expr_convert(((For_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else {
			System.out.println("error in for convert");
			System.exit(-1);
		}

		// type checking
		if (!result.getTerminalExpr().getExprType().equals("BOOL")) {
			System.out
					.println("Terminal expression type is not boolean at for loop");
			System.exit(-1);
		}

		return result;
	}

	public SastIfStatementNode ifstmt_convert(Node n) {

		SastIfStatementNode result = new SastIfStatementNode();

		result.setExpr(expr_convert(((If_stmtNode) n).getDet()));

		if (((If_stmtNode) n).getLeftChild() == null) {
			result.setIfstmt(null);
		} else if (((If_stmtNode) n).getLeftChild().getType()
				.equals("assign_stmt")) {
			result.setIfstmt(assignStmt_convert(((If_stmtNode) n)
					.getLeftChild().getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType().equals("if_stmt")) {
			result.setIfstmt(ifstmt_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType()
				.equals("for_stmt")) {
			result.setIfstmt(forstmt_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType()
				.equals("return_stmt")) {
			result.setIfstmt(return_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType()
				.equals("file_stmt")) {
			result.setIfstmt(filestmt_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType().equals("block")) {
			result.setIfstmt(block_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else if (((If_stmtNode) n).getLeftChild().getType()
				.equals("expression")) {
			result.setIfstmt(expr_convert(((If_stmtNode) n).getLeftChild()
					.getLeftChild()));
		} else {
			System.out.println("error in if convert");
			System.exit(-1);
		}

		if (((If_stmtNode) n).getType().equals("if")) {
			result.setType("if");
		} else if (((If_stmtNode) n).getType().equals("if_else")) {
			result.setType("if_else");

			if (((If_stmtNode) n).getRightChild() == null) {
				result.setIfstmt(null);
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("assign_stmt")) {
				result.setElsestmt(assignStmt_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("if_stmt")) {
				result.setElsestmt(ifstmt_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("for_stmt")) {
				result.setElsestmt(forstmt_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("return_stmt")) {
				result.setElsestmt(return_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("file_stmt")) {
				result.setElsestmt(filestmt_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("block")) {
				result.setElsestmt(block_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else if (((If_stmtNode) n).getRightChild().getType()
					.equals("expression")) {
				result.setElsestmt(expr_convert(((If_stmtNode) n)
						.getRightChild().getLeftChild()));
			} else {
				System.out.println("error in if convert");
				System.exit(-1);
			}

		} else {
			System.out.println("error in if convert");
			System.exit(-1);
		}

		// type checking
		if (!result.getExpr().getExprType().equals("BOOL")) {
			System.out
					.println("Terminal expression type is not boolean at for loop");
			System.exit(-1);
		}

		return result;
	}

	public SastFileStatementNode filestmt_convert(Node n) {
		SastFileStatementNode fileStmtNode = new SastFileStatementNode();

		if (n.getType().equals("INFILEOPEN")) {
			fileStmtNode.setType("file_INFILEOPEN");
			fileStmtNode.setExpr1(expr_convert(n.getLeftChild()));
			
			if(!expr_convert(n.getLeftChild()).getExprType().equals("FILE")){
				System.out.println("infileopen should followed by a FILE type!");
				System.exit(-1);
			}
			
			return fileStmtNode;
		} else if (n.getType().equals("INFILECLOSE")) {
			fileStmtNode.setType("file_INFILECLOSE");
			fileStmtNode.setExpr1(expr_convert(n.getLeftChild()));
			
			if(!expr_convert(n.getLeftChild()).getExprType().equals("FILE")){
				System.out.println("infileclose should followed by a FILE type!");
				System.exit(-1);
			}
			
			return fileStmtNode;
		} else if (n.getType().equals("OUTFILEOPEN")) {
			fileStmtNode.setType("file_OUTFILEOPEN");
			fileStmtNode.setExpr1(expr_convert(n.getLeftChild()));
			
			if(!expr_convert(n.getLeftChild()).getExprType().equals("FILE")){
				System.out.println("outfileopen should followed by a FILE type!");
				System.exit(-1);
			}
			
			return fileStmtNode;
		} else if (n.getType().equals("OUTFILECLOSE")) {
			fileStmtNode.setType("file_OUTFILECLOSE");
			fileStmtNode.setExpr1(expr_convert(n.getLeftChild()));
			
			if(!expr_convert(n.getLeftChild()).getExprType().equals("FILE")){
				System.out.println("outfileclose should followed by a FILE type!");
				System.exit(-1);
			}
			
			return fileStmtNode;
		} else if (n.getType().equals("input")) {
			fileStmtNode.setType("file_" + n.getType());
			fileStmtNode.setExpr1(expr_convert(n.getLeftChild().getLeftChild()));
			fileStmtNode.setExpr2(expr_convert(n.getLeftChild().getRightChild()));
			
			if(!expr_convert(n.getLeftChild().getLeftChild()).getExprType().equals("BIG")){
				System.out.println("input statement should followed by a big type and file type!");
				System.exit(-1);
			}
			
			if(!expr_convert(n.getLeftChild().getRightChild()).getExprType().equals("FILE")){
				System.out.println("input statement should followed by a big type and file type!");
				System.exit(-1);
			}		
			
			
			return fileStmtNode;
		} else if (n.getType().equals("ostmt1")) {
			fileStmtNode.setType("file_" + n.getType());
			fileStmtNode.setExpr1(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()));	
			
			
			return fileStmtNode;
		} else if (n.getType().equals("ostmt2")) {
			fileStmtNode.setType("file_" + n.getType());
			fileStmtNode.setExpr1(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()));
			fileStmtNode.setExpr2(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr2()));
			
			
			
			
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()).getExprType().equals("SMALL")){
				System.out.println("In output(s) statement to the screen, s should be small type!");
				System.exit(-1);
			}	
			
			
			
			
			return fileStmtNode;
		} else if (n.getType().equals("ostmt3")) {
			fileStmtNode.setType("file_" + n.getType());
			fileStmtNode.setExpr1(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()));
			fileStmtNode.setExpr2(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr2()));
			
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()).getExprType().equals("BIG")){
				System.out.println("You can only output BIG type to a file!");
				System.exit(-1);
			}	
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr2()).getExprType().equals("FILE")){
				System.out.println("You can only output big number to a FILE type!");
				System.exit(-1);
			}	
			
			
			
			return fileStmtNode;
		} else if (n.getType().equals("ostmt4")) {
			fileStmtNode.setType("file_" + n.getType());
			fileStmtNode.setExpr1(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()));
			fileStmtNode.setExpr2(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr2()));
			fileStmtNode.setExpr3(expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr3()));
			
			
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr1()).getExprType().equals("SMALL")){
				System.out.println("In output(s) statement to a file, s should be small type!");
				System.exit(-1);
			}	
			
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr2()).getExprType().equals("BIG")){
				System.out.println("You can only output BIG type to a file!");
				System.exit(-1);
			}	
			
			if(!expr_convert(((OStmtNode) n.getLeftChild())
					.getExpr3()).getExprType().equals("FILE")){
				System.out.println("You can only output big number to a FILE type!");
				System.exit(-1);
			}	
			
			
			
			
			return fileStmtNode;
		} else {
			System.out
					.println("Error: can not found expected file statement, return an empty SastFileStatementNode");
			System.exit(-1);
			return fileStmtNode;
		}

	}

	public SastParamsNode params_convert(Node n) {
		SastParamsNode paramsNode = new SastParamsNode();
		Node currentNode = n;
		paramsNode.setParamType(currentNode.getLeftChild().getValue());
		paramsNode.setIdentifier(((ParamNode) currentNode).getIdentifier());
		if (((ParamNode) currentNode).getSmall() != null) {
			paramsNode.setArrayTail(Integer.parseInt(((ParamNode) currentNode)
					.getSmall()));
		}

		return paramsNode;

	}

	public SastExpressionNode notConvert(ExpressionNode n) {
		SastExpressionNode child = expr_convert(n.getLeftChild());
		List<String> resultString = child.getExpr();

		if (!child.getExprType().equals("BOOL")) {
			System.out
					.println("'!' should be used before a bool constant or variable!");
			System.exit(-1);
		}

		resultString.add("NOT");
		SastExpressionNode result = new SastExpressionNode();
		result.setExpr(resultString);
		result.setExprType("BOOL");
		return result;
	}

	public SastExpressionNode andOrConvert(ExpressionNode n) {

		SastExpressionNode leftchild = expr_convert(n.getLeftChild());
		SastExpressionNode rightchild = expr_convert(n.getRightChild());

		if (!(leftchild.getExprType().equals("BOOL") && rightchild
				.getExprType().equals("BOOL"))) {
			System.out
					.println("'&&' or '||' should be used before a bool constant or variable!");
			System.exit(-1);
		}

		List<String> resultString = leftchild.getExpr();
		resultString.addAll(rightchild.getExpr());
		resultString.add(n.getType());

		SastExpressionNode result = new SastExpressionNode();
		result.setExpr(resultString);
		result.setExprType("BOOL");
		return result;
	}

	public SastExpressionNode expr_convert(Node n) {
		// System.out.println("expr convert start");
		if (n.getType().equals("NOT"))
			return notConvert((ExpressionNode) n);
		else if (n.getType().equals("OR") || n.getType().equals("AND"))
			return andOrConvert((ExpressionNode) n);
		else if (n.getType().equals("relop"))
			return relOpConvert((ExpressionNode) n);
		else if (n.getType().equals("AE"))
			return addConvert((AdditiveExpressionNode) n.getLeftChild());
		else {
			System.out.println("error in expr convert");
			System.exit(-1);
			return null;
		}
	}

	public SastExpressionNode relOpConvert(ExpressionNode n) {

		SastExpressionNode leftchild = expr_convert(n.getLeftChild());
		SastExpressionNode rightchild = expr_convert(n.getRightChild());

		if (!(leftchild.getExprType().equals("SMALL") && rightchild
				.getExprType().equals("SMALL"))
				&& !(leftchild.getExprType().equals("BIG") && rightchild
						.getExprType().equals("BIG"))
						&&!(leftchild.getExprType().equals("BOOL") && rightchild
								.getExprType().equals("BOOL"))) {
			System.out
					.println("Types between relation operators should be the same.");
			System.out
					.println("Types between relation operators should be the same.");
			System.out
					.println("the left child's & righchild's type is: "
							+ leftchild.getExprType() + "  "
							+ rightchild.getExprType());
			System.exit(-1);
		}

		List<String> resultString = leftchild.getExpr();
		resultString.addAll(rightchild.getExpr());

		if (!n.getRelop().getType().equals("relop")) {
			System.out.println("error in relop convert");
			System.exit(-1);
		}

		resultString.add(n.getRelop().getValue());

		SastExpressionNode result = new SastExpressionNode();
		result.setExpr(resultString);
		result.setExprType("BOOL");
		return result;

	}

	public SastExpressionNode mulopConvert(MulopNode n) {
		SastExpressionNode exprNode = new SastExpressionNode();
		List<String> expr = new LinkedList<String>();
		expr.add(n.getValue());
		exprNode.setExpr(expr);
		return exprNode;
	}

	public SastExpressionNode termConvert(Node n) {

		SastExpressionNode leftchild = new SastExpressionNode();
		SastExpressionNode middlechild = new SastExpressionNode();
		SastExpressionNode rightchild = new SastExpressionNode();
		SastExpressionNode result = new SastExpressionNode();
		String type = null;

		List<String> resultString = null;

		if (n.getType().equals("term1")) {
			leftchild = factorConvert((FactorNode) n.getLeftChild());
			resultString = leftchild.getExpr();
			result.setExpr(resultString);
			type = leftchild.getExprType();
		} else if (n.getType().equals("term2")) {

			leftchild = termConvert(n.getLeftChild());
			middlechild = mulopConvert((MulopNode) ((TermNode) n).getMidChild());
			rightchild = factorConvert((FactorNode) n.getRightChild());

			if (!leftchild.getExprType().equals(rightchild.getExprType())) {
				System.out
						.println("Types located at multiply operators should be the same.");
				System.exit(-1);
			}

			if ((!leftchild.getExprType().equals("SMALL"))
					&& (!leftchild.getExprType().equals("BIG"))) {
				System.out
						.println("Types located at multiply operators should be small or big.");
				System.exit(-1);
			}
			resultString = leftchild.getExpr();
			resultString.addAll(rightchild.getExpr());

			resultString.addAll(middlechild.getExpr());
			type = leftchild.getExprType();
		} else {
			System.out.println("error in term convert");
			System.exit(-1);
		}

		result.setExpr(resultString);
		result.setExprType(type);
		return result;
	}

	public SastExpressionNode varConvert(VarNode n) {

		SastExpressionNode result = new SastExpressionNode();
		List<String> resultString = null;
		String type = null;

		if (n == null) {
			System.out.println("error in var convert");
			System.exit(-1);
			return null;
		} else if (n.getType().equals("var")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getIdentifier());
			if(getSymbolTableElement(n.getIdentifier()) != null)
				type = getSymbolTableElement(n.getIdentifier()).getType();
			else{
				System.out.println("Variable "+n.getIdentifier()+" is not defined!");
				System.exit(-1);
			}

		} else if (n.getType().equals("var_array")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getIdentifier());
			SastExpressionNode leftchild = expr_convert(n.getLeftChild());

			if (!leftchild.getExprType().equals("SMALL")) {
				System.out.println("The index of array should be small.");
				System.exit(-1);
			}

			List<String> childString = leftchild.getExpr();
			resultString.add("ArrayBegin");
			resultString.addAll(childString);
//			System.out.println("childString is: " + childString);
			resultString.add("ArrayEnd");

			SymbolTableElement st = getSymbolTableElement(n.getIdentifier());
			if(st == null){
				System.out.println("Variable "+n.getIdentifier()+" is not defined!");
				System.exit(-1);
			}
			
			if (!st.getArray()) {
				System.out.println("'[]' should be used after array.");
				System.exit(-1);
			}

			type = getSymbolTableElement(n.getIdentifier()).getType();

		} else {
			System.out.println("error in var convert");
			System.exit(-1);
		}
//		for (int i = 0; i < resultString.size(); i++) {
//			System.out.println("result string " + i + "is: "
//					+ resultString.get(i));
//		}

		result.setExpr(resultString);
		result.setExprType(type);
		// System.out.println("type in var convert is: " + type);
		// if (type.equals("BOOL"))
		// result.setExprType("BOOL");
		// else
		// result.setExprType("Arithmetic");

		return result;
	}

	public SastExpressionNode addConvert(AdditiveExpressionNode n) {

		SastExpressionNode result = new SastExpressionNode();
		List<String> resultString = null;
		String type = null;

		if (n == null)
			return null;
		else if (n.getType().equals("AdditiveExpression")) {

			SastExpressionNode leftchild = addConvert((AdditiveExpressionNode) n
					.getLeftChild());
			SastExpressionNode rightchild = termConvert(n.getRightChild());

			type = leftchild.getExprType();

			if (!leftchild.getExprType().equals(rightchild.getExprType())) {
				System.out.println("leftchild type is: "
						+ leftchild.getExprType() + " rightchild type is: "
						+ rightchild.getExprType());
				System.out
						.println("Types located at additive operators should be the same.");
				System.exit(-1);
			}

			if ((!leftchild.getExprType().equals("SMALL"))
					&& (!leftchild.getExprType().equals("BIG"))) {
				System.out
						.println("Types located at additive operators should be small or big.");
				System.exit(-1);
			}

			resultString = leftchild.getExpr();
			resultString.addAll(rightchild.getExpr());
			resultString.add(n.getMidChild().getValue());
		} else if (n.getType().equals("small") || n.getType().equals("big")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getValue());
			resultString.add("NEG");
			type = n.getType().toUpperCase();
		} else if (n.getType().equals("var")) {
			SastExpressionNode leftchild = varConvert((VarNode) n
					.getLeftChild());

			if ((!leftchild.getExprType().equals("SMALL"))
					&& (!leftchild.getExprType().equals("BIG"))) {
				System.out
						.println("Types located at UNARY MINUS should be small or big.");
				System.exit(-1);
			}

			type = leftchild.getExprType();

			resultString = leftchild.getExpr();
			resultString.add("NEG");
		} else if (n.getType().equals("additive_term")) {

			SastExpressionNode leftchild = termConvert(n.getLeftChild());
			resultString = leftchild.getExpr();

			type = leftchild.getExprType();

		} else {
			System.out.println("error in add convert");
			System.exit(-1);
		}

		result.setExpr(resultString);
		result.setExprType(type);
		return result;
	}

	public SastExpressionNode factorConvert(FactorNode n) {

		SastExpressionNode result = new SastExpressionNode();
		SastExpressionNode child = null;
		List<String> resultString = null;
		String type = null;

		if (n == null) {
			return null;
		} else if (n.getType().equals("factor_var")) {
			child = varConvert((VarNode) n.getLeftChild());
			resultString = child.getExpr();
			type = child.getExprType();
		} else if (n.getType().equals("factor_call")) {
			child = callConvert((CallNode) n.getLeftChild());
			resultString = child.getExpr();
			type = child.getExprType();
		} else if (n.getType().equals("factor_small")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getValue());
			type = "SMALL";
		} else if (n.getType().equals("factor_big")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getValue());
			type = "BIG";
		} else if (n.getType().equals("factor_T")) {
			resultString = new LinkedList<String>();
			resultString.add("true");
			type = "BOOL";
		} else if (n.getType().equals("factor_F")) {
			resultString = new LinkedList<String>();
			resultString.add("false");
			type = "BOOL";
		} else if (n.getType().equals("factor_file")) {
			resultString = new LinkedList<String>();
			resultString.add(n.getValue());
			type = "FILE";
		} else if (n.getType().equals("factor_expr")) {
			child = expr_convert(n.getLeftChild());
			resultString = child.getExpr();
			type = child.getExprType();
		} else {
			System.out.println("error in factor convert");
			System.exit(-1);
		}

		result.setExpr(resultString);
		result.setExprType(type);

		return result;
	}

	public FunctionTableElement getElementFromFunctionTbl(String id) {
		FunctionTableElement fte = null;
		fte = functionTable.get(id);
		return fte;
	}

	public SastExpressionNode callConvert(CallNode n) {
		SastExpressionNode result = new SastExpressionNode();
		SastExpressionNode child = null;
		List<String> resultString = null;
		String type = null;
		String identifier = null;

		if (n == null) {
			System.out.println("error in call convert");
			System.exit(-1);
			return null;
		} else if (n.getType().equals("call")) {
			identifier = ((CallNode) n).getIdentifier();

			FunctionTableElement fte = getElementFromFunctionTbl(identifier);

			if (fte == null) {
				System.out.println("function " + identifier
						+ " is not defined!");
				System.exit(-1);
			} else {
				child = argsConvert((ArgsNode) n.getLeftChild(), identifier);
				resultString = new LinkedList<String>();
				resultString.add(identifier);
				resultString.add("CallBegin");
				if(child.getExpr() != null)
					resultString.addAll(child.getExpr());
				resultString.add("CallEnd");
				type = fte.getReturnType();
			}
		} else {
			System.out.println("error in call convert");
			System.exit(-1);
		}

		result.setExpr(resultString);
		result.setExprType(type);
		return result;
	}

	public SastExpressionNode argsConvert(ArgsNode n, String identifier) {
		// List<String> args_list = new LinkedList<String>();

		List<List<String>> args_list = new LinkedList<List<String>>();
		FunctionTableElement fte = functionTable.get(identifier);
		

		SastExpressionNode leftchild = null;
		SastExpressionNode rightchild = null;

		Node currentNode = null;
		if (n == null) {
			return new SastExpressionNode();
		} else {
			currentNode = n.getLeftChild();
		}
		
		int k=fte.getArgsType().size()-1;
//		System.out.println(fte.getArgsType());

		while (true) {
			if (currentNode.getRightChild() == null) {
				rightchild = expr_convert(currentNode.getLeftChild());
				
				if(!fte.getArgsType().get(k).equals(rightchild.getExprType())){
					System.out.println("The type in function call is wrong!"+fte.getArgsType().get(k)+rightchild.getExprType());
					System.exit(-1);
				}
				
				args_list.add(rightchild.getExpr());
				break;
			} else {
				rightchild = expr_convert(currentNode.getRightChild());
				if(!fte.getArgsType().get(k).equals(rightchild.getExprType())){
					System.out.println("The type in function call is wrong!");
					System.exit(-1);
				}
				currentNode = currentNode.getLeftChild();
				args_list.add(rightchild.getExpr());
				k--;

			}
		}

		List<String> result = new LinkedList<String>();
		for (int i = args_list.size()-1; i >=0 ; i--) {
			if (i != args_list.size()-1) {
				result.add("ArgComma");
			}
			result.addAll(args_list.get(i));
			
		}
		

		SastExpressionNode sastroot = new SastExpressionNode();
		sastroot.setExpr(result);
		return sastroot;
	}
}
