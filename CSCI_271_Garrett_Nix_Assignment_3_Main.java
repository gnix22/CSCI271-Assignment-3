public class CSCI_271_Garrett_Nix_Assignment_3_Main{
    public static void main(String[] args){
        try{
            String stringIn = args[0];
            System.out.println("select an option:");
            System.out.println("    character related options:");
            System.out.println("        1: Count the number of elements in the string.");
            System.out.println("        2: Count number of times search element occurs.");
            if(args[2].charAt(0) == 'i'){
                int searchNum = Integer.parseInt(args[1]);
                Counter result = new Counter(stringIn, searchNum);
                result.printResult();
            } else if(args[2].charAt(0) == 'c'){
                char searchChar = args[1].charAt(0);
                Counter result = new Counter(stringIn, searchChar);
                result.printResult();
            } else System.err.println("please ensure you provide either an int OR char argument.");
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("error: please provide a string of numbers followed by a number to search the string for.");
        }
    }
}
