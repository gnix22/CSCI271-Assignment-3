class Counter{
    // Possible avenues to try:
    // - set an array that puts each indivdual character in that array, recursively count through, matching to a search number.
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
    public int lengthReader(int i){
        if(i < 0){
            return 0;
        }
        if(stringIn_.charAt(i) != ' '){
            return 1 + lengthReader(i-1);
        }
        return lengthReader(i-1);
    }
    public void printResult(int choice){
        if(choice >= 4){
            if(!isInt_){
                System.err.println("error: options 4,5 limited to integers");
                return;
            }
            switch (choice - 3) {
                case 1:
                    System.out.println("the max of the string is " + max_);
                    break;
                case 2:
                    System.out.println("the sum of even elements is ");
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
                System.err.println("invalid option.");
        }
    }
    public int recurserCounter_(int searchSize, int i) {
        if (searchSize < 0) {
            return 0;
        }
        if (isInt_ && searchNum_ == numArray_[i]) { // shortcircuit logic error fix
            isInt_ = true; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1);
        } else if(searchChar_ == charArray_[i] && !isInt_){
            isInt_ = false; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1);
        }
        return recurserCounter_(searchSize - 1, i + 1);
    }
    public long max(int i){
        if (i <= 0){
            return 0;
        }
        if(numArray_[i-1] > max_){
            max_ = numArray_[i-1];
        }
        return max(i - 1);
    }
    public String reverseOutput(int i){
        if(i < 0){
            return new String(charOutArray_);
        }
        if(isInt_){
            charOutArray_[i] = (char) ('0' + numArray_[numArray_.length-1-i]); // '0' to get back what we subtracted to get numArray.
        } else {
            charOutArray_[i] = charArray_[charArray_.length-1-i];
        }
        return reverseOutput(i-1);
    }
    public long sumEven(int i){
        if(i < 0){
            return 0;
        }
        if(numArray_[i] % 2 == 0){
            return numArray_[i] + sumEven(i-1);   
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