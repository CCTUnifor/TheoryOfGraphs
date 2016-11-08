package interfaces;

import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IMinimalSpanningTree<V, E> {
	IGraph<V, E> search() throws InvalidVertexException, InvalidEdgeException;
	
	boolean isMinimalSpanningTree(IGraph<V, E> subGraph) throws IllegalGraphFormatException;
}
