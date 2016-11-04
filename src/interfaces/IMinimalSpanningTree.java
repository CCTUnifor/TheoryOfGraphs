package interfaces;

import exceptions.IllegalGraphFormatException;

public interface IMinimalSpanningTree<V, E> {
	IGraph<V, E> search();
	
	boolean isMinimalSpanningTree(IGraph<V, E> subGraph) throws IllegalGraphFormatException;
}
