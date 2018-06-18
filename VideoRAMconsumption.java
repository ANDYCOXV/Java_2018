/*
Author: Andy Cox V
Date: 6/4/2016
Program: VideoRAMconsumption
Language: Java
Description:
        This program is used to calculate the amount of RAM
a certian display size and color depth would consume.
*/

import java.util.Scanner;

class VideoRAMconsumption
{
        
        public static Scanner kbInput = new Scanner(System.in);
        
        public static long getnum(String question)
        {
                long getnumber = 0;
                boolean loop = true;
                
                do
                {
                        try
                        {
                                System.out.print(question);
                                getnumber = Integer.parseInt(kbInput.next());
                                loop = false;
                        }catch(NumberFormatException e)
                        {
                                System.out.println("\nInvalid integer!");
                                loop = true;
                        }
                }while(loop == true);
                
                return getnumber;
        }
        
        public static void main(String[] args)
        {       
                long vert = 0;
                long horz = 0;
                long ramuse = 0;
                long ram = 1;
                long databuss = 0;
                long depth = 0;
                long ramarea = 0;
                
                System.out.println("Andy Cox V - 6/4/2016\n" +
                                "This program is used to calculate the amount of RAM a certian display size and color depth would consume.\n");
                        vert = getnum("Input vertical pixel size: ");
                        
                        horz = getnum("\nInput horizontal pixel size: ");
                        
                        depth = getnum("\nInput color/bit depth: ");
                        
                        databuss = getnum("\nInput the size of the databuss: ");
                        
                ramuse = ((vert * horz) * depth);
                        
                while((ram * databuss) < ramuse)
                        ram = ram << 1;
                
                ramarea = ram * databuss;
                        
                System.out.println("\nConfiguration parameters:" + 
                                   "\n\n\t* Vertical pixel size: " + vert +
                                   "\n\t* Horizontal pixel size: " + horz +
                                   "\n\t* Color/bit depth: " + depth +
                                   "\n\t* Databuss size: " + databuss +
                                   "\n\nThis configuration would consume " + ramuse + " bits of RAM.\n" +
                                "At least a " + ramarea + " bit (" + ram + " x " + databuss + ") sized RAM IC would be needed.\n" +
                                (ramarea - ramuse) + " bits of RAM would be free.\n");
                                
                System.exit(0);
        }
        
}