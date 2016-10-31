package interfaces;

import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IFleury<V, E> {
	
	IGraph<V, E> search(IVertex<V> source) throws IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException;
	
}
