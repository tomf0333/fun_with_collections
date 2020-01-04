/**
 * a class that represents a Closed hashing handling hash set, it has an array of SuperStrings (like normal
 * strings except they have a member that says if they were deleted for the purpose of this class),
 * when we want to add Strings in this class we hash them with quadratic equation and then insert them.
 * there is a probe for inserting and a probe for searching that know to search with the algorithm specified.
 */
public class ClosedHashSet extends SimpleHashSet {

    private SuperString[] array = new SuperString[SimpleHashSet.INITIAL_CAPACITY];

    private static final char UP = 'u';

    private static final char DOWN = 'd';

    private int size;

    /**
     * a function that initializes all the SuperStrings in the array so they wont be null pointers;
     */
    private void initializeArray(){
        /* the "size" of and empty array is 0 */
        this.size = 0;
        for (int i = 0; i < this.array.length; i++){
            this.array[i] = new SuperString();
        }
    }
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        this.initializeArray();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor   -   The upper load factor of the hash table.
     * @param lowerLoadFactor   -   The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        this.initializeArray();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     *
     * @param data -   Values to add to the set.
     */
    public ClosedHashSet(java.lang.String[] data) {
        super();
        this.initializeArray();
        for (String s : data) {
            this.add(s);
        }
    }

    @Override
    public int capacity() {
        return this.array.length;
    }

    @Override
    public boolean add(String newValue) {
        if (this.size() + 1 > this.getUpperLoadFactor() * this.capacity())
            this.resize(ClosedHashSet.UP);
        if (!this.contains(newValue)) {
            int index = this.probeForFreeSpace(newValue);
            this.array[index] = new SuperString(newValue);
            this.size++;
            return true;
        }
        return false;
    }

    /**
     * a quadratic probing function that tries to find a free space in the array for a number of attempts and
     * returns an index for clamping the value we want for it.
     * note that according to the algorithm and the way we make this set we will always find a place in n
     * attempts or less where n is the capacity of the array (so the while isn't forever).
     *
     * @param addValue  -   the String value we want to insert.
     * @return the index that we need to clamp the value to.
     */
    private int probeForFreeSpace(String addValue) {
        int attempt = 0;
        int index = this.clamp(addValue.hashCode());
        while (this.array[index].getValue() != null) {
            attempt += 1;
            index = this.clamp(addValue.hashCode() + ((attempt + attempt * attempt) / 2));
        }
        return index;
    }

    /**
     * a quadratic probing function that searches for the value we want, ignores elements that are none but
     * because they were deleted (with the help of SuperString).
     * note that we don't need to search for more then n attempts where n is the capacity of the array (so
     * the while isn't forever).
     *
     * @param searchValue   -   the String value we want to find.
     * @return the index of the value we searched for if it exists and -1 if it doesn't.
     */
    private int probeForSearch(String searchValue) {
        int attempt = 0;
        int index = this.clamp(searchValue.hashCode());
        while (((this.array[index].getValue() == null) && (this.array[index].isDeleted())) ||
                ((this.array[index].getValue() != null) && (!this.array[index].getValue().equals(searchValue)
                ))) {
            attempt += 1;
            index = this.clamp(searchValue.hashCode() + ((attempt + attempt * attempt) / 2));
        }
        if (this.array[index].getValue() == null)
            return -1;
        return index;
    }

    @Override
    public boolean contains(String searchVal) {
        int index = this.probeForSearch(searchVal);
        return index != -1;
    }

    @Override
    public boolean delete(String toDelete) {
        int index = this.probeForSearch(toDelete);
        if (index != -1){
            this.array[index].setDeleted();
            if (this.size() < this.getLowerLoadFactor() * this.capacity())
                this.resize(ClosedHashSet.DOWN);
            this.size--;
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * a function that resizes the SuperString array either to double or half what it was depends on the
     * input it receives.
     *
     * @param whichWay  -   'u' for up and 'd' for down (up doubles the capacity and down halves it)
     */
    private void resize(char whichWay){
        SuperString[] clone = this.array.clone();
        double resizeValue = 0;
        switch (whichWay){
            case ClosedHashSet.UP:
                resizeValue = 2;
                break;
            case ClosedHashSet.DOWN:
                resizeValue = 0.5;
        }
        this.array = new SuperString[(int)(this.capacity() * resizeValue)];
        this.initializeArray();
        for (SuperString ss : clone){
            if (ss.getValue() != null)
                this.add(ss.getValue());
        }
    }
}
