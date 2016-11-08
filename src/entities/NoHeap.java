package entities;

public class NoHeap<Key extends Number, Value> {
	private Key key;
	private Value value;
	
	public NoHeap(Key key, Value value) {
		this.key = key;
		this.value = value;
	}
	
	public Key getKey(){
		return this.key;
	}
	
	public Value getValue(){
		return this.value;
	}
	
	public void setKey(Key key){
		this.key = key;
	}
	
	public void setValue(Value value){
		this.value = value;
	}
	
	@Override
	public String toString(){
		return String.format("(%s, %s)", this.key, this.value);
	}
}
