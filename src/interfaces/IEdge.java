package interfaces;

public abstract interface IEdge<T> {

	IVertex<?> getSource();
	
	IVertex<?> getDestination();
	
	IEdge<T> getReverse();
	
	T getData();
	
	String toString();
}
