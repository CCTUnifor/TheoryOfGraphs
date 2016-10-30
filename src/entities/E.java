package entities;

import interfaces.IEdge;
import interfaces.IVertex;

public class E<T, V> implements IEdge<T, V> {

	private IVertex<T> source;
	private IVertex<T> destination;
	private V data;
	
	public E(IVertex<T> source, IVertex<T> destination, V data){
		this.source = source;
		this.destination = destination;
		this.data = data;
	}
	
	@Override
	public IVertex<T> getSource() {
		return this.source;
	}

	@Override
	public IVertex<T> getDestination() {
		return this.destination;
	}

	@Override
	public IEdge<T, V> getReverse() {
		return new E<T,V>(this.destination, this.source, this.data);
	}

	@Override
	public V getData() {
		return this.data;
	}

}
