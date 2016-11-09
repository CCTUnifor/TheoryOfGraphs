package algorithms;

import java.util.ArrayList;

import entities.NoHeap;
import interfaces.IHeapMin;

public class HeapMin<Key extends Number, Value> implements IHeapMin<Key, Value> {

	private ArrayList<NoHeap<Key, Value>> priorityQueue;

	public HeapMin() {
		this.priorityQueue = new ArrayList<NoHeap<Key, Value>>();
	}

	@Override
	public void insert(Key key, Value value) {
		NoHeap<Key, Value> no = this.find(value);
		
		if (no == null){
			no = new NoHeap<Key, Value>(key, value);
			this.priorityQueue.add(no);			
		}else{
			no.setKey(key);
			no.setValue(value);
		}

		this.rebuildHeap();
	}

	@Override
	public Value remove() {
		if (this.isEmpty())
			return null;
		
		Value removed = this.priorityQueue.get(0).getValue(); 
		this.priorityQueue.remove(0);
		this.rebuildHeap();
		
		return removed;
	}

	@Override
	public boolean isEmpty() {
		return this.priorityQueue.size() == 0;
	}

	@Override
	public int getSize() {
		return this.priorityQueue.size();
	}

	@Override
	public void rebuildHeap() {

		int lengthQueue = this.getSize();

		for (int i = (lengthQueue / 2); i > -1; i--) {
			lengthQueue = this.getSize() - 1;
			int j = i;

			while ((2 * j + 1) <= lengthQueue) {
				int childrenIndex = 2 * j + 1;

				if (childrenIndex + 1 <= lengthQueue) {
					NoHeap<Key, Value> firstChild = this.priorityQueue.get(childrenIndex);
					NoHeap<Key, Value> secondChild = this.priorityQueue.get(childrenIndex + 1);
					
					if (firstChild.getKey().doubleValue() > secondChild.getKey().doubleValue())
						childrenIndex++;
				}
				
				if (this.priorityQueue.get(j).getKey().doubleValue() <= this.priorityQueue.get(childrenIndex).getKey()
						.doubleValue())
					j = lengthQueue;
				else {
					NoHeap<Key, Value> noAux = this.priorityQueue.get(j);
					this.priorityQueue.set(j, this.priorityQueue.get(childrenIndex));
					this.priorityQueue.set(childrenIndex, noAux);
					j = childrenIndex;
				}
			}
		}

	}

	@Override
	public boolean isExists(Key key) {
		for (NoHeap<Key, Value> noHeap : priorityQueue) {
			if (noHeap.getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean isExists(Value value) {
		for (NoHeap<Key, Value> noHeap : priorityQueue) {
			if (noHeap.getValue().equals(value))
				return true;
		}
		return false;
	}
	
	@Override
	public String toString(){
		String message = "{ ";
		
		for (NoHeap<Key, Value> noHeap : priorityQueue) {
			message += String.format("%s ", noHeap);
		}
		message += "}";
		
		return message;
	}
	
	private NoHeap<Key, Value> find(Key key){
		for (NoHeap<Key, Value> noHeap : priorityQueue) {
			if (noHeap.getKey().equals(key))
				return noHeap;
		}
		return null;
	}
	
	private NoHeap<Key, Value> find(Value value){
		for (NoHeap<Key, Value> noHeap : priorityQueue) {
			if (noHeap.getValue().equals(value))
				return noHeap;
		}
		return null;
	}
}
