/**
 * A superclass for implementations of hash-sets implementing the SimpleSet interface.
 */
public abstract class SimpleHashSet implements SimpleSet {
    //static constant/magic numbers
    //Describes the higher load factor of a newly created hash set.
    protected static final float DEFAULT_HIGHER_CAPACITY = 0.75f;
    //Describes the lower load factor of a newly created hash set.
    protected static final float DEFAULT_LOWER_CAPACITY = 0.25f;
    //Describes the capacity of a newly created hash set.
    protected static final int INITIAL_CAPACITY = 16;
    /* -----= fields = -------*/
    //mandatory
    ////Describes the higher load factor of this hash set.
    protected final float higherCap;
    //Describes the lower load factor of this hash set.
    protected final float lowerCap;
    //my decision for the implention
    //describe the amount of element currently in the hashset.
    protected int curSize;
    //describe the currently capacity of the hashset.
    protected int curCapacity;
    /* -----=  Constructors  =----- */

    /**
     * Constructs a new hash set with the default
     * capacities given in DEFAULT_LOWER_CAPACITY
     * and DEFAULT_HIGHER_CAPACITY.
     */
    protected SimpleHashSet() {
        this.higherCap = DEFAULT_HIGHER_CAPACITY;
        this.lowerCap = DEFAULT_LOWER_CAPACITY;
        this.curSize = 0;
        this.curCapacity = INITIAL_CAPACITY;
    }

    /**
     * Constructs a new hash set with capacity INITIAL_CAPACITY
     * @param upperLoadFactor
     * @param lowerLoadFactor
     */
    protected SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
        this.higherCap = upperLoadFactor;
        this.lowerCap = lowerLoadFactor;
        this.curSize = 0;
        this.curCapacity = INITIAL_CAPACITY;
    }
    /* -----=  Instance Methods  =----- */
    //by API

    /**
     * Clamps hashing indices to fit within the
     * current table capacity (see the exercise description for details).
     *
     * @param index
     * @return An index properly clamped.
     */
    protected int clamp(int index) {
        return ((this.curCapacity - 1) & index);
    }

    //getters-By the API

    /**
     * Returns the size of the storage space currently allocated for the set.
     *
     * @return The current capacity (number of cells) of the table.
     */
    public int capacity() {
        return this.curCapacity;
    }

    /**
     * Getter for lower load factor of the table.
     *
     * @return The lower load factor of the table.
     */
    protected float getLowerLoadFactor() {
        return this.lowerCap;
    }

    /**
     * Getter for upper load factor of the table.
     *
     * @return The higher load factor of the table.The higher load factor of the table.
     */
    protected float getUpperLoadFactor() {
        return this.higherCap;
    }

    /* -----=  Instance Methods  =----- */
    //by SimpleSet
    @Override
    public abstract boolean add(String newValue);

    @Override
    public abstract boolean contains(String searchVal);

    @Override
    public abstract boolean delete(String toDelete);

    @Override
    public int size() {
        return this.curSize;
    }
}
