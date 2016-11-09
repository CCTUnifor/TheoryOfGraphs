package interfaces;

public interface IHeapMin<Key extends Number, Value> {
	
	void insert(Key key, Value value);
	Value remove();
	void rebuildHeap();
	boolean isEmpty();
	boolean isExists(Key key);
	boolean isExists(Value value);
	int getSize();
	String toString();
}
