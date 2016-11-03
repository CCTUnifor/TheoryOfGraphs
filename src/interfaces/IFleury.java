package interfaces;

import java.util.Set;

import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IFleury<V, E> {
	
	Set<IEdge<E>> search(IVertex<V> source) throws IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException;
	
}
