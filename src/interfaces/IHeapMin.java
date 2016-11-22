package interfaces;

public interface IHeapMin<Key extends Number, Value> {
	
	/**
	 * Insert the Key and Value in this HeapMin.
	 * 
	 * @param key
	 * @param value
	 */
	void insert(Key key, Value value);
	
	/**
	 * Remove the first element from the Heap, and rebuild the Heap.
	 * 
	 * @return Value
	 */
	Value remove();
	
	/**
	 * Orgonize the Heap with your Property.
	 * 
	 */
	void rebuildHeap();
	
	/**
	 * 
	 * @return boolean
	 */
	boolean isEmpty();
	
	/**
	 * Verify if the key exist in the Heap.
	 * 
	 * @param key
	 * @return boolean
	 */
	boolean isExists(Key key);
	
	/**
	 * Verify if the value exist in the Heap.
	 * 
	 * @param value
	 * @return A boolean if this value exist in the Heap, True. Else, False.
	 */
	boolean isExists(Value value);
	
	/**
	 * @return Number of size this Heap.
	 */
	int getSize();
	
	/**
	 * @return
	 */
	String toString();
}
