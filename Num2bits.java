/*
Author: Andy Cox V
Date: 6/18/2016
Program: Num2bits
Language: Java
Description:
        This program is used to create the bit patter to match a desired unit.
*/

import java.util.*;

// I had to use int instead of long because .add cannot use long.
class Num2bits
{
        
        public static Scanner kbInput = new Scanner(System.in);
        
        public static int getnum(String question)
        {
                int getnumber = 0;
                boolean loop = true;
                
                do
                {
                        try
                        {
                                System.out.print(question);
                                getnumber = Integer.parseInt(kbInput.next());
                                
                                loop = getnumber < 0;
                                
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

                int user = 0;
                String userString = "";
                
                ArrayList<Integer> bits = new ArrayList<Integer>();
                ArrayList<Integer> value = new ArrayList<Integer>();
        
                System.out.println("Andy Cox V - 6/18/2016\n" +
                                "This program will create the desired bit patter to reach a user defined unit.");
                                
                user = getnum("\nInput the positive integer to convert: ");
                
                userString = Integer.toBinaryString(user);
                
                for(int i = 0 ; i < userString.length() ; i++)
                        if(userString.charAt(i) == '1')
                        {
                                bits.add(i);
                                value.add((int)Math.pow(2, i));
                        }
                
                
                System.out.println("\nThe integer " + user + " is equivlant to the " + userString.length() + " bit pattern:\n" + userString +
                                        "\n\nA total of " + value.size() + " the following values will be used:\n" + value + "\n\nA total of " + bits.size() + " the follwing bits will be on:\n" + bits);
        
        }

}