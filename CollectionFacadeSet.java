import java.util.Collection;

/**
 * Wraps an underlying Collection<String>and serves to
 * both simplify its API and give it a common type with
 * the implemented SimpleHashSets.
 */
public class CollectionFacadeSet implements SimpleSet {
    //static constant/magic numbers
    /* -----= fields = -------*/
    //The warped object by this facade.
    protected Collection<String> collection;

    /* -----=  Constructors  =----- */

    /**
     * Creates a new facade wrapping the specified collection.
     *
     * @param collection - The Collection to wrap.
     */
    public CollectionFacadeSet(Collection<String> collection) {
        this.collection = collection;
    }

    /* -----=  Instance Methods  =----- */
    //all the methods will use the basic algorithms of collection class

    /**
     * Add a specified element to the set if it's not already in it.
     *
     * @param newValue New value to add to the set.
     * @return False if newValue already exists in the set, true otherwise.
     */
    @Override
    public boolean add(String newValue) {
        if (!(this.collection.contains(newValue))) {
            this.collection.add(newValue);
            return true;
        }
        return false;
    }

    /**
     * Look for a specified value in the set.
     *
     * @param searchVal Value to search for.
     * @return True if searchVal is found in the set, false otherwise.
     */
    @Override
    public boolean contains(String searchVal) {
        return this.collection.contains(searchVal);
    }

    /**
     * Remove the input element from the set.
     *
     * @param toDelete Value to delete.
     * @return True if toDelete is found and deleted, false otherwise.
     */
    @Override
    public boolean delete(String toDelete) {
        if (this.collection.contains(toDelete)) {
            this.collection.remove(toDelete);
            return true;
        }
        return false;
    }

    /**
     * Returns the number of actual objects held in the set,
     * which is not necessarily equal to its storage capacity.
     *
     * @return The number of elements currently in the set.
     */
    @Override
    public int size() {
        return collection.size();
    }
}
