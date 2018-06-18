import java.io.FileWriter;
import java.io.IOException;

public class PrintAttributes
{
  //Simply prints a file with the domain and ranges of the conics that are plotted and what conics was plotted etc...
  public static void conic(String fileName) throws Exception
  {
    String newFileName = fileName + "_ConicAttributes.txt"; //Could clean this up but nah.
    FileWriter target = new FileWriter(newFileName);
    int number = 0;
    int xshift = 0;
    int yshift = 0;
    int temp1 = 0;
    int temp2 = 0;
    int temp3 = 0;
    int temp4 = 0; //Used with hyperbolas.
    
    //Print out the header for the file.
    //NOTE: There will be a space at the end of this block of text.
    target.write("CONIC ATTRIBUTE FILE " + newFileName);
    target.write(System.lineSeparator());
    target.write("This file lists the attributes of the conics computed from the path of the script file " + fileName + ".");
    target.write(System.lineSeparator());
    
    //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write("POINTS");
    target.write(System.lineSeparator());
    target.write("======");

    if(ConicData.point().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the points.
    for(int i = 0 ; i < ConicData.point().size() ; i++)
    {
      number++;
      target.write(System.lineSeparator()); //Makes a new line since \n is not platform independant.
      target.write(System.lineSeparator());
      target.write("POINT #" + number + " (" + ConicData.point().get(i) + "," + ConicData.point().get(++i) + ")");
      target.flush();
    }
    
    //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("LINE SEGMENTS");
    target.write(System.lineSeparator());
    target.write("=============");
    
    if(ConicData.lineSegment().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the linesegment.
    for(int i = 0 ; i < ConicData.lineSegment().size() ; i++)
    {
      number++;
      xshift = ConicData.lineSegment().get(i); //x
      yshift = ConicData.lineSegment().get(++i); //y
      temp1 = ConicData.lineSegment().get(++i); //x2
      temp2 = ConicData.lineSegment().get(++i); //y2

      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("LINE SEGMENT #" + number);
      
      //Check for divide by zero.
      if((temp1-xshift) != 0)
      {
        temp3 = ((temp2-yshift)/(temp1-xshift)); //Slope
        target.write(System.lineSeparator());
        target.write("EQUATION: " + "y=" + temp3 + "*x+" + (yshift-(temp3-xshift)));
        target.write(System.lineSeparator());
      }
      else //Undefined Slope.
      {
        target.write(System.lineSeparator());
        target.write("EQUATION: (UNDEFINED SLOPE) x=" + xshift);
        target.write(System.lineSeparator());
      }
      
      //Remember in interval notation smallest first biggest last.
      if(xshift < temp1)
        target.write("DOMAIN: [" + (xshift) + "," + (temp1) + "]");
      else
        target.write("DOMAIN: [" + (temp1) + "," + (xshift) + "]");
      
      target.write(System.lineSeparator());
      
      if(yshift < temp2)
        target.write("RANGE: [" + (yshift) + "," + (temp2) + "]");
      else
        target.write("RANGE: [" + (temp2) + "," + (yshift) + "]");
      
      target.flush();
    }
    

    //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("VERTICAL PARABOLA");
    target.write(System.lineSeparator());
    target.write("=================");
    
    if(ConicData.verticalParabola().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the vertical parabola.
    for(int i = 0 ; i < ConicData.verticalParabola().size() ; i++)
    {
      number++;
      xshift = ConicData.verticalParabola().get(i);
      yshift = ConicData.verticalParabola().get(++i);
      temp1 = ConicData.verticalParabola().get(++i); //P
      temp2 = ConicData.verticalParabola().get(++i); //Range
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("VERTICAL PARABOLA #" + number);
      target.write(System.lineSeparator());
      target.write("EQUATION: " + "(x-" + xshift + ")^2=4*" + temp1 + "*(y-" + yshift + ")");
      
      temp3 = xshift+(int)Math.sqrt(4*temp1*temp2); //Find the positive value of x.
      
      //Remember in interval notation smallest first biggest last.
      target.write(System.lineSeparator());
      target.write("DOMAIN: [" + (-temp3) + "," + (temp3) + "]");
      
      target.write(System.lineSeparator());
      
      if(temp2 < yshift)
        target.write("RANGE: [" + (temp2) + "," + (yshift) + "]");
      else
        target.write("RANGE: [" + (yshift) + "," + (temp2) + "]");
      
      target.flush();
    }
    
     //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("HORIZONTAL PARABOLA");
    target.write(System.lineSeparator());
    target.write("===================");
    
    if(ConicData.horizontalParabola().size() == 0)
      target.write("NONE");
    
    target.flush();
    number = 0;
    
    //Print the horizontal parabola.
    for(int i = 0 ; i < ConicData.horizontalParabola().size() ; i++)
    {
      number++;
      xshift = ConicData.horizontalParabola().get(i);
      yshift = ConicData.horizontalParabola().get(++i);
      temp1 = ConicData.horizontalParabola().get(++i); //P
      temp2 = ConicData.horizontalParabola().get(++i); //DOMAIN
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("HORIZONTAL PARABOLA #" + number);
      target.write(System.lineSeparator());
      target.write("EQUATION: " + "(y-" + yshift + ")^2=4*" + temp1 + "*(x-" + xshift + ")");
      
      temp3 = yshift+(int)Math.sqrt(4*temp1*temp2); //Find the positive value of y.
      
      //Remember in interval notation smallest first biggest last.
      target.write(System.lineSeparator());
      
      if(temp1 < xshift)
        target.write("DOMAIN: [" + (temp1) + "," + (xshift) + "]");
      else
        target.write("DOMAIN: [" + (xshift) + "," + (temp1) + "]");
      
      target.write(System.lineSeparator());      
      target.write("RANGE: [" + (-temp3) + "," + (temp3) + "]");
      
      target.flush();
    }
    
    //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("CIRCLES");
    target.write(System.lineSeparator());
    target.write("=======");
    
    if(ConicData.circle().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the circle.
    for(int i = 0 ; i < ConicData.circle().size() ; i++)
    {
      number++;
      xshift = ConicData.circle().get(i);
      yshift = ConicData.circle().get(++i);
      temp1 = ConicData.circle().get(++i); //Radius
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("CIRCLE #" + number);
      target.write(System.lineSeparator());
      target.write("EQUATION: " + "(x-" + xshift + ")^2+(y-" + yshift + ")^2=" + temp1 + "^2");
      target.write(System.lineSeparator());
      
      //Negative minus a negative is addition.
      if((temp1-xshift) < ((-temp1)-xshift))
      {
        target.write("DOMAIN: [" + (temp1-xshift) + "," + ((-temp1)-xshift) + "]"); 
        target.write(System.lineSeparator());
        target.write("RANGE: [" + (temp1-yshift) + "," + ((-temp1)-yshift) + "]");
      }
      else
      {
        target.write("DOMAIN: [" + ((-temp1)-xshift) + "," + (temp1-xshift) + "]"); 
        target.write(System.lineSeparator());
        target.write("RANGE: [" + ((-temp1)-yshift) + "," + (temp1-yshift) + "]");
      }
         
      target.flush();
    }
    
    //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("ELLIPSES");
    target.write(System.lineSeparator());
    target.write("========");
    
    if(ConicData.ellipse().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the ellipse.
    for(int i = 0 ; i < ConicData.ellipse().size() ; i++)
    {
      number++;
      xshift = ConicData.ellipse().get(i);
      yshift = ConicData.ellipse().get(++i);
      temp1 = ConicData.ellipse().get(++i); //a
      temp2 = ConicData.ellipse().get(++i); //b
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("ELLIPSE #" + number);

      //Set's up the proper major and minor axis if a user error occured.
      //Find out if it is a horizontal or vertical ellipse as well.
      //A negative minus a negative is addition.
      if(temp1 >= temp2) //Horizontal ellipse.
      {
        target.write(System.lineSeparator());
        target.write("EQUATION (HORIZONTAL ELLIPSE): " + "(x-" + xshift + ")^2/" + temp1 + "^2+(y-" + yshift + ")^2/" + temp2 + "=1");
        target.write(System.lineSeparator());
        
        if(((-temp1)-xshift)<(temp1-xshift)) //Check if -a-xshift < a-xshift
          target.write("DOMAIN: [" + ((-temp1)-xshift) + "," + (temp1-xshift) + "]");
        else
          target.write("DOMAIN: [" + (temp1-xshift) + "," + ((-temp1)-xshift) + "]");
        
        target.write(System.lineSeparator());
        
        if(((-temp2)-yshift)<(temp2-yshift)) //Check if -a-xshift < a-xshift
          target.write("RANGE: [" + ((-temp2)-yshift) + "," + (temp2-yshift) + "]");
        else
          target.write("RANGE: [" + (temp2-yshift) + "," + ((-temp2)-yshift) + "]");
      }
      else //Vertical ellipse SWAP TEMP2 AND TEMP1 IN THE EQUATION!
      {
        target.write(System.lineSeparator());
        target.write("EQUATION (VERTICAL ELLIPSE): " + "(y-" + yshift + ")^2/" + temp2 + "^2+(x-" + xshift + ")^2/" + temp1 + "=1");
        target.write(System.lineSeparator());
        
        if((temp2-yshift)<((-temp2)-yshift))
          target.write("DOMAIN: [" + (temp2-yshift) + "," + ((-temp2)-yshift) + "]");
        else
          target.write("DOMAIN: [" + ((-temp2)-yshift) + "," + (temp2-yshift) + "]");
        
        target.write(System.lineSeparator());
        
        if((temp1-xshift)<((-temp1)-xshift))
          target.write("RANGE: [" + (temp1-xshift) + "," + ((-temp1)-xshift) + "]");
        else
          target.write("RANGE: [" + ((-temp1)-xshift) + "," + (temp1-xshift) + "]");
      }
      
      target.flush();
    }
    
     //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("VERTICAL HYPERBOLA");
    target.write(System.lineSeparator());
    target.write("==================");
    
    if(ConicData.verticalHyperbola().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the vertical hyperbola.
    for(int i = 0 ; i < ConicData.verticalHyperbola().size() ; i++)
    {
      number++;
      xshift = ConicData.verticalHyperbola().get(i);
      yshift = ConicData.verticalHyperbola().get(++i);
      temp1 = ConicData.verticalHyperbola().get(++i); //a
      temp2 = ConicData.verticalHyperbola().get(++i); //b
      temp3 = ConicData.verticalHyperbola().get(++i); //Range
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("VERTICAL HYPERBOLA #" + number);
      target.write(System.lineSeparator());
      target.write("EQUATION: " + "(y-" + yshift + ")^2/" + temp2 + "^2-(x-" + xshift + ")^2/" + temp1 + "=1");
      
      //Find the positive domain.
      temp4 = (int)(xshift+(Math.sqrt((1-(Math.pow(temp3, 2)/Math.pow(temp2, 2)))*(-Math.pow(temp1, 2)))));
      
      //Remember in interval notation smallest first biggest last.
      target.write(System.lineSeparator());
      target.write("DOMAIN: [" + (-temp4) + "," + ((-temp2)-xshift) + "]" + " U " + "[" + (temp2-xshift) + "," + (temp4) + "]");
      target.write(System.lineSeparator());      
      target.write("RANGE: [" + (-temp3) + "," + ((-temp1)-yshift) + "]" + " U " + "[" + (temp1-yshift) + "," + (temp3) + "]");
      
      target.flush();
    }

     //Use this block of code to print a new line or new instance.
    target.write(System.lineSeparator());
    target.write(System.lineSeparator());
    target.write("HORIZONTAL HYPERBOLA");
    target.write(System.lineSeparator());
    target.write("====================");
    
    if(ConicData.horizontalHyperbola().size() == 0)
    {
      target.write(System.lineSeparator());
      target.write("NONE");
    }
    
    target.flush();
    number = 0;
    
    //Print the horizontal hyperbola.
    for(int i = 0 ; i < ConicData.horizontalHyperbola().size() ; i++)
    {
      number++;
      xshift = ConicData.horizontalHyperbola().get(i);
      yshift = ConicData.horizontalHyperbola().get(++i);
      temp1 = ConicData.horizontalHyperbola().get(++i); //a
      temp2 = ConicData.horizontalHyperbola().get(++i); //b
      temp3 = ConicData.horizontalHyperbola().get(++i); //Domain
      
      target.write(System.lineSeparator());
      target.write(System.lineSeparator());
      target.write("HORIZONTAL HYPERBOLA #" + number);
      target.write(System.lineSeparator());
      target.write("EQUATION: " + "(x-" + xshift + ")^2/" + temp1 + "^2-(y-" + yshift + ")^2/" + temp2 + "=1");
      
      //Find the positive range.
      temp4 = (int)(yshift+(Math.sqrt((1-(Math.pow(temp3, 2)/Math.pow(temp2, 2)))*(-Math.pow(temp1, 2)))));
      
      //Remember in interval notation smallest first biggest last.
      target.write(System.lineSeparator());   
      target.write("DOMAIN: [" + (-temp3) + "," + ((-temp1)-xshift) + "]" + " U " + "[" + (temp1-xshift) + "," + (temp3) + "]");
      target.write(System.lineSeparator());   
      target.write("RANGE: [" + (-temp4) + "," + ((-temp2)-yshift) + "]" + " U " + "[" + (temp2-yshift) + "," + (temp4) + "]");
      
      target.flush();
    }

    target.close();
  }
}