public class ErrorHandling
{

  //Divide by Zero.
  //Panic
  public static void divideByZero()
  {
    System.out.println("\nERROR: Cannot divide by zero");
    errorPanic();
  }
  
  //The file size is too big.
  //Panic.
  public static void fileTooBig(String x)
  {
    System.out.println("\nERROR: File " + x + " is too large to load!\n" +
                       "Files must be " + ConicConstant.MAX_FILE_SIZE + " bytes or less.");
    errorPanic();
  }
  
  //Invalid file.
  //Panic.
  public static void invalidFile(String x)
  {
    System.out.println("\nERROR: Invalid file: " + x);
    errorPanic();
  }
  
  //No -s argument found!
  //Panic.
  public static void noSArgument()
  {
    System.out.println("\nERROR: No \"-s\" argument found to load source file!");
    errorPanic();
  }
  
  //Too many command line arguments.
  //Panic.
  public static void tooManyArguments()
  {
    System.out.println("\nERROR: Too many command line arguments!");
    errorPanic();
  }
  //No file arguments.
  //Panic.
  public static void noFileArguments()
  {
    System.out.println("\nERROR: No arguments found!");
    errorPanic();
  }
  
  //Invalid image size, the image must be X*Y less than Integer.MAX_VALUE and greater than 0.
  //Panic.
  public static void invalidImageSize(int x, int y)
  {
    System.out.println("\nERROR: Invalid image size: " + (x*y) + " X-axis length: " + x + " Y-axis length: " + y);
    errorPanic();
  }
  //Invalid input from read.
  //Continue.
  public static void invalidUserInput(String x)
  {
    System.out.print("\nERROR: Invalid user input" + x + 
                     "\nOnly positive or negative integer values inclusivly between " + Integer.MIN_VALUE + " to " + Integer.MAX_VALUE + "\n" +
                     "Please try again: ");
  }

  //Says that the designated variable or integer is invalid.
  //Panic.
  public static void invalidVariable(String x)
  {
    System.out.println("\nERROR: Invalid variable or integer found: " + x);
    errorPanic();
  }
  
  //Says that the $displayspecial: argument is invalid.
  //Panic.
  public static void invalidDisplaySpecailArgument(String x)
  {
    System.out.println("\nERROR: Invalid displayspecial ($displayspecial:) argument found: " + x);
    errorPanic();
  }
  
  //Says that the goto could not be found.
  //Panic.
  public static void invalidGoto(int x)
  {
    System.out.println("\nERROR: Invalid label integer or variable value identifier: " + x);
    errorPanic();
  }
  
  //Says that there is no maching enddisplayblock.
  //Panic.
  public static void noStartDisplayBlockTermination()
  {
    System.out.println("\nERROR: No termination/matching enddisplayblock for startdisplayblock instruction!");
    errorPanic();
  }
  
  //Says that there is no maching endcommentblock.
  //Panic.
  public static void noStartCommentBlockTermination()
  {
    System.out.println("\nERROR: No termination/matching endcommentblock for startcommentblock instruction!");
    errorPanic();
  }
  
  //Says that a string found is orphand and is invalid.
  //Panic.
  public static void invalidString(String x)
  {
    System.out.println("\nERROR: Invalid instruction: " + x);
    errorPanic();
  }
  
  //For major errors that prevent the program from operating.
  public static void errorPanic()
  {
    System.exit(0);
  }
}