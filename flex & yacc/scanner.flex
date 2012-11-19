import java_cup.runtime.*;
import java.io.*;

%%

%class scanner 
%line 
%column 
%cup
%unicode

%{
    public static void init(){}
    private Symbol newToken(int type){
        return new Symbol(type,yyline,yycolumn); 
    }
    private Symbol newToken(int type,Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }
%}
letter=[A-Za-z]
identifier={letter}*
digit=[0-9]
small={digit}+
big={small}\.{small}
file=\"(([a-zA-Z]:)?(\/[a-zA-Z0-9._-]+)+\/?|(.\/))[a-zA-Z0-9._-]+(.bdf)\" 
comment=\/\/(.*)
lineTerminator=\r|\n|\r\n
whiteSpace={lineTerminator}|[ \t\f]
%%

<YYINITIAL>{
    "big" 			{ return newToken(sym.BIG); }
	"bool"			{ return newToken(sym.BOOL);}
	"else"			{ return newToken(sym.ELSE); }
	"file"			{ return newToken(sym.FILE); }
	"infileopen"	{ return newToken(sym.INFILEOPEN); }
	"outfileopen"	{ return newToken(sym.OUTFILEOPEN); }
	"infileclose"	{ return newToken(sym.INFILECLOSE); }
	"outfileclose"	{ return newToken(sym.OUTFILECLOSE); }
	"for"			{ return newToken(sym.FOR); }
	"if"			{ return newToken(sym.IF); }
	"input"			{ return newToken(sym.INPUT); }
	"output"		{ return newToken(sym.OUTPUT); }
	"return" 		{ return newToken(sym.RETURN); }
	"small"			{ return newToken(sym.SMALL); }
	void			{ return newToken(sym.VOID); }
	"<<"			{ return newToken(sym.FILEINPUT); }
	">>"			{ return newToken(sym.FILEOUTPUT); }
	"true"			{ return newToken(sym.T, "T"); }
	"false"			{ return newToken(sym.F, "F"); }
	{identifier}	{ return newToken(sym.IDENTIFIER, yytext()); }
	{small}			{ return newToken(sym.small, yytext()); }
	{big}			{ return newToken(sym.big, yytext()); }	
	{file}			{ return newToken(sym.file, yytext()); }
	"+"				{ return newToken(sym.PLUS); }
	"-"				{ return newToken(sym.MINUS); }
	"*"				{ return newToken(sym.TIMES); }
	"/"				{ return newToken(sym.DIVIDE); }
	"%"				{ return newToken(sym.MOD); }
	"["				{ return newToken(sym.LEFT_BRKT); }
	"]"				{ return newToken(sym.RIGHT_BRKT); }
	"="				{ return newToken(sym.ASSIGN); }
	"=="			{ return newToken(sym.EQUAL); }
	">"         	{ return newToken(sym.GTR); }
    "<"      		{ return newToken(sym.LESS); }
	">="         	{ return newToken(sym.GTR_EQ); }
	"<="			{ return newToken(sym.LESS_EQ); }
	"!="			{ return newToken(sym.NOT_EQ); }
	"||"			{ return newToken(sym.OR); }
	"&&"			{ return newToken(sym.AND); }
	"!"        		{ return newToken(sym.NOT); }
	"("				{ return newToken(sym.LEFT_PAREN); }
	")"				{ return newToken(sym.RIGHT_PAREN); }
	{comment}		{ /* Ignore comment. */;}
	";"				{ return newToken(sym.SEMI); }
	","           	{ return newToken(sym.COMMA); }
	"{"				{ return newToken(sym.LEFT_BRACE); }
	"}"				{ return newToken(sym.RIGHT_BRACE); }
	{whiteSpace}   	{ /* Ignore whitespace. */; }
	. 				{ System.out.println("Illegal char ," + yytext() +" line: " + yyline + ", column: " + yycolumn);}
}
