class Counter{
    private String stringIn_; // input string from cmdline
    private long[] numArray_; // array of numbers obtained from strToNumArray_
    private char[] charArray_; // array of characters
    private char[] charOutArray_; // output array
    private String stringOut_; // output string
    private int searchNum_; // element to search through the arrays
    private int searchNumCount_; // count used for computing num times search element happens
    private char searchChar_; // same purpose as searchNum_
    private boolean isInt_; // used in testing integer vs char cases.
    private long max_ = 0; // used to hold maximum number of elements
    private long evenSum_ = 0; // used to hold sum of even numbers
    // initializer
    /******************************<Counter>*****************************
    * Description: constructor method for numbers 
    *
    * Parameters: 
    *   strIn: string taken from cmdline
    *   searchNumIn: integer that becomes the element to search for.
    *   isInt: boolean to check whether an element is an integer, used for
    *   edge cases.
    *
    * Pre: a string of integers, must be passed in along with a search element.
    * The boolean must also be true in order for this method to construct the 
    * class object.
    *
    * Post: a constructed object of type Counter will be created, allowing for
    * integer methods.
    *
    * Returns: none
    *
    * Called by: main()
    * Calls: strToNumArray_(), recurserCounter_(), reverseOutput_(), max_(),
    * sumEven_();
    ************************************************************************/
    public Counter(String strIn, int searchNumIn, boolean isInt){
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        charOutArray_ = new char[stringIn_.length()];
        numArray_ = new long[stringIn_.length()];
        numArray_ = strToNumArray_(0);
        searchNum_ = searchNumIn;
        if(!isInt){
            isInt_ = false;
            System.err.println("please ensure string is an integer to use integer searching.");
            return;
        }
        searchNumCount_ = recurserCounter_(numArray_.length-1, 0);
        stringOut_ = reverseOutput_(numArray_.length - 1);
        max_(numArray_.length-1);
        sumEven_(numArray_.length-1);
    }
    /******************************<Counter>*****************************
    * Description: constructor method for characters
    *
    * Parameters: 
    *   strIn: string taken from cmdline
    *
    * Pre: a string must be given along with a character search element. 
    *   the passed arguments are assumed only to be char.
    * Post: an object of type counter is constructed.
    *
    * Returns: none 
    *
    * Called by: main()
    * Calls: recurserCounter_(), reverseOutput_() 
    ************************************************************************/
    public Counter(String strIn, char searchCharIn){
        isInt_ = false; // assume that if isInt isn't passed, it is false 
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        charOutArray_ = new char[stringIn_.length()]; // set a new character array to pre initialize output array
        searchChar_ = searchCharIn;
        searchNumCount_ = recurserCounter_(charArray_.length-1, 0);
        stringOut_ = reverseOutput_(charArray_.length - 1);
    }
    /******************************<printResult>*****************************
    * Description: prints the result of various methods, given a choice input.
    *
    * Parameters: 
    *   choice: integer that is used for CLI implementation.
    *
    * Pre: a choice must be made by the user, and every method must be possible
    * to compute in order for this method to be called.
    *
    * Post: the result of the specified choice will be given on the terminal. 
    *
    * Returns: void (none) 
    *
    * Called by: main() 
    * Calls: lengthReader_()
    ************************************************************************/
    public void printResult(int choice){ // has constant runtime complexity, that is o(1)
        if(choice >= 4){
            if(!isInt_){
                System.err.println("error: options 4,5 limited to integers");
                return;
            }
            // in cli, to be able to keep continutity, 4 and 5 are options, but as far as I know, cases have to start
            // at 1?
            switch (choice - 3) {
                case 1:
                    System.out.println("the max of the string is " + max_);
                    break;
                case 2:
                    System.out.println("the sum of even elements is " + evenSum_);
                    break;
                default:
                    throw new IllegalArgumentException("operations not available for characters");
            }
        }
        switch (choice) {
            case 1:
                System.out.println("the length of the string is: " + lengthReader_(stringIn_.length() - 1));
                break;
            case 2:
                System.out.println("the number of times the search element occurs is " + searchNumCount_);
                break;
            case 3:
                System.out.println("the backwards output of the string is: " + stringOut_);
                break;
            default:
                if(choice > 5 || choice < 1){
                System.err.println("invalid option."); 
                }
                break;
        }
    }
    /******************************<lengthReader_()>*****************************
    * Description: recursivel counts the length of a given string. 
    *
    * Parameters: 
    *   i: integer that marks index of recursion
    *
    * Pre: an integer of i must be input set greater than zero. 
    *
    * Post: the length of the string this method is called on will give the length
    * of the string.
    *
    * Returns: integer of string count. 
    *
    * Called by: Counter(), itself
    * Calls: itself 
    ************************************************************************/
    private int lengthReader_(int i){ // O(n): 2c+2n = O(n)
        if(i < 0){ // constant
            return 0;
        }
        if(stringIn_.charAt(i) != ' '){ // constant
            return 1 + lengthReader_(i-1); // linear
        }
        return lengthReader_(i-1); // linear
    }
    /******************************<recurserCounter_()>*****************************
    * Description: recursively counts the number of times the search element is matched. 
    *
    * Parameters: 
    *   searchSize: integer of the length of the string.
    *   i: recursive index
    *
    * Pre: a valid object must be constructed, and a choice must be made ( see printResult ) 
    *
    * Post: the number of times the element occurs within a given string will show. 
    *
    * Returns: integer denoting the number of times the search element occurs.
    *
    * Called by: itself 
    * Calls: Counter(), itself 
    ************************************************************************/
    private int recurserCounter_(int searchSize, int i) { // O(n^2): 2c+3n^2=O(n^2)
        if (searchSize < 0) { // base case
            return 0; // constant
        }
        // if the number is an integer, and the number matches the search element, increment, otherwise it is char
        // and same rule applies.
        if (isInt_ && searchNum_ == numArray_[i]) { // shortcircuit logic error fix
            isInt_ = true; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1); // fun criss cross increments. // each increment, adjusts element twice
        } else if(searchChar_ == charArray_[i] && !isInt_){
            isInt_ = false; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1); // adjusts element twice
        }
        return recurserCounter_(searchSize - 1, i + 1); // adjusts elements twice
    }
    /******************************<max_()>*****************************
    * Description: recursively provides the maximum of integer digits within the string 
    *
    * Parameters: i: recursive index 
    *
    * Pre: a digit string must be provided by the user in order to call this
    * method.
    *
    * Post: a maximum of the digits will be found 
    *
    * Returns: long integer maximum 
    *
    * Called by: Counter(), itself 
    * Calls: itself 
    ************************************************************************/
    private long max_(int i){ // O(n) : c+n=O(n)
        if (i <= 0){ // base case
            return 0;
        }
        // if the the previous number (the one searched next) is less than the maximum currently,
        // assign that value to the maximum
        if(numArray_[i-1] > max_){
            max_ = numArray_[i-1];
        }
        return max_(i - 1);
    }
    /******************************<reverseOutput_>*****************************
    * Description: reverses the input string recursively
    *
    * Parameters: 
    *   i: recursion index
    *
    * Pre: a string of either int or char must be provided
    *
    * Post: the input string will be reversed and output to the terminal.
    *
    * Returns: an output string in reverse order from the input
    *
    * Called by: itself, Counter 
    * Calls: itself 
    ************************************************************************/
    private String reverseOutput_(int i){ // O(n) : c+n = o(n)
        // the way in which I have this laid out is purely for robustness in being able to recycle functions for further use
        // if we have an int, type cast the numArray to char, that is then passed into the return string when base case is hit.
        if(i <= 0){ // base case
            return new String(charOutArray_);
        }
        if(isInt_){
            charOutArray_[i] = (char) ('0' + numArray_[numArray_.length-1-i]); // '0' to get back what we subtracted to get numArray.
        } else {
            charOutArray_[i] = charArray_[charArray_.length-1-i];
        }
        return reverseOutput_(i-1);
    }
    /******************************<sumEven_()>*****************************
    * Description: takes in the string of digits, and sums the even integers 
    *
    * Parameters: 
    *   i: recursive index
    *
    * Pre: a string of digits must be provided in order for the sum of even integers 
    * to be determined.
    *
    * Post: list the postconditions 
    *
    * Returns: returns the sum of even integers 
    *
    * Called by: itself, Counter()
    * Calls: itself 
    ************************************************************************/
    private long sumEven_(int i){ // O(n) = c+n = o(n)
        if(i < 0){
            return 0;
        }
        if(numArray_[i] % 2 == 0){
            evenSum_ += numArray_[i] + sumEven_(i-1);
        }
        return sumEven_(i - 1);
    }
    // HELPER FUNCTIONS
    /******************************<strToNumArray_()>*****************************
    * Description: turns a string into a number recursively 
    *
    * Parameters:
    *   i: recursive index
    *
    * Pre: a string of characters is provided 
    *
    * Post: a converted array from char to int is formed 
    *
    * Returns: describe what value the function returns 
    *
    * Called by: itself, Counter()
    * Calls: itself 
    ************************************************************************/
    private long[] strToNumArray_(int i){ // O(n)
        if (i >= stringIn_.length()){
            return numArray_;
        } 
        numArray_[i] = charArray_[i] - '0';
        return strToNumArray_(i + 1);
    }
}
