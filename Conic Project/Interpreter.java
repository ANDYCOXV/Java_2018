import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter
{

  private static ArrayList<String> instruction = new ArrayList<String>();
  
  public static void start(String fileName) throws Exception
  {
    Scanner source = new Scanner(new File(fileName));
    String word = "";
    boolean notFoundVariableForDisplayLoop = true;
    boolean invalidInstruction = false;
    
    //Load whole file into the instruction string ArrayList.
    while(source.hasNext())
    {
      word = source.next();
      
      //Don't load in commented code.
      if(word.equals(ConicConstant.COMMENT_SINGLE_LINE_INSTRUCTION))
        source.nextLine();
      else if(word.equals(ConicConstant.STARTCOMMENTBLOCK_INSTRUCTION)) //Multiline comment blocks.
      {
        while(!source.next().equals(ConicConstant.ENDCOMMENTBLOCK_INSTRUCTION))
          if(!source.hasNext())
            ErrorHandling.noStartCommentBlockTermination();
      }
      else
        instruction.add(word); //Valid string load it in.
    }
    
    source.close();
    
    for(int i = 0 ; i < instruction.size() ; i++)
    {
      word = instruction.get(i);
      
      if(word.equals(ConicConstant.SET_INSTRUCTION)) //Set an instruction equal to another variable or integer.
        ConicData.loadVar(instruction.get(++i), instruction.get(++i));
      else if(word.equals(ConicConstant.GIVING_INSTRUCTION)) //Sets a variable two the accumulator.
        ConicData.givingVar(instruction.get(++i));
      else if(word.equals(ConicConstant.SWAP_INSTRUCTION)) //Swaps two variables.
        ConicData.swapVar(instruction.get(++i), instruction.get(++i));
      else if(word.equals(ConicConstant.ADD_INSTRUCTION)) //Adds wto variables and stores in the accumulator.
        ConicData.addVar(instruction.get(++i), instruction.get(++i));
       else if(word.equals(ConicConstant.SUBTRACT_INSTRUCTION)) //Subtracts two variables and stores in the accumulator.
        ConicData.subtractVar(instruction.get(++i), instruction.get(++i));
       else if(word.equals(ConicConstant.MULTIPLY_INSTRUCTION)) //Multiplies two variables and stores in the accumulator.
        ConicData.multiplyVar(instruction.get(++i), instruction.get(++i));
       else if(word.equals(ConicConstant.DIVIDE_INSTRUCTION)) //Divides two variables and stores in the accumulator.
        ConicData.divideVar(instruction.get(++i), instruction.get(++i));
       else if(word.equals(ConicConstant.SQUARE_ROOT_INSTRUCTION)) //Takes the positive squre root of a variable.
        ConicData.squareRootVar(instruction.get(++i));
       else if(word.equals(ConicConstant.SQUARE_INSTRUCTION)) //Squares a variable and stores in the accumulator.
        ConicData.squareVar(instruction.get(++i));
       else if(word.equals(ConicConstant.NEGATE_INSTRUCTION)) //Negates a variable and stores in the accumulator.
        ConicData.negateVar(instruction.get(++i));
       else if(word.equals(ConicConstant.GOTO_INSTRUCTION)) //Goto and also implements label, finds label and set i euqal to location.
         i = gotoSearch(i);
       else if(word.equals(ConicConstant.COMPARE_INSTRUCTION)) //Gets two variables and compares them then sets a flag.
         ConicData.compareVar(instruction.get(++i), instruction.get(++i));
       else if(word.equals(ConicConstant.GOIFGREATERTHAN_INSTRUCTION) && (ConicData.giveCompareValue() == ConicConstant.GOIFGREATERTHAN_TRUE))
         i = gotoSearch(i);
       else if(word.equals(ConicConstant.GOIFLESSTHAN_INSTRUCTION) && (ConicData.giveCompareValue() == ConicConstant.GOIFLESSTHAN_TRUE))
         i = gotoSearch(i);
       else if(word.equals(ConicConstant.GOIFEQUALTO_INSTRUCTION) && (ConicData.giveCompareValue() == ConicConstant.GOIFEQUALTO_TRUE))
         i = gotoSearch(i);
       else if(word.equals(ConicConstant.GOIFNOTEQUALTO_INSTRUCTION) && (ConicData.giveCompareValue() == ConicConstant.GOIFNOTEQUALTO_TRUE))
         i = gotoSearch(i);
       else if(word.equals(ConicConstant.POINT_INSTRUCTION)) //Plot the conics instructions.
         ConicData.set(ConicConstant.POINT_INSTRUCTION);
       else if(word.equals(ConicConstant.LINESEGMENT_INSTRUCTION))
         ConicData.set(ConicConstant.LINESEGMENT_INSTRUCTION);
       else if(word.equals(ConicConstant.CIRCLE_INSTRUCTION))
         ConicData.set(ConicConstant.CIRCLE_INSTRUCTION);
       else if(word.equals(ConicConstant.ELLIPSE_INSTRUCTION))
         ConicData.set(ConicConstant.ELLIPSE_INSTRUCTION);
       else if(word.equals(ConicConstant.VERTICALPARABOLA_INSTRUCTION))
         ConicData.set(ConicConstant.VERTICALPARABOLA_INSTRUCTION);
       else if(word.equals(ConicConstant.HORIZONTALPARABOLA_INSTRUCTION))
         ConicData.set(ConicConstant.HORIZONTALPARABOLA_INSTRUCTION);
       else if(word.equals(ConicConstant.VERTICALHYPERBOLA_INSTRUCTION))
         ConicData.set(ConicConstant.VERTICALHYPERBOLA_INSTRUCTION);
       else if(word.equals(ConicConstant.HORIZONTALHYPERBOLA_INSTRUCTION))
         ConicData.set(ConicConstant.HORIZONTALHYPERBOLA_INSTRUCTION);
       else if(word.equals(ConicConstant.READ_INSTRUCTION)) //Make sure that the argument MUST BE AN INSTRUCTION!
         ConicData.readinVar(instruction.get(++i));
       else if(word.equals(ConicConstant.EXIT_INSTRUCTION))
         return; //Exit this class and run the program :-).

       invalidInstruction = true;
       
       //Check to see if it is an instruction and if not then throw an error.
       for(String element : ConicConstant.INSTRUCTIONS_ARRAY)
         if(word.equals(element))
           invalidInstruction = false;
            
      if(invalidInstruction)
         ErrorHandling.invalidString(word);
         
       
       //--- DISPLAY BLOCK INSTRUCTION ---
       if(word.equals(ConicConstant.STARTDISPLAYBLOCK_INSTRUCTION))
       {
         while(!word.equals(ConicConstant.ENDDISPLAYBLOCK_INSTRUCTION))
         {
           i++;
           
           if(i >= instruction.size()) //ERROR: NO ENDDISPLAYBLOCK
           {
             ErrorHandling.noStartDisplayBlockTermination();
           }
           
           word = instruction.get(i);
           
           if(word.equals(ConicConstant.DISPLAY_TO_CONSOLE_SUBINSTRUCTION))
           {
             i++;
             
               notFoundVariableForDisplayLoop = true; //Boolean specifically created for this purpose.
               
               //Check to see if a variable value needs to be displayed.
               for(String element : ConicConstant.VARIABLE_NAMES)
                 if(instruction.get(i).equals(element))
                 {
                   System.out.print(ConicData.readVar(element));
                   notFoundVariableForDisplayLoop = false;
                 }
               
               //Check to see if whitespace character is called.
               if(instruction.get(i).equals(ConicConstant.DISPLAY_SPACE))
                 System.out.print(" ");
               else if(instruction.get(i).equals(ConicConstant.DISPLAY_NEWLINE))
                 System.out.print("\n");
               else if(instruction.get(i).equals(ConicConstant.DISPLAY_CARRIGERETURN))
                 System.out.print("\r");
               else if(instruction.get(i).equals(ConicConstant.DISPLAY_HORIZONTALTABULATION))
                 System.out.print("\t");
               else if(instruction.get(i).equals(ConicConstant.DISPLAY_FORMFEED))
                 System.out.print("\f");
               else if(instruction.get(i).equals(ConicConstant.DISPLAY_TO_CONSOLE_SUBINSTRUCTION)) //For the rare event that someone wants to display "$displayspecial:".
                 System.out.print(ConicConstant.DISPLAY_TO_CONSOLE_SUBINSTRUCTION);
               else if(notFoundVariableForDisplayLoop)
                 ErrorHandling.invalidDisplaySpecailArgument(instruction.get(i)); //ERROR: Invalid argument!
           }
           else if(!word.equals(ConicConstant.ENDDISPLAYBLOCK_INSTRUCTION))
             System.out.print(word + " ");
         }
         word = ""; //Clear word.
       }
    }
  }
  
  //Returns the location of a label.
  public static int gotoSearch(int location)
  {
    int labelIdentifier = ConicData.convertToInteger(instruction.get(++location));
    int foundLocation = 0;
    
    for(int i = 0 ; i < instruction.size() ; i++) //Search for a label instruction.
      if((instruction.get(i).equals(ConicConstant.LABEL_INSTRUCTION)) && (ConicData.convertToInteger(instruction.get(++i)) == labelIdentifier))
        foundLocation = i;

    //Must be invaid or an error if the program has goten this far panic and exit.
    ErrorHandling.invalidGoto(location);
    
    //Here we handle the error that the label is invalid.
    return foundLocation;
  }
}