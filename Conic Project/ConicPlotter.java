/* author: ANDY COX V
 * date: 3/26/2017
 * program: ConicPlotter.jar
 * 
 * Classes:
 *        ConicConstant.java
 *        ConicData.java
 *        ConicPlotter.java
 *        ErrorHandling.java
 *        Interpreter.java
 *        PlotPoints.java
 *        PrintAttributes.java
 * 
 * Description:
 *        This program is the engine of a scripting language Conic Plotter Script used to plot conics.
 * 
 * Command Line Arguments:
 * ConicPlotter.jar -s <SOURCEFILE> -p
 *        -s <SOURCEFILE> to load a source file.
 *        -p to output a companion conic attribute file.
 * */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

class ConicsPlotter
{

  private static ArrayList<Integer> image = new ArrayList<Integer>();
  private static int imageX = Integer.MAX_VALUE;
  private static int imageY = Integer.MAX_VALUE;
  private static boolean printConicAttributes = false;

  //-s <SOURCEFILENAME>.
  //-p Prints a ASCII text file with domain and ranges etc...
  public static void main(String args[])
  {
    
    String fileName = ConicConstant.NO_FILE_FOUND;
    File fileCheck = new File(ConicConstant.NO_FILE_FOUND);
    
    //FOR DEBUGGING ONLY!!!
    args = new String[3];
    args[0] = "-s";
    args[1] = "test.txt";
    args[2] = "-p";
    
    System.out.println("ConicPlotter.jar - Version 1.1 - Copyright 4/14/2017 - Andy Cox V\n" +
    "This program is an engine designed to execute the Connic Plotter Script scripting language.\n" +
    "Which is a scripting language designed to plot conics." + 
    "\nCommand line arguments:\n" +
    "\t*-p - Prints a ASCII text file with the domain and ranges of the plotted conics.\n" +
    "\t*-s <SOURCEFILE> - Loads a script to be executed must be in unformatted ASCII.\n" + 
    "\nExample: ConicPlotter.jar -s test.txt -p" + 
    "\nNOTE: Command line arguments may be in any order.");
    
    if(args.length == 0)
      ErrorHandling.noFileArguments();
    
    //-s source -p.
    if(args.length > 3)
      ErrorHandling.tooManyArguments();
    
    //Search for -s argument and find the file to load.
    for(int i = 0 ; i < 2 ; i++)
      if(args[i].equals(ConicConstant.SOURCE_FILE))
        fileName = args[++i];
    
    if(fileName.equals(ConicConstant.NO_FILE_FOUND))
      ErrorHandling.noSArgument();
    
    fileCheck = new File(fileName);
    
    //Checks to make sure that the file is valid
    if(!fileCheck.exists() || fileCheck.isDirectory())
      ErrorHandling.invalidFile(fileName);
    
    //Check to see how large the file is and if it is valid.
    if(fileCheck.length() > ConicConstant.MAX_FILE_SIZE)
      ErrorHandling.fileTooBig(fileName);
    
    //Search for -p argument and find the file to load.
    for(int i = 0 ; i < 3 ; i++)
      if(args[i].equals(ConicConstant.PRINT_CONIC_ATTRIBUTES))
        printConicAttributes = true;
    
    try
    {
      Interpreter.start(fileName);
      imageSetup(fileName); //Used strictly for the arguments.
      writeFile("test.png");
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
  }
  
 private static void writeFile(String fileName) throws IOException
  {
    File target = new File(fileName);
    BufferedImage graphOut = new BufferedImage(imageX, imageY, BufferedImage.TYPE_INT_RGB);
    
    //Create image.
    for(int i = 0 ; i < image.size() ; i++)
      graphOut.setRGB(image.get(i), image.get(++i), ConicConstant.RGB_COLOR);
    
    ImageIO.write(graphOut, "png", target); //Save image as *.png file.
  }
  
  //Aquires image attributes and make a temporary file to hold the coordinate points.
  public static void imageSetup(String fileName) throws Exception
  {
    ArrayList<Integer> storedPoints = new ArrayList<Integer>();
    int largestPoint = 0;
    int imageSize = 0;
    int xShift = 0;
    int yShift = 0;
    
    //Plot all of the points.
    PlotPoints.pointPlot(ConicData.point());
    PlotPoints.lineSegmentPlot(ConicData.lineSegment());
    PlotPoints.circlePlot(ConicData.circle());
    PlotPoints.verticalParabolaPlot(ConicData.verticalParabola());
    PlotPoints.horizontalParabolaPlot(ConicData.horizontalParabola());
    PlotPoints.ellipsePlot(ConicData.ellipse());
    PlotPoints.verticalHyperbolaPlot(ConicData.verticalHyperbola());
    PlotPoints.horizontalHyperbolaPlot(ConicData.horizontalHyperbola());
    
    //Print attributes of the file.
    if(printConicAttributes)
      PrintAttributes.conic(fileName);
    
    //Find largest absolute value of a point.
    for(int i = 0 ; i < PlotPoints.coordinatePlot().size() ; i++)
      if(Math.abs(PlotPoints.coordinatePlot().get(i)) >= imageSize) //Aquire largest point to find the size of the square image.
        imageSize = (Math.abs(i)+1); //Note zero is a point that is why one is added.
    
    //Find most negative x and y values for the x and y shift.
    //NOTE: Make positive after finding the correct values.
    for(int i = 0 ; i < PlotPoints.coordinatePlot().size() ; i++)
    {
      //Find most negative x value
      if(PlotPoints.coordinatePlot().get(i) < xShift)
        xShift = PlotPoints.coordinatePlot().get(i);
      
      ++i;

      //Find most negative y value
      if(PlotPoints.coordinatePlot().get(i) < yShift)
        yShift = PlotPoints.coordinatePlot().get(i);
    }
    
    //Shift y-axis vertically to have origin at lower left instead of upper left corner.
    //Write the new points to the file.
    for(int i = 0 ; i < PlotPoints.coordinatePlot().size() ; i++)
    {
      storedPoints.add(PlotPoints.coordinatePlot().get(i)-xShift); //Subtract a negative it becomes positive.
      storedPoints.add((PlotPoints.coordinatePlot().get(++i)-yShift)+imageSize); //Also preform vertical shift as well.
    }
  
    //Find the new smallest y and x values to aid in the removal of vertical blank space.
    for(int i = 0 ; i < storedPoints.size() ; i++)
    {      
      //Find the smallest x value.
      if(storedPoints.get(i) < imageX)
        imageX = storedPoints.get(i);
      
      ++i;
      
      //Find the smallest y value.
      if(storedPoints.get(i) < imageY)
        imageY = storedPoints.get(i);
    }
    
    //To remove unnessesary vertical blank space subtract all of the y values by the new largest y.
    for(int i = 0 ; i < storedPoints.size() ; i++)
    {
      image.add(storedPoints.get(i)-imageX); //Preform removal of horizontal blank space.
      image.add(storedPoints.get(++i)-imageY); //Preform removal of vertical blank space.
    }

    imageX = 0; //Clear imageX.
    imageY = 0; //Clear imageY.
    
    //Find the new largest x and y points for the new imageX and imageY of the new image.
    for(int i = 0 ; i < image.size() ; i++)
    {

      //Find largest x value
      if(image.get(i) >= imageX)
        imageX = (image.get(i)+1);
      
      ++i;

      //Find largest y value
      if(image.get(i) >= imageY)
        imageY = (image.get(i)+1);
    }

      if((imageX*imageY) > Integer.MAX_VALUE || (imageX*imageY) < 1)
        ErrorHandling.invalidImageSize(imageX, imageY);
  }
}