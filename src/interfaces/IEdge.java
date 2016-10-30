package interfaces;

public abstract interface IEdge<T, V> {

	IVertex<T> getSource();
	
	IVertex<T> getDestination();
	
	IEdge<T, V> getReverse();
	
	V getData();
}
