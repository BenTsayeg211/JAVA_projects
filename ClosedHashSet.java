/**
 * A hash-set based on closed-hashing with quadratic probing. Extends SimpleHashSet.
 */
public class ClosedHashSet extends SimpleHashSet {
    //static constant/magic numbers
    /* -----= fields = -------*/
    /**
     * array of string-the data of the hash set
     */
    private String[] data;
    /* -----=  Constructors  =----- */

    /**
     * Constructs a new, empty table with the specified load factors,
     * and the default initial capacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash table.
     * @param lowerLoadFactor - The lower load factor of the hash table.
     */
    public ClosedHashSet(float upperLoadFactor, float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        this.data = new String[INITIAL_CAPACITY];
    }

    /*
     *A default constructor.
     * Constructs a new, empty table with default initial capacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public ClosedHashSet() {
        super();
        this.data = new String[INITIAL_CAPACITY];
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new table has the default values of initial capacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data - Values to add to the set.
     */
    public ClosedHashSet(String[] data) {
        super();
        this.data = new String[INITIAL_CAPACITY];
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /* -----=  Instance Methods  =----- */

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue - New value to add to the set.
     * @return False if newValue already exists in the set, true otherwise.
     */
    @Override
    public boolean add(String newValue) {
        int hashCodeOfNewVal = newValue.hashCode();
        int goalIndex;
        for (int i = 0; i <= this.curCapacity; i++) {
            goalIndex = this.clamp((int) ((Math.pow(i, 2)) + i) / 2 + hashCodeOfNewVal);
            if (i == this.curCapacity || this.data[goalIndex] == null) {
                if (((float) (this.curSize + 1)) / (float) this.curCapacity > this.higherCap) {
                    this.increasedataSize();
                    //try to add after change the capacity
                    if (this.add(newValue)) {
                        return true;
                    }
                    return false;
                }
                this.curSize++;
                this.data[goalIndex] = newValue;
                return true;
            }
            if (this.data[goalIndex].equals(newValue)) {
                return false;
            }
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal - Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        int hashCodeOfNewVal = searchVal.hashCode();
        int goalIndex;
        for (int i = 0; i <= this.curCapacity; i++) {
            goalIndex = this.clamp((int) ((Math.pow(i, 2)) + i) / 2 + hashCodeOfNewVal);
            if (this.data[goalIndex] != null) {
                if (searchVal.equals(this.data[goalIndex])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete - Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        int hashCodeOfNewVal = toDelete.hashCode();
        for (int i = 0; i <= this.curCapacity; i++) {
            int goalIndex = this.clamp(hashCodeOfNewVal + (i + (i * i)) / 2);
            if (this.data[goalIndex] != null) {
                if (toDelete.equals(this.data[goalIndex])) {
                    this.curSize -= 1;
                    this.data[goalIndex] = null;
                    if (this.lowerCap > ((float) this.curSize) / (float) this.curCapacity) {
                        this.decreasedataSize();
                    }
                    return true;
                }
            }

        }
        return false;
    }
    //helper functions
    //this helper function for "add" that increase the cur curCapacity
    private void increasedataSize() {
        this.curCapacity *= 2;
        String[] newOne = new String[this.curCapacity];
        String[] oldOne = this.data;
        this.curSize = 0;
        this.data = newOne;
        for (int i = 0; i < oldOne.length; i++) {
            if (oldOne[i] != null) {
                this.add(oldOne[i]);
            }
        }
    }

    //this helper function for "delete" that increase the cur curCapacity
    private void decreasedataSize() {
        if (this.curCapacity == 1) {
            return;
        }
        this.curCapacity /= 2;
        String[] newOne = new String[this.curCapacity];
        String[] oldOne = this.data;
        this.curSize = 0;
        this.data = newOne;
        for (int i = 0; i < oldOne.length; i++) {
            if (oldOne[i] != null) {
                this.add(oldOne[i]);
            }
        }
    }

}
