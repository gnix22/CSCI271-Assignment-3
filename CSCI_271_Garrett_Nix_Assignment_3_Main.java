import java.util.Scanner;
public class CSCI_271_Garrett_Nix_Assignment_3_Main{
    
    public static void main(String[] args){
        Scanner userIn = new Scanner(System.in);
        try{
            String stringIn = args[0];
            System.out.println("select an option:");
            System.out.println("    1: output the length of the input string");
            System.out.println("    2: print the number of times the search element occurs");
            System.out.println("    3: output the input string backwards");
            System.out.println("    4: output the maximum of the list of numbers");
            System.out.println("    5: sum all even elements, output the result");
            int choice = userIn.nextInt();
            // System.out.println(args[0] + " " + args[1] + " " + " " + args[2]); testing
            if(args[2].charAt(0) == 'i'){
                int searchNum = Integer.parseInt(args[1]);
                Counter result = new Counter(stringIn, searchNum, true);
                result.printResult(choice);
            } else if(args[2].charAt(0) == 'c'){
                char searchChar = args[1].charAt(0);
                Counter result = new Counter(stringIn, searchChar);
                result.printResult(choice);
            } else System.err.println("please ensure you provide either an int OR char argument.");
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            System.err.println("error: please provide a string of numbers followed by a number to search the string for.");
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        }
    }
}
