package interfaces;

import exceptions.InvalidEdgeException;

public abstract interface IEdge<T> {
	
	IVertex<?> getSource();
	IVertex<?> getDestination();
	T getData();
	
	IEdge<T> getReverse();

	
	String toString();
}
