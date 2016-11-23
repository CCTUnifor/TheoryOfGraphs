package interfaces;

/**
 * 
 * @author Thiago Maia
 *
 * @param <Key>
 * @param <Value>
 */
public interface IHeapMin<Key extends Number, Value> {
	
	/**
	 * Insert the Key and Value in this HeapMin.
	 * 
	 * @param key
	 * @param value
	 */
	public void insert(Key key, Value value);
	
	/**
	 * Update the Key and the Value if this Value exist in the Heap.
	 * 
	 * @param key
	 * @param value
	 */
	public void update(Key key, Value value);
	
	/**
	 * Remove the first element from the Heap, and rebuild the Heap.
	 * 
	 * @return Value
	 */
	public Value remove();
	
	/**
	 * Orgonize the Heap with your Property.
	 * 
	 */
	public void rebuildHeap();
	
	/**
	 * 
	 * @return boolean
	 */
	public boolean isEmpty();
	
	/**
	 * Verify if the key exist in the Heap.
	 * 
	 * @param key
	 * @return boolean
	 */
	public boolean isExists(Key key);
	
	/**
	 * Verify if the value exist in the Heap.
	 * 
	 * @param value
	 * @return A boolean if this value exist in the Heap, True. Else, False.
	 */
	public boolean isExists(Value value);
	
	/**
	 * @return Number of size this Heap.
	 */
	public int getSize();
	
	/**
	 * @return
	 */
	public String toString();
}
