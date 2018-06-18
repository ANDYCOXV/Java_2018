import java.util.ArrayList;
import java.lang.Math; //Note: Using Math.pow(x,y) does not have an advantage over x*x*x... Because it is compiled down to multiplication.

//NOTE: With circles and ellipses need to check for Not a Number or Nan.
public class PlotPoints
{
  
  private static ArrayList<Integer> coordinatePoints = new ArrayList<>(); //Format: (x,y)
  
  //Plot all points of a horizontal hyperbola.
  public static void horizontalHyperbolaPlot(ArrayList<Integer> hyperbolaPoints) //Format: h,k,a,b,domain
  {
    //y=k+-sqrt((1+(x-h)^2/b^2)*-b^2)
    double y = 0;
    double x = 0;
    double k = 0;
    double h = 0;
    double a = 0;
    double b = 0;
    double aConstant = 0;
    double bConstant = 0;
    double domain = 0;
    int i = 0;
    
    while(i < hyperbolaPoints.size())
    {
      //Set all of the variables.
      h = hyperbolaPoints.get(i);
      i++;
      k = hyperbolaPoints.get(i);
      i++;
      a = hyperbolaPoints.get(i);
      i++;
      b = hyperbolaPoints.get(i);
      i++;
      domain = hyperbolaPoints.get(i);
      i++;
      
      //Precalculate constants.
      aConstant = Math.pow(a,2);
      bConstant = -Math.pow(b,2);
     
      x = a; //Have this to cancle out random points.
      
      while(x <= domain)
      {
        y = Math.sqrt((1-Math.pow(x,2)/aConstant)*bConstant);
        add(x-h);
        add(y-k);
        add((x*-1)-h);
        add((y*-1)-k);
        add(x-h);
        add((y*-1)-k);
        add((x*-1)-h);
        add(y-k);
        x++;
      }
    }
  }
  
  //Plot all points of a vertical hyperbola.
  public static void verticalHyperbolaPlot(ArrayList<Integer> hyperbolaPoints) //Format: h,k,a,b,range
  {
    //y=k+-a*sqrt((1+(x-h)^2/b^2)*a)
    double y = 0;
    double x = 0;
    double k = 0;
    double h = 0;
    double a = 0;
    double b = 0;
    double aConstant = 0;
    double bConstant = 0;
    double range = 0;
    int i = 0;
    
    while(i < hyperbolaPoints.size())
    {
      //Set all of the variables.
      h = hyperbolaPoints.get(i);
      i++;
      k = hyperbolaPoints.get(i);
      i++;
      a = hyperbolaPoints.get(i);
      i++;
      b = hyperbolaPoints.get(i);
      i++;
      range = hyperbolaPoints.get(i);
      i++;
      
      //Precalculate constants.
      aConstant = Math.pow(a,2);
      bConstant = -Math.pow(b,2);
      
      //System.out.println("y=" + k + "+-" + a + "*sqrt((1+(x-" + h + ")^2/" + bConstant + ")*" + a + ")");

      y = a;
      
      //NOTE: y and x values are swapped from the horizontal hyperbola.
      while(y <= range)
      {
        x = Math.sqrt((1-Math.pow(y,2)/aConstant)*bConstant);
        add(x-h);
        add(y-k);
        add((x*-1)-h);
        add((y*-1)-k);
        add(x-h);
        add((y*-1)-k);
        add((x*-1)-h);
        add(y-k);
        y++;
      }
    }
  }
  
  //Plot all points of a ellipse.
  //NOTE: a>b horizontal axis, a<b vertical axis, a=b circle
  public static void ellipsePlot(ArrayList<Integer> ellipsePoints) //Format: h,k,a,b
  {
    //y = k+-b*sqrt((1-(x-h)^2/a^2)*b)
    double y = 0;
    double x = 0;
    double k = 0;
    double h = 0;
    double a = 0;
    double b = 0;
    double aConstant = 0;
    double bConstant = 0;
    int i = 0;
    
    while(i < ellipsePoints.size())
    {
      //Set all of the variables.
      h = ellipsePoints.get(i);
      i++;
      k = ellipsePoints.get(i);
      i++;
      a = ellipsePoints.get(i);
      i++;
      b = ellipsePoints.get(i);
      i++;
      
      //System.out.println("y=" + k + "+-" + b + "*sqrt((1-(x-" + h + ")^2/" + a + "^2)" + b + ")");
      
      //Compute constants.
      aConstant = Math.pow(a,2);
      bConstant = Math.pow(b,2);
      
      //Fixes a user error.
      if(a >= b)
        x = -aConstant;
      else
        x = -bConstant;
      
      while(((x <= aConstant)&&(a >= b))||((x <= bConstant)&&(a <= b))) //Calculate positive section.
      {
        y = b*Math.sqrt((1-(Math.pow(x,2)/aConstant))*b);
        if(!Double.isNaN(y))
        {
          add(x-h);
          add(y-k);
          add((x*-1)-h); //Ellipses also have symmetry so exploit it.
          add((y*-1)-k);
        }
        x++;
      }
    }
  }
  
  //Plot all points of a horizontal axis parabolas.
  public static void horizontalParabolaPlot(ArrayList<Integer> parabolaPoints) //Format: h,k,p,domain
  {
    //y = k+-sqrt(4p(x-h))
    double y = 0;
    double x = 0;
    double k = 0;
    double h = 0;
    double p = 0;
    double domain = 0; //Named domain because the line is on the x-axis. Use this instead of listing two points.
    double pConstant = 0;
    int i = 0;
    
    while(i < parabolaPoints.size())
    {
      //Setup variables.
      h = parabolaPoints.get(i);
      i++;
      k = parabolaPoints.get(i);
      i++;
      p = parabolaPoints.get(i);
      i++;
      domain = parabolaPoints.get(i);
      i++;
      x = 0;
      
      //Check for same sign to prevent infinity errors.
      if(((int)p >>> 31) == ((int)domain >>> 31))
      {
        
        //System.out.println("y=" + k + "+-sqrt(4*" + p + "(x-" + h + "))");
        
        //Do constants so they dont have to be recaclulated.
        pConstant = 4*p;
        
        //NOTE: Shifts are preformed after the calculation vertex is always at (0,0).
        while((x <= domain)&&(p >= 0)||(x >= domain)&&(p < 0))
        {
          y = Math.sqrt(pConstant*x);
          add(x-h);
          add(y-k);
          add(x-h);
          y = -Math.sqrt(pConstant*x);
          add(y-k);
          
          if(p < 0)
            x--;
          else
            x++;
        }
      }
    }
  }
  
  //Plot all points of vertical axis parabolas.
  public static void verticalParabolaPlot(ArrayList<Integer> parabolaPoints) //Format: h,k,p,range
  {
    //y = k + (x-h)^2/4p
    double y = 0;
    double x = 0;
    double k = 0;
    double h = 0;
    double p = 0;
    double range = 0; //Named range because the line is on the y-axis. Use this instead of listing two points.
    double pConstant = 0;
    int i = 0;
    
    while(i < parabolaPoints.size())
    { 
      //Setup variables.
      h = parabolaPoints.get(i);
      i++;
      k = parabolaPoints.get(i);
      i++;
      p = parabolaPoints.get(i);
      i++;
      range = parabolaPoints.get(i);
      i++;
      y = 0;
      
      //Check to see if the signs are the same use to prevent infinity errors.
      if(((int)p >>> 31) == ((int)range >>> 31))
      {
        //System.out.println("y=" + k + "+((x-" + h + ")^2)/(4*" + p + ")");
        
        //Calculate all of the constants.
        pConstant = 4*p;
        
        //REMEMBER: P can be positive or negative. If positive then opens up else down.
        //Symmetry could be exploted by irritating with y and solving for x.
        //Start at k and work down or up to range.
        while((y <= range)&&(p >= 0)||(y >= range)&&(p < 0))
        {
          x = Math.sqrt(pConstant*y);
          add(x-h);
          add(y-k);
          add((x*-1)-h);
          add(y-k);
          
          if(p < 0)
            y--;
          else
            y++;
        }
      }
    }
  }
  
  //Plot all points of circles.
  public static void circlePlot(ArrayList<Integer> circlePoints) //Format: h,k,r
  {
    //y = k +- sqrt(r^2 - (x - h)^2)
    double y = 0;
    double x = 0;
    double h = 0;
    double k = 0;
    double r = 0;
    double rSquared = 0;
    int i = 0;
    
    while(i < circlePoints.size())
    {
      //Setup vairables.
      h = circlePoints.get(i);
      i++;
      k = circlePoints.get(i);
      i++;
      r = circlePoints.get(i);
      i++;
      x = 0;
      rSquared = 0;
      
      //System.out.println(r + "^2=(x-" + h + ")^2+(y-" + k + ")^2");
      
      //Precaclulate values so computer does not have to continually recalculate.
      rSquared = Math.pow(r,2); //Calculate the value of r^2.
      
      //Calculate circle.
      for(x = -rSquared ; x <= rSquared ; x++)
      {
        y = Math.sqrt(rSquared-Math.pow(x,2));
        if(!Double.isNaN(y))
        {
          add(x-h);
          add(y-k);
          add((x*-1)-h); //Circles have symmetry so here it is exploited.
          add((y*-1)-k);
        }
      }
    }
  }
  
  //Plots all of the points for the line segments.
  public static void lineSegmentPlot(ArrayList<Integer> lineSegmentPoints) //Format: x1,y1,x2,y2
  {
    //y = mx + b
    double y = 0;
    double x = 0;
    double m = 0;
    double b = 0;
    double x1 = 0;
    double x2 = 0;
    double y1 = 0;
    double y2 = 0;
    int i = 0;
    
    while(i < lineSegmentPoints.size())
    {
      //Setup variables.
      x1 = lineSegmentPoints.get(i);
      i++;
      y1 = lineSegmentPoints.get(i);
      i++;
      x2 = lineSegmentPoints.get(i);
      i++;
      y2 = lineSegmentPoints.get(i);
      i++;
      
      //Calculate all of the constants.
      if(((int)x2-(int)x1) != 0)
        m = (y2-y1)/(x2-x1); //Slope.

      b = y1-(m*x1); //Y-Axis shift
      
      //System.out.println("y="+m+"x+"+b);
      
      //Checks to see if the line has an infinate slope which is Not a Number Nan.
      if(((int)x2-(int)x1) != 0)
      {
         //Set x do this because user may not have to enter in points from least point to greatest point.
         x = x1;
         while((x <= x2)&&(x1 <= x2)||(x >= x2)&&(x1 >= x2))
         {
           add(x);
           y = (m*x)+b;
           add(y);

           if(x1 < x2)
             x++;
           else
             x--;
         }
       }
       else //The slope is infinate.
       {
         while(y1 != y2)
         {
           add(x1);
           add(y1);

           if(y1 < y2)
             y1++;
           else
             y1--;
        }
       add(x1); //Needed for inclusivness.
       add(y2);
     }
   }
  }
  
  //Simply load the points into the coordinatePoints ArrayList.
  public static void pointPlot(ArrayList<Integer> pointPoints)
  {
    for(int x : pointPoints)
      add(x);
  }
  
  //Adds points to the coordinatePoints integer array.
  public static void add(double x)
  {
    coordinatePoints.add((int)x);
  }
  
  //Return point at location.
  public static double aquire(int x)
  {
    return coordinatePoints.get(x);
  }
  
  //Returns an arraylist of the locations of the points on the graph in coordinate form.
  public static ArrayList<Integer> coordinatePlot()
  {
    return coordinatePoints;
  }
  
}