class NumberCounter{
    // Possible avenues to try:
    // - set an array that puts each indivdual character in that array, recursively count through, matching to a search number.
    private int[] numArray_;
    private int searchNum_;
    private int searchNumCount_ = 0;
    // initializer
    public NumberCounter(int[] numArrayIn, int searchNumIn){
        numArray_ = numArrayIn;
        searchNum_ = searchNumIn;
        searchNumCount_ = recurserCounter_(numArray_.length, 0);
    }

    private int recurserCounter_(int searchSize, int i) {
        if (searchSize == 0) {
            return 0;
        }

        if (searchNum_ == numArray_[i]) {
            return 1 + recurserCounter_(searchSize - 1, i - 1);
        }
        return recurserCounter_(searchSize - 1, i - 1);
    }
}
