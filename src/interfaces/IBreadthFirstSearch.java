package interfaces;

import exceptions.InvalidVertexException;

public interface IBreadthFirstSearch<V, E> {
	
	IGraph<V, E> search();
	String pathToFrom(IVertex<V> source, IVertex<V> destination) throws InvalidVertexException;
}
