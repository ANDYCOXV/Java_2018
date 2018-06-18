public final class ConicConstant
{
  //Make sure to initilize all constant strings in lowercase.
  public static final String SET_INSTRUCTION = "set";
  public static final String GIVING_INSTRUCTION = "giving";
  public static final String SWAP_INSTRUCTION = "swap";
  public static final String ADD_INSTRUCTION = "add";
  public static final String SUBTRACT_INSTRUCTION = "subtract";
  public static final String MULTIPLY_INSTRUCTION = "multiply";
  public static final String DIVIDE_INSTRUCTION = "divide";
  public static final String SQUARE_ROOT_INSTRUCTION = "squareroot";
  public static final String SQUARE_INSTRUCTION = "square";
  public static final String NEGATE_INSTRUCTION = "negate";
  public static final String GOTO_INSTRUCTION = "goto";
  public static final String LABEL_INSTRUCTION = "label";
  public static final String COMPARE_INSTRUCTION = "compare";
  public static final String GOIFGREATERTHAN_INSTRUCTION = "goifgreaterthan";
  public static final String GOIFLESSTHAN_INSTRUCTION = "goiflessthan";
  public static final String GOIFEQUALTO_INSTRUCTION = "goifequalto";
  public static final String GOIFNOTEQUALTO_INSTRUCTION = "goifnotequalto";
  public static final String POINT_INSTRUCTION = "point";
  public static final String LINESEGMENT_INSTRUCTION = "linesegment";
  public static final String CIRCLE_INSTRUCTION = "circle";
  public static final String VERTICALPARABOLA_INSTRUCTION = "verticalparabola";
  public static final String HORIZONTALPARABOLA_INSTRUCTION = "horizontalparabola";
  public static final String ELLIPSE_INSTRUCTION = "ellipse";
  public static final String VERTICALHYPERBOLA_INSTRUCTION = "verticalhyperbola";
  public static final String HORIZONTALHYPERBOLA_INSTRUCTION = "horizontalhyperbola";
  public static final String READ_INSTRUCTION = "read";
  public static final String STARTDISPLAYBLOCK_INSTRUCTION = "startdisplayblock";
  public static final String ENDDISPLAYBLOCK_INSTRUCTION = "enddisplayblock";
  public static final String COMMENT_SINGLE_LINE_INSTRUCTION = "comment";
  public static final String STARTCOMMENTBLOCK_INSTRUCTION = "startcommentblock";
  public static final String ENDCOMMENTBLOCK_INSTRUCTION = "endcommentblock";
  public static final String EXIT_INSTRUCTION = "exit";
  
  //An array of all of the instructions used for error checking.
  public static final String[] INSTRUCTIONS_ARRAY =
  {
    EXIT_INSTRUCTION, ENDCOMMENTBLOCK_INSTRUCTION, STARTCOMMENTBLOCK_INSTRUCTION,
    COMMENT_SINGLE_LINE_INSTRUCTION, ENDDISPLAYBLOCK_INSTRUCTION, STARTDISPLAYBLOCK_INSTRUCTION,
    READ_INSTRUCTION, HORIZONTALHYPERBOLA_INSTRUCTION, VERTICALHYPERBOLA_INSTRUCTION,
    ELLIPSE_INSTRUCTION, HORIZONTALPARABOLA_INSTRUCTION, VERTICALPARABOLA_INSTRUCTION,
    CIRCLE_INSTRUCTION, LINESEGMENT_INSTRUCTION, POINT_INSTRUCTION,
    GOIFNOTEQUALTO_INSTRUCTION, GOIFEQUALTO_INSTRUCTION, GOIFLESSTHAN_INSTRUCTION,
    GOIFGREATERTHAN_INSTRUCTION, COMPARE_INSTRUCTION, LABEL_INSTRUCTION,
    GOTO_INSTRUCTION, NEGATE_INSTRUCTION, SQUARE_INSTRUCTION,
    SQUARE_ROOT_INSTRUCTION, DIVIDE_INSTRUCTION, MULTIPLY_INSTRUCTION,
    SUBTRACT_INSTRUCTION, ADD_INSTRUCTION, SWAP_INSTRUCTION,
    GIVING_INSTRUCTION, SET_INSTRUCTION
  };
  
  //Display special arguments.
  public static final String DISPLAY_TO_CONSOLE_SUBINSTRUCTION = "$displayspecial:"; //Used specifically for display.
  public static final String DISPLAY_SPACE = "space";
  public static final String DISPLAY_NEWLINE = "newline";
  public static final String DISPLAY_CARRIGERETURN = "carrigereturn";
  public static final String DISPLAY_HORIZONTALTABULATION = "tabulation";
  public static final String DISPLAY_FORMFEED = "formfeed";
  
  //Location of misc "under the hood" registers.
  public static final int ACCUMULATOR_LOCATION = 31; //Location of the accumulator in the variables integer array.
  public static final int COMPARE_LOCATION = 32; //Location of the compare flag. 0 = nothing, 1 = x > y, 2 = x < y, 3 = x == y, 0 = x != y
  
  //Used for the compare register.
  public static final int GOIFGREATERTHAN_TRUE = 1; //This value and below basically are numbers used for boolean comparision if the comparison holds true.
  public static final int GOIFLESSTHAN_TRUE = 2;
  public static final int GOIFEQUALTO_TRUE = 3;
  public static final int GOIFNOTEQUALTO_TRUE = 0; //Set as default zero because there is all but one chance of the numbers not being equal.
  
 //The names of the variables.
  public static final String[] VARIABLE_NAMES = 
  {
  "A", "B", "C", "D", "E", "F",
  "G", "H", "I", "J", "K", "L",
  "M", "N", "O", "P", "Q", "R",
  "S", "T", "U", "V", "W", "X",
  "Y", "Z", //END OF THE VARIABLES
  "#xset", "#xshift", //A_INSTRUCTION
  "#yset", "#yshift", //B_INSTRUCTION
  "#simitransverseaxis", "#simimajoraxis", "#focaldistance", "#xtwoset", "#a", "#p", //C_INSTRUCTION
  "#simiconjugateaxis", "#simiminoraxis", "#ytwoset", "#b", //D_INSTRUCTION
  "#domain", "#range", "#radius" //E_INSTRUCTION
  };
  
  //Set the key word instructions that set specific registers used for clarification of the program.
  public static final String[] A_INSTRUCTION = {"#xset", "#xshift"};
  public static final String[] B_INSTRUCTION = {"#yset", "#yshift"};
  public static final String[] C_INSTRUCTION = {"#simitransverseaxis", "#simimajoraxis", "#focaldistance", "#xtwoset" , "#a" , "#p"};
  public static final String[] D_INSTRUCTION = {"#simiconjugateaxis", "#simiminoraxis", "#ytwoset", "#b"};
  public static final String[] E_INSTRUCTION = {"#domain", "#range", "#radius"};
  
  //Location of the variables to be set in the variable array.
  public static final int A_SET_REGISTER = 26;
  public static final int B_SET_REGISTER = 27;
  public static final int C_SET_REGISTER = 28;
  public static final int D_SET_REGISTER = 29;
  public static final int E_SET_REGISTER = 30;
  
  //A list of all the command line arguments.
  public static final String SOURCE_FILE = "-s"; //-s <SOURCEFILENAME>
  public static final String PRINT_CONIC_ATTRIBUTES = "-p";
  
  //Miscellanous file constants.
  public static final String NO_FILE_FOUND = "NOFILEFOUND";
  public static int RGB_COLOR = 0xFF;
  public static int MAX_FILE_SIZE = (Integer.MAX_VALUE)-8;
}