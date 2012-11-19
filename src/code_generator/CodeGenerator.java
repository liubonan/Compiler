package code_generator;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


import nodes.ProgramNode;
import sast_nodes.*;




public class CodeGenerator {
	
	private static String file_flag = "std";
	
	public static StringBuffer progGen(SastProgramNode progNode){
		StringBuffer prog = new StringBuffer();
		
		for(int i=0;i < progNode.getDeclaration_list().size();i++){
			if(progNode.getDeclaration_list().get(i).getType().equals("var_declare")){
				prog.append("private static ");
				prog.append(varDeclarationGen((SastVarDeclarationNode)(progNode.getDeclaration_list().get(i))));
			}
			else if(progNode.getDeclaration_list().get(i).getType().equals("fun_dec")){
				prog.append(funGen((SastFunDeclarationNode)(progNode.getDeclaration_list().get(i))));
			}
			else{
				System.out.println("error in prog Gen");
				System.exit(-1);
			}
		}
		return prog;
	}

	
	public static StringBuffer varDeclarationGen(SastVarDeclarationNode varDecNode) {
		StringBuffer varDec = new StringBuffer();
		
		if (varDecNode.getVarType().toUpperCase().equals("SMALL"))
			varDec.append("Integer");
		else if (varDecNode.getVarType().toUpperCase().equals("BIG"))
			varDec.append("BigDecimal");
		else if (varDecNode.getVarType().toUpperCase().equals("FILE"))
			varDec.append("String");
		else if (varDecNode.getVarType().toUpperCase().equals("BOOL"))
			varDec.append("Boolean");
		
		if (varDecNode.getArraytail() != -1)
			varDec.append("[]");

		varDec.append(" ");
		varDec.append(varDecNode.getIdentifier());

		if (varDecNode.getVarType().toUpperCase().equals("SMALL"))
			varDec.append(" = new Integer");
		else if (varDecNode.getVarType().toUpperCase().equals("BIG"))
			varDec.append(" = new BigDecimal");
		else if (varDecNode.getVarType().toUpperCase().equals("FILE"))
			varDec.append(" = new String");
		else if (varDecNode.getVarType().toUpperCase().equals("BOOL"))
			varDec.append(" = new Boolean");

		if (varDecNode.getArraytail() != -1)
			varDec.append("[" + varDecNode.getArraytail() + "]");
		else{
			if (varDecNode.getVarType().toUpperCase().equals("SMALL")||varDecNode.getVarType().toUpperCase().equals("BIG"))
				varDec.append("(0)");
			else if(varDecNode.getVarType().toUpperCase().equals("FILE"))
				varDec.append("()");
			else if (varDecNode.getVarType().toUpperCase().equals("BOOL"))
				varDec.append("(false)");
		}

		varDec.append(";\n");
		return varDec;

	}

	
	public static StringBuffer funGen(SastFunDeclarationNode funNode) {
		StringBuffer fun = new StringBuffer();
		String funType = funNode.getFunType();
		// type
		// void function
		
		if(funNode.getIdentifier().equals("main")){
			fun.append("public static void main(String[] args) throws Exception");
			fun.append(blockGen(funNode.getBlockNode()));
		}
		else{
			fun.append("public static ");
			if (funType == null) {
				fun.append("void");
			}
			else if (funType.equals("SMALL")) {
				fun.append("Integer");
			} 
			else if (funType.equals("BOOL")) {
				fun.append("Boolean");
			} 
			else if (funType.equals("BIG")) {
				fun.append("BigDecimal");
			}
			else if (funType.equals("FILE")) {
				fun.append("String");
			}
			// function
			else {
				
			}
			fun.append(" ");
			// identifier name
			String identifier = funNode.getIdentifier();
			fun.append(identifier);
			fun.append(" ");
			fun.append("(");
			// parameter list
			List<SastParamsNode> paraList = funNode.getParamnode_list();
			for (int i = 0; i < paraList.size(); ++i) {
				// no array allowed
				String type = null;
				type = paraList.get(i).getParamType();
				if (type.equals("SMALL")) {
					type = "Integer";
				} 
				else if (type.equals("BOOL")) {
					type = "Boolean";
				} 
				else if (type.equals("BIG")) {
					type = "BigDecimal";
				}
				else if (type.equals("FILE")) {
					type = "String";
				}
				
				fun.append(type + " " + paraList.get(i).getIdentifier());
				if (i != paraList.size() - 1)
					fun.append(",");
			}
			fun.append(")");
			fun.append(blockGen(funNode.getBlockNode()));
		}
			
		
		return fun;
	}


	
	
	public static StringBuffer blockGen(SastBlockNode blockNode){
		StringBuffer block = new StringBuffer();
		block.append("{\n");
		List<SastVarDeclarationNode> local_var_list	= new LinkedList<SastVarDeclarationNode>();	
		List<SastNode> stmt_list = new LinkedList<SastNode>();
		
		local_var_list = blockNode.getLocal_var_list();
		stmt_list = blockNode.getStmt_list();
		
		for(int i = 0; i < local_var_list.size(); i++){
			block.append(varDeclarationGen(local_var_list.get(i)));
		}

		for(int i = 0; i < stmt_list.size(); i++){
			block.append(stmtGen(stmt_list.get(i)));			
		}

		block.append("\n");
		block.append("}\n");
		return block;
	}
	
	public static int getExprElementType(String s){
//		System.out.println();
		if(s.matches("([0-9])*\\.([0-9])*"))
			return 2; // big
		else if(s.matches("[0-9]*"))
			return 3; // small
		else if(s.equals("GTR"))
			return 11;
		else if(s.equals("LESS"))
			return 12;
		else if(s.equals("EQUAL"))
			return 13;
		else if(s.equals("GTR_EQ"))
			return 14;
		else if(s.equals("LESS_EQ"))
			return 15;
		else if(s.equals("NOT_EQ"))
			return 16;
		else if(s.equals("ADD"))
			return 21;
		else if(s.equals("MINUS"))
			return 22;
		else if(s.equals("TIMES"))
			return 23;
		else if(s.equals("DIVIDE"))
			return 24;
		else if(s.equals("MOD"))
			return 25;
		else if(s.equals("NEG"))
			return 26;
		else if(s.equals("AND"))
			return 31;
		else if(s.equals("OR"))
			return 32;
		else if(s.equals("NOT"))
			return 33;
		else if(s.equals("CallBegin"))
			return 41;
		else if(s.equals("CallEnd"))
			return 42;
		else if(s.equals("ArrayBegin"))
			return 43;
		else if(s.equals("ArrayEnd"))
			return 44;
		else if(s.equals("ArgComma"))
			return 45;
		else if(s.matches("\\((.*)\\)"))
			return 46;
		else if(s.toUpperCase().matches("\\([A-Z]*\\(([A-Z,\\[\\]\\(\\)0-9\"\\. ]+)*"))
			return 47;
		else if(s.toUpperCase().matches("[A-Z]*\\[.*\\]"))
			return 48;
		else if(s.toUpperCase().matches("[A-Z]*\\(.*\\)"))
			return 49;
		else if(s.toUpperCase().matches("[A-Z]*"))
			return 1; // var
		else
			return -1; //error
	}
	
public static StringBuffer expressionGen(SastExpressionNode exprNode){
		
		
		Stack<String> stack = new Stack<String>();
		
		List<String> expr = exprNode.getExpr();
//		System.out.print("=!!!!============");
//		for(String i: expr)
//			System.out.print(i+" ");
		
		if(expr.size()>0)
			stack.add(expr.get(0));
		
		int i=1;
		String s1=null;
		String s2=null;
		
		while((i<expr.size()) || (stack.size()!=1)){
			
//			System.out.println(i);
////
//			for(String t:stack)
//				System.out.print(t+" ");
//					
//			System.out.println("&&&&&&&");
			String top = stack.pop();
//			System.out.println("top is: " + top);
//			System.out.println("stack size is: " +stack.size());
//			for( i=0; i<stack.size();i++){
//				System.out.println("stack " + i + " is : "+stack.get(i));
//			}
//			
			switch(getExprElementType(top))
			{
			case -1:
				System.out.println("Error in generate Expressions!");
				System.out.println("top is: " + top);
				System.exit(-1);
				break;
			case 2:
				stack.push("(new BigDecimal(\""+top+"\"))");
				break;
			case 11:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(gtr("+s1+","+s2+"))");
				break;
			case 12:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(less("+s1+","+s2+"))");
				break;
			case 13:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(equals("+s1+","+s2+"))");
				break;
			case 14:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(gtr_eq("+s1+","+s2+"))");
				break;
			case 15:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(less_eq("+s1+","+s2+"))");
				break;
			case 16:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(neq("+s1+","+s2+"))");
				break;
			case 21:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(add("+s1+","+s2+"))");
				break;
			case 22:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(sub("+s1+","+s2+"))");
				break;
			case 23:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(mul("+s1+","+s2+"))");
				break;
			case 24:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(divide("+s1+","+s2+"))");
				break;
			case 25:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("(mod("+s1+","+s2+"))");
				break;
			case 26:
				s1=stack.pop();
				stack.push("(negate("+s1+"))");
				break;
			case 31:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("("+s1+" && "+s2+")");
				break;
			case 32:
				s2=stack.pop();
				s1=stack.pop();
				stack.push("("+s1+" || "+s2+")");
				break;
			case 33:
				s1=stack.pop();
				stack.push("(!"+s1+")");
				break;
			case 41:
				s1=stack.pop();
				stack.push("("+s1+"(");
				break;
			case 42:
				s2=stack.pop();
				if(s2.substring(s2.length()-1).equals("(")){
					s2 = s2.substring(1);
					stack.push(s2+")");
				}
				else{
					s1=stack.pop();
					s1 = s1.substring(1);
					stack.push(s1+s2+")");					
				}
				
				break;
			case 43:
				s1=stack.pop();
				stack.push(s1+"ArrayBegin");
				break;
			case 44:
				s2=stack.pop();
				s1=stack.pop();
				String resultstring = (s1+s2+"ArrayEnd").toString();
				if(resultstring.contains("ArrayBegin")){
					resultstring = resultstring.replace("ArrayBegin", "[");
				}
				if(resultstring.contains("ArrayEnd")){
					resultstring = resultstring.replace("ArrayEnd", "]");
				}
				stack.push(resultstring);
				break;
			case 45:
				s2=stack.pop();
				s1=stack.pop();
				stack.push(s1+s2+",");
				break;
			default:				
				stack.push(top);
				stack.push(expr.get(i));
				i++;		
			}

		}
		
		if(expr.size() == 1){
			String top = stack.pop();
			if(getExprElementType(top)==2)
				top="new BigDecimal(\""+top+"\")";
			stack.push(top);
		}
			
	
		StringBuffer s = new StringBuffer(stack.pop());		
//		String resultstring = s.toString();
//		if(resultstring.contains("ArrayBegin")){
//			resultstring = resultstring.replace("ArrayBegin", "[");
//		}
//		if(resultstring.contains("ArrayEnd")){
//			resultstring = resultstring.replace("ArrayEnd", "]");
//		}
//		StringBuffer result = new StringBuffer(resultstring);
		return s;
	}




public static StringBuffer fileGen(SastFileStatementNode n){
		StringBuffer s = new StringBuffer("");
		CodeFileHelper cfh=new CodeFileHelper();
		
		if (n.getType().equals("file_INFILEOPEN")) {
			String e = expressionGen(n.getExpr1()).toString();
			
			s.append("FileReader ");
			s.append(cfh.FRname(e));
			s.append(" = new FileReader(");			
			s.append(e);			
			s.append(");\n");			
			s.append("BufferedReader ");
			s.append(cfh.BRname(e));
			s.append("= new BufferedReader(");
			s.append(cfh.FRname(e));
			s.append(");\n");
			s.append("String ");
			s.append(cfh.Lname(e));
			s.append("=null;\n");
			
		} else if (n.getType().equals("file_INFILECLOSE")) {
			String e = expressionGen(n.getExpr1()).toString();			
			s.append(cfh.BRname(e));
			s.append(".close();\n");
			s.append(cfh.FRname(e));
			s.append(".close();\n");
			
		} else if (n.getType().equals("file_OUTFILEOPEN")) {
			String e = expressionGen(n.getExpr1()).toString();
			
			s.append("FileWriter ");
			s.append(cfh.FWname(e));
			s.append(" = new FileWriter(");			
			s.append(e);			
			s.append(");\n");			
			s.append("BufferedWriter ");
			s.append(cfh.BWname(e));
			s.append("= new BufferedWriter(");
			s.append(cfh.FWname(e));
			s.append(");\n");	
			
		} else if (n.getType().equals("file_OUTFILECLOSE")) {
			String e = expressionGen(n.getExpr1()).toString();
			s.append(cfh.BWname(e));
			s.append(".flush();\n");			
			s.append(cfh.BWname(e));
			s.append(".close();\n");
			s.append(cfh.FWname(e));
			s.append(".close();\n");
			
		} else if (n.getType().equals("file_input")) {
			
			String e1 = expressionGen(n.getExpr1()).toString();
			String e2 = expressionGen(n.getExpr2()).toString();	
			
			s.append("if (");
			s.append(cfh.BRname(e2));
			s.append(".ready()){\n");
			s.append(cfh.Lname(e2));
			s.append(" = ");
			s.append(cfh.BRname(e2));
			s.append(".readLine();\n");
			s.append("}");
			s.append(e1);
			s.append(" = new BigDecimal(");
			s.append(cfh.Lname(e2));
			s.append(");\n");
			
		} else if (n.getType().equals("file_ostmt1")) {
			String e = expressionGen(n.getExpr1()).toString();
			
			if(file_flag.equals("std")){
				s.append("System.out.println(");
				s.append(e);
				s.append(");\n");
			}
			
		} else if (n.getType().equals("file_ostmt2")) {
			String e1 = expressionGen(n.getExpr1()).toString();
			String e2 = expressionGen(n.getExpr2()).toString();
			
			if (file_flag.equals("std")) {
				s.append("System.out.println(output(String.valueOf(");
				s.append(e2);
				s.append("),");
				s.append(e1);
				s.append("));\n");
			}
			
			
		} else if (n.getType().equals("file_ostmt3")) {
			
			String e1 = expressionGen(n.getExpr1()).toString();
			String e2 = expressionGen(n.getExpr2()).toString();			
					
			s.append(cfh.BWname(e2));
			s.append(".write(String.valueOf(");
			s.append(e1);
			s.append("));\n");			
			s.append(cfh.BWname(e2));
			s.append(".newLine();\n");
			
		} else if (n.getType().equals("file_ostmt4")) {
			
			String e1 = expressionGen(n.getExpr1()).toString();
			String e2 = expressionGen(n.getExpr2()).toString();
			String e3 = expressionGen(n.getExpr3()).toString();
			
			s.append(cfh.BWname(e3));
			s.append(".write(output(String.valueOf(");
			s.append(e2);
			s.append("),");
			s.append(e1);
			s.append("));\n");			
			s.append(cfh.BWname(e3));
			s.append(".newLine();\n");	
			
		} else {
			System.exit(-1);
		}
			
		return s;
	}
	
	public static StringBuffer forStmtGen(SastForStatementNode forStmtNode){
		StringBuffer s = new StringBuffer();
		
		s.append("for (");
		s.append(assignStmtGen(forStmtNode.getInitAssgin()));
		s.append(expressionGen(forStmtNode.getTerminalExpr()) + ";");
		s.append(assignStmtGen(forStmtNode.getActionAssign()));
		s.deleteCharAt(s.length()-2);
		s.append(")");
		s.append(stmtGen(forStmtNode.getBodyStmt()));
		
		
		return s;
	}
	
	
	public static StringBuffer assignStmtGen(SastAssignStatementNode assignNode) {
		StringBuffer assign = new StringBuffer();
		assign.append(expressionGen(assignNode.getExpr1()));
		assign.append("=");
		assign.append(expressionGen(assignNode.getExpr2()));
		assign.append(";\n");
		return assign;
	}

	public static StringBuffer ifStmtGen(SastIfStatementNode ifStmtNode) {
		StringBuffer s = new StringBuffer();
		s.append("if(");
		s.append(expressionGen(ifStmtNode.getExpr()) + ")");
		s.append("\n");
		s.append(stmtGen(ifStmtNode.getIfstmt()));
		s.append("\n");
		if (ifStmtNode.getElsestmt() != null) {
			s.append("else\n");
			s.append(stmtGen(ifStmtNode.getElsestmt()));
			s.append("\n");
		}
		
		return s;
	}

	public static StringBuffer stmtGen(SastNode node) {
		
		if (node.getType().equals("if")|| node.getType().equals("if_else"))
			return ifStmtGen((SastIfStatementNode) node);
		else if (node.getType().equals("for"))
			return forStmtGen((SastForStatementNode) node);
		else if (node.getType().equals("assign_stmt"))
			return assignStmtGen((SastAssignStatementNode) node);
		else if (node.getType().substring(0,3).equals("fil"))
			return fileGen((SastFileStatementNode) node);
		else if (node.getType().equals("block"))
			return blockGen((SastBlockNode) node);
		else if (node.getType().equals("return_stmt"))
			return returnStmtGen((SastReturnStatementNode) node);
		else if (node.getType().equals("expr"))
			return new StringBuffer(expressionGen((SastExpressionNode) node).toString() + ";\n");
		else {
			System.out.println("error in stmt gen");
			System.exit(-1);
			return null;
		}

	}
	
	public static StringBuffer returnStmtGen(SastReturnStatementNode ReturnStmt){
		StringBuffer returnstmt=null;
		
		
		StringBuffer SB= new StringBuffer();
		SB.append("return ");
		if((SastExpressionNode)ReturnStmt.getExpr() != null){
			returnstmt=expressionGen((SastExpressionNode)ReturnStmt.getExpr());//ExprGen needs rename
			SB.append(returnstmt);
		}
		SB.append(";\n");
		return SB;
	}
	
	public static void main(String[] args) throws Exception{
		StringBuffer finalOutPut = new StringBuffer();
		parser p = null;
		
		try{
			p = new parser(new scanner(new FileReader("test_cases/type_checking/toBig.db")));			
		}
		catch(Exception e){
			System.out.println("Reading Source File failed!");
			System.out.println(e);
			System.exit(-1);
		}
		
		ProgramNode root = null;
		try{
			p.parse();
			root = (ProgramNode) p.parseResult;			
		}
		catch(Exception e){
			System.out.println("Generate AST Failed!");
			System.out.println(e);
			System.exit(-1);
		}
		

		SemanticAST sast = null;
		SastProgramNode progNode = null;
		
//		try{
			sast = new SemanticAST(root.getLeftChild());
			progNode = sast.prog_convert();
//		}
//		catch(Exception e){
//			System.out.println("Generate SAST Failed!");
//			System.out.println(e);
//			System.exit(-1);
//		}		

		finalOutPut.append(CodeInit.OutputHead);
		finalOutPut.append(progGen(progNode));
		finalOutPut.append(CodeInit.OutputTail);	
		
		try{
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("d:\\XBDPL.java"));
			osw.write(finalOutPut.toString());			
			osw.flush();
			osw.close();
			System.out.print("Compile successfully!");
		}
		catch(Exception e){
			System.out.println("Generate Code failed!");
			System.out.println(e);
			System.exit(-1);
		}
	     

	}
	
//	public static String kernel(String source, String filename){
//		StringBuffer finalOutPut = new StringBuffer();
//		parser p = null;
//		
//		try{
//			p = new parser(new scanner(new StringReader(source)));			
//		}
//		catch(Exception e){
//			return "Reading Source File failed!\n"+e.toString();
//		}
//		
//		ProgramNode root = null;
//		try{
//			p.parse();
//			root = (ProgramNode) p.parseResult;			
//		}
//		catch(Exception e){
//			return "Generate AST Failed!\n"+e.toString();
//		}
//		
//
//		SemanticAST sast = null;
//		SastProgramNode progNode = null;
//		
//		try{
//			sast = new SemanticAST(root.getLeftChild());
//			progNode = sast.prog_convert();
//		}
//		catch(Exception e){
//			return "Generate SAST Failed!\n"+e.toString();
//		}		
//
//		finalOutPut.append(CodeInit.OutputHead);
//		finalOutPut.append(progGen(progNode));
//		finalOutPut.append(CodeInit.OutputTail);	
//		
//		try{
//			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("/tmp/XBDPL/"+filename+"/XBDPL.java"));
//			osw.write(finalOutPut.toString());			
//			osw.flush();
//			osw.close();
//		}
//		catch(Exception e){
//			return "Generate Code failed!\n"+e.toString();
//		}
//		
//		return "Success";
//	}
	
	public static String kernel(String source){
		StringBuffer finalOutPut = new StringBuffer();
		parser p = null;
		
		try{
			p = new parser(new scanner(new FileReader(source)));			
		}
		catch(Exception e){
			return "Reading Source File failed!\n"+e.toString();
		}
		
		ProgramNode root = null;
		try{
			p.parse();
			root = (ProgramNode) p.parseResult;			
		}
		catch(Exception e){
			return "Generate AST Failed!\n"+e.toString();
		}
		

		SemanticAST sast = null;
		SastProgramNode progNode = null;
		
		try{
			sast = new SemanticAST(root.getLeftChild());
			progNode = sast.prog_convert();
		}
		catch(Exception e){
			return "Generate SAST Failed!\n"+e.toString();
		}		

		finalOutPut.append(CodeInit.OutputHead);
		finalOutPut.append(progGen(progNode));
		finalOutPut.append(CodeInit.OutputTail);	
		
		try{
			OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("XBDPL.java"));
			osw.write(finalOutPut.toString());			
			osw.flush();
			osw.close();
		}
		catch(Exception e){
			return "Generate Code failed!\n"+e.toString();
		}
		
		return "Success";
	}
	

}
