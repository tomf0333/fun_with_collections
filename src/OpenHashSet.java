import java.util.LinkedList;

/**
 * a class that represents an open hashing handling hash set,
 * it has an array of buckets (Linked List via wrapper) and each value is added to the bucket that
 * corresponds to its hash and bitwise modulo.
 */
public class OpenHashSet extends SimpleHashSet {

    private int capacity = SimpleHashSet.INITIAL_CAPACITY;

    private SpecificFacadeSet[] buckets;

    private int size;

    /**
     * makes the buckets with the initial capacity.
     */
    private void makeBuckets(){
        /* the "size" of all the empty buckets is 0 */
        this.size = 0;
        this.buckets = new SpecificFacadeSet[this.capacity];
        for (int i = 0; i < this.buckets.length; i++){
            LinkedList<String> ll = new LinkedList<String>();
            this.buckets[i] = new SpecificFacadeSet(ll);
        }
    }
    /**
     * A default constructor. Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet(){
        super();
        this.makeBuckets();
    }

    /**
     * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
     *
     * @param upperLoadFactor   -   The upper load factor of the hash table.
     * @param lowerLoadFactor   -   The lower load factor of the hash table.
     */
    public OpenHashSet(float upperLoadFactor, float lowerLoadFactor){
        super(upperLoadFactor, lowerLoadFactor);
        this.makeBuckets();
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one. Duplicate values should be
     * ignored. The new table has the default values of initial capacity (16), upper load factor (0.75),
     * and lower load factor (0.25).
     *
     * @param data  -   Values to add to the set.
     */
    public OpenHashSet(java.lang.String[] data){
        super();
        this.makeBuckets();
        for (String s : data){
            this.add(s);
        }
    }

    @Override
    public int capacity() {
        return this.capacity;
    }

    @Override
    public boolean add(String newValue) {
        if (this.size() + 1 > this.getUpperLoadFactor() * this.capacity()) {
            this.capacity = this.capacity() * 2;
            this.resize();
        }
        if (!this.contains(newValue)){
            int index = clamp(newValue.hashCode());
            this.buckets[index].add(newValue);
            this.size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(String searchVal) {
        int index = this.clamp(searchVal.hashCode());
        return this.buckets[index].contains(searchVal);
    }

    @Override
    public boolean delete(String toDelete) {
        int index = this.clamp(toDelete.hashCode());
        if (this.buckets[index].contains(toDelete)) {
            this.buckets[index].delete(toDelete);
            if (this.size() < this.getLowerLoadFactor() * this.capacity()) {
                this.capacity = (int)(this.capacity() * 0.5);
                this.resize();
            }
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
     * resizes the Linked List Array (in the event we went over or under the load factor)
     */
    private void resize(){
        SpecificFacadeSet[] clone = this.buckets.clone();
        /* make buckets creates according to the new capacity */
        this.makeBuckets();
        for (SpecificFacadeSet bucket : clone){
            for (Object s : bucket.getCollection()){
                int index = this.clamp(s.hashCode());
                this.buckets[index].add((String)s);
            }
        }
    }
}
