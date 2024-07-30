//Sarah Agarwal
//UID: 119641224
// I did not use any unauthorized assistance on this assignment

/* The EmptyBSLT class serves as a placeholder for an empty key within a binary search tree (BST).
 * Methods in this class either provide base case behavior for recursive tree operations or throw exceptions
 * to signal illegal operations (e.g., requesting the smallest or largest key in an empty tree). This class
 * ensures that the BST can gracefully handle operations on empty subtrees without the need for null checks,
 * making the code cleaner, safer, and more maintainable.*/
package bslt;

@SuppressWarnings({"unchecked", "rawtypes"})
public class EmptyBSLT<K extends Comparable<K>, V> implements BSLT<K, V> {

    // Singleton instance of EmptyBSLT
    private static final EmptyBSLT instance = new EmptyBSLT();
    
    // Private constructor to prevent instantiation
    public EmptyBSLT() { 
    	
    }

    // Static method to get the singleton instance of EmptyBSLT
    public static EmptyBSLT getInstance() {
    	return instance;
    }

    // Inserts a key-value pair into the tree
    // Since the tree is empty, a new NonemptyBSLT instance is created
    public NonemptyBSLT<K, V> insertKeyWithValue(K newKey, V newValue) {
    	
    	// Check if the key or value is null
    	if(newKey == null || newValue == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	// Create and return a new NonemptyBSLT instance with the given key and value
        return new NonemptyBSLT<>(newKey, newValue);
 
    }

    // Returns the size of the tree, which is 0 since it's empty
    public int sizeOfTree() {
    	
    	return 0;
    }

    // Looks up the value associated with the given key,
    //which is null since the tree is empty
    public V lookupValueForKey(K targetKey) {
    	return null;
    }

    // Checks if the tree is empty, which is always true
    public boolean isEmpty() {
    	return true;
    }

    // Returns the largest key in the tree, which 
    //throws an EmptyBSLTException since the tree is empty
    @Override
    public K largestKey() throws EmptyBSLTException {
        	throw new EmptyBSLTException();
    }

    // Returns the smallest key in the tree, which 
    //throws an EmptyBSLTException since the tree is empty
    public K smallestKey() throws EmptyBSLTException {
    	throw new EmptyBSLTException();
    }

    // Returns the parent of the given target key, which 
    //is null since the tree is empty
    public K parent(K targetKey) {
    	return null;
    }

    // Removes the key-value pair from the tree, which does
    //nothing since the tree is empty
    public BSLT<K, V> removeKeyWithValue(K targetKey) {
    	return this;
    }

    // Returns an empty string representation of the tree since it's empty
    public String toString() {
    	return "";
    }

}