public class CSCI_271_Garrett_Nix_Assignment_3_Main{
    public static void main(String[] args){
        try{
            String numStringIn = args[0]; // first get string of numbers, used for parsing each character.
            int searchNum = Integer.parseInt(args[1]);
            System.out.println(numStringIn);
            System.out.println(searchNum);
            NumberCounter result = new NumberCounter(numStringIn, searchNum);
            result.printResult();
        } catch (ArrayIndexOutOfBoundsException e){
            System.err.println("error: please provide a string of numbers followed by a number to search the string for.");
        }
    }
}
