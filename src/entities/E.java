package entities;

import interfaces.IEdge;
import interfaces.IVertex;

public class E<T> implements IEdge<T> {

	private IVertex<?> source;
	private IVertex<?> destination;
	private T data;
	
	public E(IVertex<?> source, IVertex<?> destination, T data){
		this.source = source;
		this.destination = destination;
		this.data = data;
	}
	
	@Override
	public IVertex<?> getSource() {
		return this.source;
	}

	@Override
	public IVertex<?> getDestination() {
		return this.destination;
	}

	@Override
	public IEdge<T> getReverse() {
		return new E<T>(this.destination, this.source, this.data);
	}

	@Override
	public T getData() {
		return this.data;
	}
	
	@Override
	public String toString(){
		return String.format("(%s, %s)[%s]", this.source.getLabel(), this.destination.getLabel(), this.data);
	}

}
