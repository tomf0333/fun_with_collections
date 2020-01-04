/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet extends Object implements SimpleSet {

    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;

    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;

    protected static final int INITIAL_CAPACITY = 16;

    private float upperLoadFactor;

    private float lowerLoadFactor;

    /**
     * Constructs a new hash set with the default capacities given in DEFAULT_LOWER_CAPACITY and
     * DEFAULT_HIGHER_CAPACITY
     */
    protected SimpleHashSet(){
        this.upperLoadFactor = SimpleHashSet.DEFAULT_HIGHER_CAPACITY;
        this.lowerLoadFactor = SimpleHashSet.DEFAULT_LOWER_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     *
     * @param upperLoadFactor   -   the upper load factor before rehashing.
     * @param lowerLoadFactor   -   the lower load factor before rehashing.
     */
    public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor){
        this.upperLoadFactor = upperLoadFactor;
        this.lowerLoadFactor = lowerLoadFactor;
    }

    /**
     *
     * @return The current capacity (number of cells) of the table.
     */
    public abstract int capacity();

    /**
     * Clamps hashing indices to fit within the current table capacity.
     *
     * @param index -   the index before clamping
     * @return  an index properly clamped
     */
    protected int clamp(int index){
        return index & (this.capacity() - 1);
    }

    /**
     * @return  the upper load factor of the table.
     */
    protected float getUpperLoadFactor(){
        return this.upperLoadFactor;
    }

    /**
     * @return  the lower load factor of the table.
     */
    protected float getLowerLoadFactor(){
        return this.lowerLoadFactor;
    }
}
