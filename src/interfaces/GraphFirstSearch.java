package interfaces;

import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;

public abstract class GraphFirstSearch<V, E> implements IGraphFirstSearch<V, E> {
	
	protected IGraph<V, E> graph;
	protected int numberConnectedComponent; 
	
	@Override
	public String pathToFrom(IVertex<V> source, IVertex<V> destination) throws PathDontFoundedException, InvalidVertexException {
		
		source = this.graph.getVertex(source);
		destination = this.graph.getVertex(destination);
		
		String path = "";
		
		if(source == destination){
			path += source.toString();
		}else if(destination.getAncestor() == null){
			path = String.format("Path don't exist(%s, %s)", source, destination);
			throw new PathDontFoundedException(path);
		}else{
			path += this.pathToFrom(source, destination.getAncestor());
			path += " -> " + destination;
		}
		
		return path;
	}
	
	@Override
	public int getNumberConnectedComponent() {
		return this.numberConnectedComponent;
	}
}
