import java.util.LinkedList;

/**
 * A hash-set based on chaining. Extends SimpleHashSet.
 */
public class OpenHashSet extends SimpleHashSet {
    //static constant/magic numbers
    /* -----= fields = -------*/
    /*
     A wrapper class for a LinkedList of strings
     */
    private static class MyLinkedList {

        //the actual linked list
        private LinkedList<String> the_linked_list;

        //defauly constructor
        private MyLinkedList() {
            this.the_linked_list = new LinkedList<String>();
        }
    }

    /**
     * array of wrapped linked list
     */
    private MyLinkedList[] data;
    /* -----=  Constructors  =----- */

    /**
     * Constructs a new, empty data with the specified load factors,
     * and the default initial curCapacity (16).
     *
     * @param upperLoadFactor - The upper load factor of the hash data.
     * @param lowerLoadFactor - The lower load factor of the hash data.
     */
    public OpenHashSet(float upperLoadFactor,
                       float lowerLoadFactor) {
        super(upperLoadFactor, lowerLoadFactor);
        this.data = new MyLinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < this.curCapacity; i++) {
            this.data[i] = new MyLinkedList();
        }
    }

    /**
     * A default constructor.
     * A default constructor. Constructs a new, empty data with default initial curCapacity (16),
     * upper load factor (0.75) and lower load factor (0.25).
     */
    public OpenHashSet() {
        super();
        this.data = new MyLinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < this.curCapacity; i++) {
            this.data[i] = new MyLinkedList();
        }
    }

    /**
     * Data constructor - builds the hash set by adding the elements one by one.
     * Duplicate values should be ignored.
     * The new data has the default values of initial curCapacity (16),
     * upper load factor (0.75), and lower load factor (0.25).
     *
     * @param data
     */
    public OpenHashSet(String[] data) {
        super();
        this.data = new MyLinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < this.curCapacity; i++) {
            this.data[i] = new MyLinkedList();
        }
        for (int i = 0; i < data.length; i++) {
            this.add(data[i]);
        }
    }

    /* -----=  Instance Methods  =----- */
    /* Initializes the given array of list wrappers */

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue - New value to add to the set.
     * @return False if newValue already exists in the set, true otherwise.
     */
    @Override
    public boolean add(String newValue) {
        int goalIndex = this.clamp(newValue.hashCode());
        if (this.data[goalIndex].the_linked_list.contains(newValue)) {
            return false;
        }
        if (this.higherCap < ((float) (this.curSize + 1)) / (float) this.curCapacity) {
            this.increaseCap();
            goalIndex = this.clamp(newValue.hashCode());
        }
        this.curSize++;
        this.data[goalIndex].the_linked_list.add(newValue);
        return true;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal - Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        int code = searchVal.hashCode();
        int goalIndex = this.clamp(code);
        return this.data[goalIndex].the_linked_list.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete - Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        int code = toDelete.hashCode();
        int goalIndex = this.clamp(code);
        if (!this.data[goalIndex].the_linked_list.contains(toDelete)) {
            return false;
        }
        if (((float) (this.curSize - 1)) / (float) this.curCapacity < this.lowerCap) {
            this.decreaseCap();
            goalIndex = this.clamp(toDelete.hashCode());
        }
        this.data[goalIndex].the_linked_list.remove(toDelete);
        this.curSize--;
        return true;
    }

    //size function implmented in SimpleHashSet

    //helper functions
    //this helper function for "add" that increase the cur curCapacity
    private void increaseCap() {
        this.curCapacity *= 2;
        MyLinkedList[] newOne = new MyLinkedList[this.curCapacity];
        MyLinkedList[] oldOne = this.data;
        this.data = newOne;
        for (int i = 0; i < this.curCapacity; i++) {
            newOne[i] = new MyLinkedList();
        }
        for (int i = 0; i < oldOne.length; i++) {
            for (int j = 0; j < oldOne[i].the_linked_list.size(); j++) {
                String theWord = oldOne[i].the_linked_list.get(j);
                int goalIndex = this.clamp(theWord.hashCode());
                this.data[goalIndex].the_linked_list.add(theWord);
            }
        }
    }

    //this helper function for "delete" that increase the cur curCapacity
    private void decreaseCap() {
        this.curCapacity /= 2;
        MyLinkedList[] newOne = new MyLinkedList[this.curCapacity];
        MyLinkedList[] oldOne = this.data;
        this.data = newOne;
        for (int i = 0; i < this.curCapacity; i++) {
            newOne[i] = new MyLinkedList();
        }
        for (int i = 0; i < oldOne.length; i++) {
            for (int j = 0; j < oldOne[i].the_linked_list.size(); j++) {
                String theWord = oldOne[i].the_linked_list.get(j);
                int goalIndex = this.clamp(theWord.hashCode());
                this.data[goalIndex].the_linked_list.add(theWord);
            }
        }
    }
}
