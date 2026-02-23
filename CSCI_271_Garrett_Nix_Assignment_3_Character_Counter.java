class Counter{
    private String stringIn_;
    private long[] numArray_;
    private char[] charArray_;
    private char[] charOutArray_;
    private String stringOut_;
    private int searchNum_;
    private int searchNumCount_;
    private char searchChar_;
    private boolean isInt_;
    private long max_ = 0;
    private long evenSum_ = 0;
    // initializer
    public Counter(String strIn, int searchNumIn, boolean isInt){
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        charOutArray_ = new char[stringIn_.length()];
        numArray_ = new long[stringIn_.length()];
        numArray_ = strToNumArray(0);
        searchNum_ = searchNumIn;
        if(isInt){
            isInt_ = true;
        } else isInt_ = false;
        searchNumCount_ = recurserCounter_(numArray_.length-1, 0);
        stringOut_ = reverseOutput(numArray_.length - 1);
        max(numArray_.length-1);
        sumEven(numArray_.length-1);
    }
    public Counter(String strIn, char searchCharIn){
        isInt_ = false; // assume that if isInt isn't passed, it is false 
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        charOutArray_ = new char[stringIn_.length()];
        searchChar_ = searchCharIn;
        searchNumCount_ = recurserCounter_(charArray_.length-1, 0);
        stringOut_ = reverseOutput(charArray_.length - 1);
    }
    public void printResult(int choice){
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
                System.out.println("the length of the string is: " + lengthReader(stringIn_.length() - 1));
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
    private int lengthReader(int i){
        if(i < 0){
            return 0;
        }
        if(stringIn_.charAt(i) != ' '){
            return 1 + lengthReader(i-1);
        }
        return lengthReader(i-1);
    }
    private int recurserCounter_(int searchSize, int i) {
        if (searchSize < 0) { // base case
            return 0;
        }
        // if the number is an integer, and the number matches the search element, increment, otherwise it is char
        // and same rule applies.
        if (isInt_ && searchNum_ == numArray_[i]) { // shortcircuit logic error fix
            isInt_ = true; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1); // fun criss cross increments.
        } else if(searchChar_ == charArray_[i] && !isInt_){
            isInt_ = false; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1);
        }
        return recurserCounter_(searchSize - 1, i + 1);
    }
    private long max(int i){
        if (i <= 0){ // base case
            return 0;
        }
        // if the the previous number (the one searched next) is less than the maximum currently,
        // assign that value to the maximum
        if(numArray_[i-1] > max_){
            max_ = numArray_[i-1];
        }
        return max(i - 1);
    }
    private String reverseOutput(int i){
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
        return reverseOutput(i-1);
    }
    private long sumEven(int i){
        if(i < 0){
            return 0;
        }
        if(numArray_[i] % 2 == 0){
            evenSum_ += numArray_[i] + sumEven(i-1);   
        }
        return sumEven(i - 1);
    }
    // HELPER FUNCTIONS
    private long[] strToNumArray(int i){
        if (i >= stringIn_.length()){
            return numArray_;
        } 
        numArray_[i] = charArray_[i] - '0';
        return strToNumArray(i + 1);
    }
}