class NumberCounter{
    // Possible avenues to try:
    // - set an array that puts each indivdual character in that array, recursively count through, matching to a search number.
    private String stringIn_;
    private int[] numArray_;
    private int searchNum_;
    private int searchNumCount_;
    // initializer
    public NumberCounter(String strIn, int searchNumIn){
        stringIn_ = strIn;
        numArray_ = strToNumArray(strIn);
        searchNum_ = searchNumIn;
        searchNumCount_ = recurserCounter_(numArray_.length, 0);
    }
    public void printResult(){
        System.out.println("N= " + stringIn_);
        System.out.println("D= " + searchNum_);
        System.out.println("Answer= " + searchNumCount_);
    }
    private int[] strToNumArray(String s){
        int[] convertedArray = new int[s.length()];
        for(int i = 0; i < s.length(); i++){
            char strElem = s.charAt(i);
            convertedArray[i] = strElem - '0';
        }
        return convertedArray;
    }
    private int recurserCounter_(int searchSize, int i) {
        if (searchSize == 0) {
            return 0;
        }
        if (searchNum_ == numArray_[i]) {
            return 1 + recurserCounter_(searchSize - 1, i + 1);
        }
        return recurserCounter_(searchSize - 1, i + 1);
    }
}
