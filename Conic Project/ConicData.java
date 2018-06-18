import java.util.ArrayList;
import java.lang.Math;
import java.util.Scanner;

public class ConicData
{
  
  //NOTE: Integer ArrayLists can store up to Integer.MAX_VALUE elements.
  private static ArrayList<Integer> pointList = new ArrayList<Integer>(); //Load values of points in the order x y.
  private static ArrayList<Integer> lineSegmentList = new ArrayList<Integer>(); // Load values in the order x1 y1 x2 y2.
  private static ArrayList<Integer> circleList =  new ArrayList<Integer>(); // Load values in the order h k r.
  private static ArrayList<Integer> verticalParabolaList = new ArrayList<Integer>(); //Load values in the order h k p range.
  private static ArrayList<Integer> horizontalParabolaList = new ArrayList<Integer>(); //Load values in the order h k p domain.
  private static ArrayList<Integer> ellipseList = new ArrayList<Integer>(); //Load values in the order h k a b.
  private static ArrayList<Integer> verticalHyperbolaList = new ArrayList<Integer>(); //Load values in the order h k a b range.
  private static ArrayList<Integer> horizontalHyperbolaList = new ArrayList<Integer>(); //Load values in the order h k a b domain.
  private static int[] variables = new int[33]; //26 Variables [0...25], 5 registers [26...30], 1 accumulator [31], 1 compareto flag [32].
  private static Scanner userInput = new Scanner(System.in);
  
  //NOTE: There is only one array and it is the memory array.
  public static void set(String conicType)
  {
    if(conicType.equals(ConicConstant.POINT_INSTRUCTION)) //Plots a basic point.
    {
      pointList.add(variables[ConicConstant.A_SET_REGISTER]); //X
      pointList.add(variables[ConicConstant.B_SET_REGISTER]); //Y
    }
    else if(conicType.equals(ConicConstant.LINESEGMENT_INSTRUCTION)) //Adds points to calculate for the line segment.
    {
      lineSegmentList.add(variables[ConicConstant.A_SET_REGISTER]); //X1
      lineSegmentList.add(variables[ConicConstant.B_SET_REGISTER]); //Y1
      lineSegmentList.add(variables[ConicConstant.C_SET_REGISTER]); //X2
      lineSegmentList.add(variables[ConicConstant.D_SET_REGISTER]); //Y2
    }
    else if(conicType.equals(ConicConstant.CIRCLE_INSTRUCTION)) //Adds points and radius for the circle.
    {
      circleList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      circleList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      circleList.add(Math.abs(variables[ConicConstant.E_SET_REGISTER])); //RADIUS
    }
    else if(conicType.equals(ConicConstant.VERTICALPARABOLA_INSTRUCTION)) //Adds points p and range for vertical parabolas.
    {
      verticalParabolaList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      verticalParabolaList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      verticalParabolaList.add(variables[ConicConstant.C_SET_REGISTER]); //P
      verticalParabolaList.add(variables[ConicConstant.E_SET_REGISTER]); //RANGE
    }
    else if(conicType.equals(ConicConstant.HORIZONTALPARABOLA_INSTRUCTION)) //Adds points p and domain for horizontal parabolas.
    {
      horizontalParabolaList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      horizontalParabolaList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      horizontalParabolaList.add(variables[ConicConstant.C_SET_REGISTER]); //P
      horizontalParabolaList.add(variables[ConicConstant.E_SET_REGISTER]); //DOMAIN
    }
    else if(conicType.equals(ConicConstant.ELLIPSE_INSTRUCTION)) //Adds h k a b for ellipse.
    {
      ellipseList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      ellipseList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      ellipseList.add(Math.abs(variables[ConicConstant.C_SET_REGISTER])); //A
      ellipseList.add(Math.abs(variables[ConicConstant.D_SET_REGISTER])); //B
    }
    else if(conicType.equals(ConicConstant.VERTICALHYPERBOLA_INSTRUCTION)) //Adds h k a b and range for vertical hyperbolas.
    {
      verticalHyperbolaList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      verticalHyperbolaList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      verticalHyperbolaList.add(Math.abs(variables[ConicConstant.C_SET_REGISTER])); //A
      verticalHyperbolaList.add(Math.abs(variables[ConicConstant.D_SET_REGISTER])); //B
      verticalHyperbolaList.add(variables[ConicConstant.E_SET_REGISTER]); //RANGE
    }
    else if(conicType.equals(ConicConstant.HORIZONTALHYPERBOLA_INSTRUCTION)) //Adds h k a b and domain for vertical hyperbolas.
    {
      horizontalHyperbolaList.add(variables[ConicConstant.A_SET_REGISTER]); //H
      horizontalHyperbolaList.add(variables[ConicConstant.B_SET_REGISTER]); //K
      horizontalHyperbolaList.add(Math.abs(variables[ConicConstant.C_SET_REGISTER])); //A
      horizontalHyperbolaList.add(Math.abs(variables[ConicConstant.D_SET_REGISTER])); //B
      horizontalHyperbolaList.add(variables[ConicConstant.E_SET_REGISTER]); //DOMAIN
    }
  }

  //Reads an integer value from the user via console.
  //Note: The argument MUST BE AN VARIABLE!
  public static void readinVar(String x)
  {
    
    isVar(x);
    
    int y = Integer.MIN_VALUE;
    String z = userInput.next();
    
    while(y == Integer.MIN_VALUE)
    try
    {
      y = Integer.parseInt(z);
    }
    catch(Exception e)
    {
      ErrorHandling.invalidUserInput(z);
    }
    
    variables[findVar(x)] = y;
  }
  
  //Accessor to give the value of the compareto instruction.
  public static int giveCompareValue()
  {
    return variables[ConicConstant.COMPARE_LOCATION];
  }
  //Compares two variable or integer values and then sets a flag to a value for conditional goto statments.
  //Compares x to y.
  //EX: x > y | x < y | x == y | x != y
  public static void compareVar(String x, String y)
  {
    int xx = convertToInteger(x);
    int yy = convertToInteger(y);
    
    if(xx > yy)
      variables[ConicConstant.COMPARE_LOCATION] = 1;
    else if(xx < yy)
      variables[ConicConstant.COMPARE_LOCATION] = 2;
    else if(xx == yy)
      variables[ConicConstant.COMPARE_LOCATION] = 3;  
    else if(xx != yy)
      variables[ConicConstant.COMPARE_LOCATION] = 0;
  }
  
  //Sets a register equal to a value.
  //y = register location, x = value to load.
  //NOTE: REGISTERS CAN ONLY BE WRITTEN TO!
  public static void setRegister(String x, int y)
  {
    variables[y] = convertToInteger(x);
  }
  
  //Negates a variable or integer value and stores in the accumulator.
  public static void negateVar(String x)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = -convertToInteger(x);
  }
  
  //Squares a variable or integer value and stores in the accumulator.
  public static void squareVar(String x)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = (int)Math.pow(convertToInteger(x), 2);
  }
  //Square Roots a variable and stores in the accumulator positive side.
  //accumulator = squareroot(x).
  public static void squareRootVar(String x)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = (int)Math.sqrt(convertToInteger(x));
  }
  
  //Divides two variables or integer values and stores in the accumulator.
  //accumulator = x/y.
  //Adds two variables and stores in the accumulator.
  public static void divideVar(String x, String y)
  {
    if(convertToInteger(y) == 0)
      ErrorHandling.divideByZero();
    else
      variables[ConicConstant.ACCUMULATOR_LOCATION] = convertToInteger(x) / convertToInteger(y);
  }
  
  //Multiplies two variables or integer values and stores in the accumulator.
  //accumulator = x*y.
  public static void multiplyVar(String x, String y)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = convertToInteger(x) + convertToInteger(y);
  }
  
  //Subtracts two variables or integer values and stores in the accumulator.
  //accumulator = x-y.
  public static void subtractVar(String x, String y)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = convertToInteger(x) - convertToInteger(y);
  }
  
  //Adds two variables or integer values and stores in the accumulator.
  //accumulator = x+y.
  public static void addVar(String x, String y)
  {
    variables[ConicConstant.ACCUMULATOR_LOCATION] = convertToInteger(x) + convertToInteger(y);
  }
  
  //Sets a variable equal to the accumulator.
  public static void givingVar(String x)
  {
    variables[findVar(x)] = variables[ConicConstant.ACCUMULATOR_LOCATION];
  }
  
  //Swaps two variables.
  // x:=y and y:=x.
  public static void swapVar(String x, String y)
  {
    isVar(x);
    isVar(y);
    
    int temp = variables[findVar(x)]; //Stores old x variable value.
    
    variables[findVar(x)] = variables[findVar(y)];
    variables[findVar(y)] = temp;
  }
  
  //Loads a variable or integer into a variable.
  //x must be a variable, y = variable or integer.
  //x:=y.
  public static void loadVar(String x, String y)
  {
    isVar(x);
    variables[findVar(x)] = convertToInteger(y);
  }
  
  //Returns the integer value of the variable at the specified location.
  public static int readVar(String x)
  {
    return variables[findVar(x)];
  }
  
  //Check to make sure that a variable exists ERRORS if invalid.
  public static void isVar(String x)
  {
    if(findVar(x) == -1) //ERROR NOT A VARIABLE!
    {
      ErrorHandling.invalidVariable(x);
    }
  }
  
  //Finds the location of a variable.
  //I could just xor a byte by 'a', but Java char's are 16-bit unicode and Strings arn't bytes.
  //I also could just use hard coded values but for exapandability reasons.
  //A = Location 0, Z = Location 25
  //Registers 26-30
  //Returns -1 if it does not exist.
  public static int findVar(String x)
  {
    int variableLocation = -1;
    
    for(int i = 0 ; i < ConicConstant.VARIABLE_NAMES.length ; i++)
      if(ConicConstant.VARIABLE_NAMES[i].equals(x))
        variableLocation = i;
    
    //Access the registers as variables.
    for(int i = 0 ; i < ConicConstant.A_INSTRUCTION.length ; i++)
      if(ConicConstant.A_INSTRUCTION[i].equals(x))
        variableLocation = ConicConstant.A_SET_REGISTER;
    
    for(int i = 0 ; i < ConicConstant.B_INSTRUCTION.length ; i++)
      if(ConicConstant.B_INSTRUCTION[i].equals(x))
        variableLocation = ConicConstant.B_SET_REGISTER;
    
    for(int i = 0 ; i < ConicConstant.C_INSTRUCTION.length ; i++)
      if(ConicConstant.C_INSTRUCTION[i].equals(x))
        variableLocation = ConicConstant.C_SET_REGISTER;
    
    for(int i = 0 ; i < ConicConstant.D_INSTRUCTION.length ; i++)
      if(ConicConstant.D_INSTRUCTION[i].equals(x))
        variableLocation = ConicConstant.D_SET_REGISTER;
    
    for(int i = 0 ; i < ConicConstant.E_INSTRUCTION.length ; i++)
      if(ConicConstant.E_INSTRUCTION[i].equals(x))
        variableLocation = ConicConstant.E_SET_REGISTER;
    
    return variableLocation;
  }
  
  //Extracts an integer value from variables or convert a string to an integer.
  public static int convertToInteger(String x)
  {
    int convertedInteger = 0;
    
    if(findVar(x) != -1)
      convertedInteger = variables[findVar(x)];
    else
      try
      {
        convertedInteger = Integer.parseInt(x);
      }
      catch(Exception e) //ERROR: Not a variable or integer.
      {
        ErrorHandling.invalidVariable(x);
      }
    
    return convertedInteger;
  }
  
  //Returns an ArrayList of all horizontal hyperbola lists.
  public static ArrayList<Integer> horizontalHyperbola()
  {
    return horizontalHyperbolaList;
  }
  
  //Returns an ArrayList of all vertical hyperbola lists.
  public static ArrayList<Integer> verticalHyperbola()
  {
    return verticalHyperbolaList;
  }
  
  //Retuns an ArrayList of all ellipse lists.
  public static ArrayList<Integer> ellipse()
  {
    return ellipseList;
  }
  
  //Returns an ArrayList of all horizontal parabola lists.
  public static ArrayList<Integer> horizontalParabola()
  {
    return horizontalParabolaList;
  }
  
  //Returns an ArrayList of all vertical parabola lists.
  public static ArrayList<Integer> verticalParabola()
  {
    return verticalParabolaList;
  }
  
  //Returns an ArrayList of all line segment start and stop points.
  public static ArrayList<Integer> lineSegment()
  {
    return lineSegmentList;
  }
  
  //Returns an ArrayList of all circle shift and radius.
  public static ArrayList<Integer> circle()
  {
    return circleList;
  }
  
  //Returns an ArrayList of all the point x and ys.
  public static ArrayList<Integer> point()
  {
    return pointList;
  }
}