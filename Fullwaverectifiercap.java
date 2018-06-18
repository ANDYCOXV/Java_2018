 /*
Author: Andy Cox V
Date: 6/5/2016
Program: Fullwaverectifiercap
Language: Java
Description:
        This program is used to calculate the recommended minimal capacitor
size to cut down on voltage ripple for a deivce. Voltage ripple is calculated
by (I * t)/C.
        I = Load Current (AMPS)
        t = time for electricity to move through capacitor,
                ex a 60hz = 120hz with full wave rectifier. (HERTZ)
        C = Capacitor value (FARADS).
*/

import java.util.Scanner;

class Fullwaverectifiercap
{

        public static Scanner kbInput = new Scanner(System.in);
        
        public static double getnum(String question)
        {
                double getnumber = 0;
                boolean loop = true;
                
                do
                {
                        try
                        {
                                System.out.print(question);
                                getnumber = Double.parseDouble(kbInput.next());
                                loop = false;
                        }catch(NumberFormatException e)
                        {
                                System.out.println("\nInvalid double!");
                                loop = true;
                        }
                }while(loop == true);
                
                return getnumber;
        }
        
        public static void main(String[] args)
        {
                
                double load = 0;
                double capacitor = 0;
                double time = 0;
                double voltage = 0;
                
                System.out.println("Andy Cox V - 6/10/2016\n" +
                                "This program will calculate the required capacitor for a full wave rectifier\n to bring the ripple to a user defined level.");
                                
                load = getnum("\nInput capacitor load (Amps): ");
                time = getnum("\nInput frequency of AC power line (hertz): ");
                voltage = getnum("\nInput maximum allowed voltage ripple (volts): ");
                
                capacitor = (load / (voltage / ( 1 / (time * 2)))); // If not full wave then do not multiply by 2.
                
                System.out.println("\nA " + capacitor + " farad capacitor is needed (may be in scientific notation).");
                
                if(capacitor < 1)
                        System.out.println("Microfarads: " + (capacitor * 1000000));
                
                System.exit(0);
                                
        }
}


