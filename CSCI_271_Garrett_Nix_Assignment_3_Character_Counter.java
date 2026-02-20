class Counter{
    // Possible avenues to try:
    // - set an array that puts each indivdual character in that array, recursively count through, matching to a search number.
    private String stringIn_;
    private long[] numArray_;
    private char[] charArray_;
    private int searchNum_;
    private int searchNumCount_;
    private char searchChar_;
    // initializer
    public Counter(String strIn, int searchNumIn){
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        numArray_ = new long[stringIn_.length()];
        numArray_ = strToNumArray(0);
        searchNum_ = searchNumIn;
        searchNumCount_ = recurserCounter_(numArray_.length-1, 0, true);
    }
    public Counter(String strIn, char searchCharIn){
        stringIn_ = strIn;
        charArray_ = stringIn_.toCharArray();
        searchChar_ = searchCharIn;
        searchNumCount_ = recurserCounter_(charArray_.length-1, 0, false);
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
    public void printResult(int choice, boolean isInt){
        if(isInt){
            switch (choice - 2) {
                case 1:
                    System.out.println("the max of the string is " + max(numArray_.length));
                    break;
                case 2:
                    System.out.println("the number of times " + searchNum_ + "happens is " + searchNumCount_ + "times");
                default:
                    break;
            }
        }
    }
    public int recurserCounter_(int searchSize, int i, boolean isInt) {
        if (searchSize < 0) {
            return 0;
        }
        if (isInt && searchNum_ == numArray_[i]) { // shortcircuit logic error fix
            isInt = true; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1, isInt);
        } else if(searchChar_ == charArray_[i] && !isInt){
            isInt = false; // likely unneeded, but for insurance.
            return 1 + recurserCounter_(searchSize - 1, i + 1, isInt);
        }
        return recurserCounter_(searchSize - 1, i + 1, isInt);
    }
    public long max(int i){
        if (i < 0){
            return 0;
        }
        if(numArray_[i-1] > max(i)){
            long max = numArray_[i-1];
            return max;
        }
        return max(i - 1);
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
