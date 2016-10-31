package interfaces;

import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public abstract class IDepthFirstSearchBridge<V, E>{
	
	protected IGraph<V, E> graph;
	public abstract boolean isBridge(IEdge<E> edge) throws InvalidVertexException, InvalidEdgeException ;
}
