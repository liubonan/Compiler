package code_generator;
/* The following code was generated by JFlex 1.4.3 on 5/3/12 11:11 PM */

import java_cup.runtime.*;
import java.io.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 5/3/12 11:11 PM from the specification file
 * <tt>scanner.flex</tt>
 */
class scanner implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\15\1\10\1\0\1\15\1\14\22\0\1\15\1\46\1\4"+
    "\2\0\1\42\1\50\1\0\1\51\1\52\1\41\1\37\1\54\1\40"+
    "\1\3\1\6\12\2\1\5\1\53\1\35\1\45\1\36\2\0\32\1"+
    "\1\43\1\0\1\44\1\0\1\7\1\0\1\33\1\11\1\30\1\12"+
    "\1\22\1\13\1\17\1\1\1\16\2\1\1\21\1\32\1\24\1\20"+
    "\1\25\1\1\1\31\1\23\1\27\1\26\1\34\4\1\1\55\1\47"+
    "\1\56\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\1\1\2\1\1\1\3\1\2\1\4\1\5\2\1"+
    "\1\5\7\1\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\2\2\1\20\1\21\1\22"+
    "\1\23\1\24\1\25\4\0\1\26\5\1\1\27\7\1"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\3\0\1\41\2\1\1\42\11\1\4\0\1\43"+
    "\1\44\5\1\1\45\1\1\1\46\1\1\1\47\4\0"+
    "\1\50\1\1\1\51\2\1\1\52\1\1\4\0\2\1"+
    "\1\53\1\54\3\0\3\1\3\0\4\1\2\55\4\1"+
    "\1\56\3\1\1\57\1\60\1\1\1\61";

  private static int [] zzUnpackAction() {
    int [] result = new int[140];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\57\0\u011a"+
    "\0\u0149\0\u0178\0\u01a7\0\u01d6\0\u0205\0\u0234\0\u0263\0\u0292"+
    "\0\u02c1\0\u02f0\0\u031f\0\57\0\57\0\57\0\57\0\57"+
    "\0\57\0\u034e\0\u037d\0\u03ac\0\u03db\0\57\0\57\0\57"+
    "\0\57\0\57\0\57\0\u040a\0\u0439\0\u0468\0\u0497\0\u04c6"+
    "\0\u04f5\0\u0524\0\u0553\0\u0582\0\u05b1\0\136\0\u05e0\0\u060f"+
    "\0\u063e\0\u066d\0\u069c\0\u06cb\0\u06fa\0\57\0\57\0\57"+
    "\0\57\0\57\0\57\0\57\0\57\0\u040a\0\u0729\0\u0758"+
    "\0\u0787\0\136\0\u07b6\0\u07e5\0\136\0\u0814\0\u0843\0\u0872"+
    "\0\u08a1\0\u08d0\0\u08ff\0\u092e\0\u095d\0\u098c\0\u09bb\0\u09ea"+
    "\0\u0a19\0\u0a48\0\136\0\136\0\u0a77\0\u0aa6\0\u0ad5\0\u0b04"+
    "\0\u0b33\0\136\0\u0b62\0\136\0\u0b91\0\136\0\u0bc0\0\u0bef"+
    "\0\u0c1e\0\u0c4d\0\136\0\u0c7c\0\136\0\u0cab\0\u0cda\0\136"+
    "\0\u0d09\0\u0d38\0\u0d67\0\u0d96\0\u0dc5\0\u0df4\0\u0e23\0\136"+
    "\0\136\0\u0e52\0\u0e81\0\u0eb0\0\u0edf\0\u0f0e\0\u0f3d\0\u0f6c"+
    "\0\u0f9b\0\u0fca\0\u0ff9\0\u1028\0\u1057\0\u1086\0\57\0\u0bc0"+
    "\0\u10b5\0\u10e4\0\u1113\0\u1142\0\136\0\u1171\0\u11a0\0\u11cf"+
    "\0\136\0\136\0\u11fe\0\136";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[140];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\2\1\5\1\2\1\6\1\2"+
    "\1\7\1\10\1\3\1\11\1\12\1\7\1\13\1\3"+
    "\1\14\1\3\1\15\1\16\3\3\1\17\1\3\1\20"+
    "\2\3\1\21\1\22\1\23\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\1\42\1\43\60\0\1\3\7\0\3\3"+
    "\2\0\17\3\24\0\1\4\1\44\53\0\1\45\1\46"+
    "\4\45\1\47\1\45\1\0\3\46\2\45\17\46\22\45"+
    "\6\0\1\50\51\0\1\3\7\0\3\3\2\0\1\51"+
    "\1\3\1\52\14\3\23\0\1\3\7\0\3\3\2\0"+
    "\1\53\1\3\1\54\12\3\1\55\1\3\32\0\1\7"+
    "\47\0\1\3\7\0\2\3\1\56\2\0\6\3\1\57"+
    "\10\3\23\0\1\3\7\0\3\3\2\0\10\3\1\60"+
    "\6\3\23\0\1\3\7\0\3\3\2\0\3\3\1\61"+
    "\13\3\23\0\1\3\7\0\3\3\2\0\14\3\1\62"+
    "\2\3\23\0\1\3\7\0\3\3\2\0\13\3\1\63"+
    "\3\3\23\0\1\3\7\0\3\3\2\0\4\3\1\64"+
    "\12\3\23\0\1\3\7\0\3\3\2\0\2\3\1\65"+
    "\14\3\57\0\1\66\7\0\1\67\47\0\1\70\6\0"+
    "\1\71\56\0\1\72\56\0\1\73\60\0\1\74\57\0"+
    "\1\75\10\0\1\76\62\0\1\77\55\0\1\100\1\77"+
    "\51\0\3\101\2\0\1\77\1\101\1\0\3\101\2\0"+
    "\17\101\3\0\1\101\16\0\10\50\1\0\46\50\1\0"+
    "\1\3\7\0\3\3\2\0\1\3\1\102\15\3\23\0"+
    "\1\3\7\0\3\3\2\0\2\3\1\103\14\3\23\0"+
    "\1\3\7\0\3\3\2\0\3\3\1\104\13\3\23\0"+
    "\1\3\7\0\3\3\2\0\13\3\1\105\3\3\23\0"+
    "\1\3\7\0\3\3\2\0\3\3\1\106\13\3\23\0"+
    "\1\3\7\0\2\3\1\107\2\0\7\3\1\110\7\3"+
    "\23\0\1\3\7\0\3\3\2\0\11\3\1\111\5\3"+
    "\23\0\1\3\7\0\3\3\2\0\5\3\1\112\11\3"+
    "\23\0\1\3\7\0\3\3\2\0\15\3\1\113\1\3"+
    "\23\0\1\3\7\0\3\3\2\0\10\3\1\114\6\3"+
    "\23\0\1\3\7\0\3\3\2\0\11\3\1\115\5\3"+
    "\23\0\1\3\7\0\3\3\2\0\1\116\16\3\23\0"+
    "\3\117\3\0\1\117\1\0\3\117\2\0\17\117\3\0"+
    "\1\117\24\0\1\120\51\0\3\121\2\0\1\122\1\121"+
    "\1\0\3\121\2\0\17\121\3\0\1\121\17\0\1\3"+
    "\7\0\3\3\2\0\3\3\1\123\13\3\23\0\1\3"+
    "\7\0\3\3\2\0\4\3\1\124\12\3\23\0\1\3"+
    "\7\0\3\3\2\0\5\3\1\125\11\3\23\0\1\3"+
    "\7\0\3\3\2\0\1\126\16\3\23\0\1\3\7\0"+
    "\3\3\2\0\10\3\1\127\6\3\23\0\1\3\7\0"+
    "\2\3\1\130\2\0\7\3\1\131\7\3\23\0\1\3"+
    "\7\0\3\3\2\0\4\3\1\132\12\3\23\0\1\3"+
    "\7\0\3\3\2\0\3\3\1\133\13\3\23\0\1\3"+
    "\7\0\3\3\2\0\4\3\1\134\12\3\23\0\1\3"+
    "\7\0\3\3\2\0\10\3\1\135\6\3\23\0\1\3"+
    "\7\0\1\3\1\136\1\3\2\0\17\3\22\0\1\137"+
    "\3\140\3\137\1\140\1\0\3\140\2\137\17\140\3\137"+
    "\1\140\16\137\1\0\3\101\3\0\1\101\1\0\3\101"+
    "\2\0\17\101\3\0\1\101\16\0\1\137\3\141\2\137"+
    "\1\142\1\141\1\0\3\141\2\137\17\141\3\137\1\141"+
    "\16\137\1\0\3\121\3\0\1\121\1\0\3\121\2\0"+
    "\17\121\3\0\1\121\17\0\1\3\7\0\3\3\2\0"+
    "\4\3\1\143\12\3\23\0\1\3\7\0\3\3\2\0"+
    "\3\3\1\144\13\3\23\0\1\3\7\0\3\3\2\0"+
    "\11\3\1\145\5\3\23\0\1\3\7\0\3\3\2\0"+
    "\1\146\16\3\23\0\1\3\7\0\3\3\2\0\10\3"+
    "\1\147\6\3\23\0\1\3\7\0\3\3\2\0\3\3"+
    "\1\150\13\3\23\0\1\3\7\0\3\3\2\0\13\3"+
    "\1\151\3\3\33\0\1\152\45\0\1\137\3\140\3\137"+
    "\1\140\1\0\1\153\2\140\2\137\17\140\3\137\1\140"+
    "\17\137\3\141\2\137\1\142\1\141\1\0\1\154\2\141"+
    "\2\137\17\141\3\137\1\141\16\137\1\0\3\121\3\0"+
    "\1\121\1\0\1\155\2\121\2\0\17\121\3\0\1\121"+
    "\17\0\1\3\7\0\3\3\2\0\4\3\1\156\12\3"+
    "\23\0\1\3\7\0\3\3\2\0\3\3\1\157\13\3"+
    "\23\0\1\3\7\0\3\3\2\0\11\3\1\160\5\3"+
    "\23\0\1\3\7\0\3\3\2\0\6\3\1\161\10\3"+
    "\34\0\1\162\44\0\1\137\3\140\3\137\1\140\1\0"+
    "\1\153\1\163\1\140\2\137\17\140\3\137\1\140\17\137"+
    "\3\141\2\137\1\142\1\141\1\0\1\154\1\164\1\141"+
    "\2\137\17\141\3\137\1\141\17\137\3\141\2\137\1\142"+
    "\1\141\1\0\1\141\1\164\1\141\2\137\17\141\3\137"+
    "\1\141\16\137\1\0\1\3\7\0\3\3\2\0\2\3"+
    "\1\165\7\3\1\166\4\3\23\0\1\3\7\0\3\3"+
    "\2\0\4\3\1\167\12\3\35\0\1\170\43\0\1\137"+
    "\3\140\3\137\1\140\1\0\1\153\1\140\1\171\2\137"+
    "\17\140\3\137\1\140\17\137\3\141\2\137\1\142\1\141"+
    "\1\0\1\154\1\141\1\172\2\137\17\141\3\137\1\141"+
    "\16\137\1\0\1\3\7\0\3\3\2\0\7\3\1\173"+
    "\7\3\23\0\1\3\7\0\3\3\2\0\3\3\1\174"+
    "\13\3\23\0\1\3\7\0\3\3\2\0\2\3\1\175"+
    "\7\3\1\176\4\3\26\0\1\177\52\0\1\137\3\140"+
    "\1\200\2\137\1\140\1\0\1\153\2\140\2\137\17\140"+
    "\3\137\1\140\17\137\3\141\1\200\1\137\1\142\1\141"+
    "\1\0\1\154\2\141\2\137\17\141\3\137\1\141\16\137"+
    "\1\0\1\3\7\0\3\3\2\0\4\3\1\201\12\3"+
    "\23\0\1\3\7\0\3\3\2\0\2\3\1\202\14\3"+
    "\23\0\1\3\7\0\3\3\2\0\7\3\1\203\7\3"+
    "\23\0\1\3\7\0\3\3\2\0\3\3\1\204\13\3"+
    "\23\0\1\3\7\0\3\3\2\0\6\3\1\205\10\3"+
    "\23\0\1\3\7\0\3\3\2\0\5\3\1\206\11\3"+
    "\23\0\1\3\7\0\3\3\2\0\4\3\1\207\12\3"+
    "\23\0\1\3\7\0\3\3\2\0\2\3\1\210\14\3"+
    "\23\0\1\3\7\0\3\3\2\0\4\3\1\211\12\3"+
    "\23\0\1\3\7\0\3\3\2\0\6\3\1\212\10\3"+
    "\23\0\1\3\7\0\3\3\2\0\5\3\1\213\11\3"+
    "\23\0\1\3\7\0\3\3\2\0\4\3\1\214\12\3"+
    "\22\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4653];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\1\1\11\4\1\1\11\14\1\6\11\4\1\6\11"+
    "\4\0\16\1\10\11\1\1\3\0\15\1\4\0\14\1"+
    "\4\0\7\1\4\0\4\1\3\0\3\1\3\0\4\1"+
    "\1\11\15\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[140];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    public static void init(){}
    private Symbol newToken(int type){
        return new Symbol(type,yyline,yycolumn); 
    }
    private Symbol newToken(int type,Object value){
        return new Symbol(type,yyline,yycolumn,value);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 124) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 15: 
          { return newToken(sym.NOT);
          }
        case 50: break;
        case 31: 
          { return newToken(sym.AND);
          }
        case 51: break;
        case 21: 
          { return newToken(sym.RIGHT_BRACE);
          }
        case 52: break;
        case 26: 
          { return newToken(sym.FILEOUTPUT);
          }
        case 53: break;
        case 23: 
          { return newToken(sym.IF);
          }
        case 54: break;
        case 11: 
          { return newToken(sym.MOD);
          }
        case 55: break;
        case 2: 
          { System.out.println("Illegal char ," + yytext() +" line: " + yyline + ", column: " + yycolumn);
          }
        case 56: break;
        case 30: 
          { return newToken(sym.OR);
          }
        case 57: break;
        case 10: 
          { return newToken(sym.TIMES);
          }
        case 58: break;
        case 4: 
          { return newToken(sym.DIVIDE);
          }
        case 59: break;
        case 42: 
          { return newToken(sym.SMALL);
          }
        case 60: break;
        case 7: 
          { return newToken(sym.GTR);
          }
        case 61: break;
        case 28: 
          { return newToken(sym.EQUAL);
          }
        case 62: break;
        case 13: 
          { return newToken(sym.RIGHT_BRKT);
          }
        case 63: break;
        case 24: 
          { return newToken(sym.FILEINPUT);
          }
        case 64: break;
        case 20: 
          { return newToken(sym.LEFT_BRACE);
          }
        case 65: break;
        case 33: 
          { return newToken(sym.BIG);
          }
        case 66: break;
        case 5: 
          { /* Ignore whitespace. */;
          }
        case 67: break;
        case 41: 
          { return newToken(sym.INPUT);
          }
        case 68: break;
        case 47: 
          { return newToken(sym.INFILECLOSE);
          }
        case 69: break;
        case 43: 
          { return newToken(sym.OUTPUT);
          }
        case 70: break;
        case 27: 
          { return newToken(sym.GTR_EQ);
          }
        case 71: break;
        case 8: 
          { return newToken(sym.PLUS);
          }
        case 72: break;
        case 6: 
          { return newToken(sym.LESS);
          }
        case 73: break;
        case 17: 
          { return newToken(sym.RIGHT_PAREN);
          }
        case 74: break;
        case 34: 
          { return newToken(sym.FOR);
          }
        case 75: break;
        case 9: 
          { return newToken(sym.MINUS);
          }
        case 76: break;
        case 45: 
          { return newToken(sym.file, yytext());
          }
        case 77: break;
        case 39: 
          { return newToken(sym.VOID);
          }
        case 78: break;
        case 37: 
          { return newToken(sym.ELSE);
          }
        case 79: break;
        case 3: 
          { return newToken(sym.small, yytext());
          }
        case 80: break;
        case 19: 
          { return newToken(sym.COMMA);
          }
        case 81: break;
        case 46: 
          { return newToken(sym.INFILEOPEN);
          }
        case 82: break;
        case 48: 
          { return newToken(sym.OUTFILEOPEN);
          }
        case 83: break;
        case 14: 
          { return newToken(sym.ASSIGN);
          }
        case 84: break;
        case 1: 
          { return newToken(sym.IDENTIFIER, yytext());
          }
        case 85: break;
        case 44: 
          { return newToken(sym.RETURN);
          }
        case 86: break;
        case 35: 
          { return newToken(sym.BOOL);
          }
        case 87: break;
        case 16: 
          { return newToken(sym.LEFT_PAREN);
          }
        case 88: break;
        case 22: 
          { /* Ignore comment. */;
          }
        case 89: break;
        case 18: 
          { return newToken(sym.SEMI);
          }
        case 90: break;
        case 25: 
          { return newToken(sym.LESS_EQ);
          }
        case 91: break;
        case 38: 
          { return newToken(sym.T, "T");
          }
        case 92: break;
        case 32: 
          { return newToken(sym.big, yytext());
          }
        case 93: break;
        case 49: 
          { return newToken(sym.OUTFILECLOSE);
          }
        case 94: break;
        case 12: 
          { return newToken(sym.LEFT_BRKT);
          }
        case 95: break;
        case 29: 
          { return newToken(sym.NOT_EQ);
          }
        case 96: break;
        case 40: 
          { return newToken(sym.F, "F");
          }
        case 97: break;
        case 36: 
          { return newToken(sym.FILE);
          }
        case 98: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}