package interfaces;

public abstract class IDepthFirstSearchBridge<V, E>{
	
	protected IGraph<V, E> graph;
	public abstract boolean isBridge(IEdge<E> edge);
}
