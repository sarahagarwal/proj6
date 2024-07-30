//Sarah Agarwal
//UID: 119641224
// I did not use any unauthorized assistance on this assignment


package bslt;

@SuppressWarnings("unchecked")

/*The NonemptyBSLT class is a component of a binary search tree (BST)
 *  structure that represents non-empty keys within the tree. It implements
 *  the BSLT interface, which outlines common operations for a binary search
 *   tree node, such as insertion, deletion, and lookup of key-value pairs. 
 */
public class NonemptyBSLT<K extends Comparable<K>, V> implements BSLT<K, V> {

	// The key stored in this node
    private K key;
    
    // The value associated with the key
    private V value;
    
    // The left subtree of this node
    private BSLT<K, V> left = EmptyBSLT.getInstance();
    
    // The right subtree of this node
    private BSLT<K, V> right = EmptyBSLT.getInstance();
    
    //Initializes a non-empty binary search tree node with a given key and value.
    public NonemptyBSLT(K key, V value) {
        this.key = key;
        this.value = value; 
    }
    
    // Inserts a new key-value pair into the binary search tree while
    //maintaining the binary search tree property.
    public NonemptyBSLT<K, V> insertKeyWithValue(K newKey, V newValue) {
    	
    	// Check if the new key or value is null, and throw an exception if so
        if (newKey == null || newValue == null) {
        	
            throw new IllegalArgumentException("New key or value cannot be null.");
        }
        
        // Compare the new key with the current key
        int num = newKey.compareTo(key);

        // If the new key is less than the current  key
        if (num < 0) {
        	
            // If the left child is empty, create a new object with the new key and
        	//value and set it as the left child
            if (left.isEmpty()) {
            	
                left = new NonemptyBSLT<>(newKey, newValue);
            } 
            // Otherwise, recursively insert the key-value pair into the left subtree
            else {
            	
                left = left.insertKeyWithValue(newKey, newValue);
            }
        } 
        
        // If the new key is greater than the current  key
        else if (num > 0) {
        	
            // If the right child is empty, create a new object with the new key and
        	//value and set it as the right child
            if (right.isEmpty()) {
            	
                right = new NonemptyBSLT<>(newKey, newValue);
            } 
            
            // Otherwise, recursively insert the key-value pair into the right subtree
            else {
                right = right.insertKeyWithValue(newKey, newValue);
            }
        }
        
        // Return a reference to the current object
        return this;
    	
    		
    	
    }

    //Calculates and returns the size of the binary search tree 
    public int sizeOfTree() {
    	
    	return 1 + left.sizeOfTree() + right.sizeOfTree();
    }

    // Searches for a key in the binary search tree and returns the associated value,
    //or null if the key is not found.
    public V lookupValueForKey(K targetKey) {
    	
    	// Compare the target key with the current key
        int num = targetKey.compareTo(key);
        
        // Initialize the result variable to null
        V result = null;
        
        // If the target key matches the current key
        if (num == 0) {
        	
            // Set the result to the value associated with the current node's key
            result = value;
        } 
        
        // If the target key is greater than the current key
        else if (num > 0) {
        	
            // Recursively search for the target key in the right subtree
            result = right.lookupValueForKey(targetKey);
        } 
        // If the target key is less than the current key
        else if (num < 0) {
        	
            // Recursively search for the target key in the left subtree
            result = left.lookupValueForKey(targetKey);
        }
        
        // Return the result (either the value associated with the target key
        //or null if not found)
        return result;
    }

    // Finds and returns the largest key in the binary search tree and throws
    //an exception if the tree is empty.
    @Override
    public K largestKey() throws EmptyBSLTException {
    	
    	K result = key;
    	if(right.isEmpty()) {
    		result = key;
    	} else {
    		result = right.largestKey();
    	}
    	return result;
    	
    	
    }

    // Finds and returns the smallest key in the binary search tree and throws
    //an exception if the tree is empty.
    public K smallestKey() throws EmptyBSLTException {
    	
    	// Initialize the result variable with the current key
        K result = key;
        
        // If the left subtree is empty (i.e., there are no smaller keys)
        if (left.isEmpty()) {
        	
            // The smallest key is the current key, so assign it to the result
            result = key;
        } 
        
        // If the left subtree is not empty
        else {
            // Recursively search for the smallest key in the left subtree
            result = left.smallestKey();
        }
        
        // Return the smallest key found
        return result;
    }
    
   // Checks whether the current key is empty and 
    //returns true if it is, false otherwise.
    public boolean isEmpty() {
    	
    	return key == null;
    }

    // Finds and returns the parent key of a given key in the binary search tree, 
    //or null if the given key is the root or not found.
    public K parent(K targetKey) {
 
    	  K result = null; // Initialize the result as null, to return null if parent isn't found
    	    int num = targetKey.compareTo(key); // Compare the targetKey with the current node's key
    	    
    	    // Attempt to find the value associated with the targetKey in the left and right subtrees
    	    V currentLeftValue = left.lookupValueForKey(targetKey);
    	    V currentRightValue = right.lookupValueForKey(targetKey);

    	    // If the targetKey is less than the current key, and the left subtree is not
    	    //empty, and the targetKey is found in the left subtree
    	    if(num < 0 && !left.isEmpty() && currentLeftValue != null) {
    	    	
    	        // If the value found in the left subtree for the targetKey is the same
    	    	//as the current value
    	        if(currentLeftValue.equals(value)) {
    	        	
    	            result = key; // The current node is the parent
    	            
    	        } else {
    	        	
    	            // Otherwise, recursively search for the parent in the left subtree
    	            result = left.parent(targetKey);
    	        }
    	    } 
    	    // If the targetKey is greater than the current key, and the right subtree 
    	    //is not empty, and the targetKey is found in the right subtree
    	    else if(num > 0 && !right.isEmpty() && currentRightValue != null) {
    	    	
    	        // If the value found in the right subtree for the targetKey is the 
    	    	//same as the current value
    	        if(currentRightValue.equals(value)) {
    	        	
    	            result = key; // The current key is the parent
    	            
    	        } else {
    	        	
    	            // Otherwise, recursively search for the parent in the right subtree
    	            result = right.parent(targetKey);
    	            
    	        }
    	    }
    	    // Return the key of the parent, or null if no parent was found
    	    return result;
    }
    

    // Removes a key-value pair from the binary search tree while maintaining the binary 
    //search tree property.
    public BSLT<K, V> removeKeyWithValue(K targetKey) {
    	
    	// Compare the target key with the current key
        int num = targetKey.compareTo(key);
        
        // If the target key is smaller than the current key
        if (num < 0) {
        	
            // Recursively remove the target key from the left subtree
            left = left.removeKeyWithValue(targetKey);
        } 
        
        // If the target key is larger than the current key
        else if (num > 0) {
        	
            // Recursively remove the target key from the right subtree
            right = right.removeKeyWithValue(targetKey);
        } 
        
        // If the target key matches the current key
        else {
        	
            // If the current node is a leaf (both left and right subtrees are empty)
            if (left.isEmpty() && right.isEmpty()) {
            	
                // Remove the current node by returning an empty tree
                return EmptyBSLT.getInstance();
            } 
            
            // If the left subtree is empty
            else if (left.isEmpty()) {
            	
                // Return the right subtree to replace the current key
                return right;
            } 
            
            // If the right subtree is empty
            else if (right.isEmpty()) {
            	
                // Return the left subtree to replace the current key
                return left;
            } 
            // If both subtrees are non-empty
            else {
            	
                // Find the smallest key in the right subtree (successor)
                try {
                    K currKey = right.smallestKey();
                    // Get the value associated with the smallest key
                    V currValue = right.lookupValueForKey(currKey);
                    
                    // Remove the successor node from the right subtree
                    right = right.removeKeyWithValue(currKey);
                    
                    // Replace the current node's key and value with those of the successor
                    key = currKey;
                    value = currValue;
                } catch (EmptyBSLTException e) {
                    // Handle the exception if the right subtree is unexpectedly empty
                    throw new RuntimeException();
                }
            }
        }
        
        // Return the modified tree
        return this;
    	
    }

    // Returns a string representation of the binary search tree rooted
    //at this node, in an in-order traversal order.
    public String toString() {
    	
    	// Initialize an empty string to store the result
        String result = "";
        
        // add the string representation of the left subtree, if it exists
        if (left != null) {
            result += left.toString();
        }
        
        // Append the current key and value to the result string
        result += key + "+" + value + " ";
        
        // add the string representation of the right subtree, if it exists
        if (right != null) {
            result += right.toString();
        }
        
        // Return the concatenated result string
        return result;
    }

}
