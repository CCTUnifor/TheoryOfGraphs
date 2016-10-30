package interfaces;

import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;

public abstract interface IGraphFirstSearch<V, E> {
	
	IGraph<V, E> search();
	String pathToFrom(IVertex<V> source, IVertex<V> destination) throws PathDontFoundedException, InvalidVertexException;
}
	