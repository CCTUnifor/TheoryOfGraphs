package algorithms;

import enums.Colors;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class DepthFirstSearchBridge<V, E> extends IDepthFirstSearchBridge<V, E> {

	private int time;
	private boolean isBridge;
	private IEdge<E> edgeToCompare;

	public DepthFirstSearchBridge(IGraph<V, E> graph) {
		this.graph = graph.clone();
		time = 0;
		this.isBridge = false;
	}

	@Override
	public boolean isBridge(IEdge<E> edge) {

		this.edgeToCompare = edge;

		return this.runDFS((IVertex<V>) edge.getSource(), (IVertex<V>) edge.getDestination());
		
	}

	private boolean runDFS(IVertex<V> source, IVertex<V> target) {

		source.setColor(Colors.GREY);
		source.setTimeStart(++time);

		if (!this.graph.isEmpty()) {

			return this.organizeAllVexter(source, target);

		}
		return true;

	}

	private boolean organizeAllVexter(IVertex<V> source, IVertex<V> target){
		
		for (IEdge<?> iEdge : source.getAllEdge()) {
			iEdge = (IEdge<E>)iEdge;
			IVertex<V> destination = (IVertex<V>) iEdge.getDestination();
			
			try {
				destination = this.graph.getVertex(destination);
			} catch (InvalidVertexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (destination.getColor() == Colors.GREY){
				if ((!source.equals(this.edgeToCompare.getDestination()) || !destination.equals(this.edgeToCompare.getSource())) &&
						(destination.equals(target)))
					return false;
			}
			
			if (destination.getColor() == Colors.WHITE) {
				destination.setAncestor(source);
				if(!this.runDFS(destination, target))
					return false;
			}
			
		}
		return true;
		
	}

	/*
	 * @Override public IGraph<V, E> search(IVertex<V> source) {
	 * 
	 * this.runDFS(source, source);
	 * 
	 * return this.graph; }
	 * 
	 * @Override public IGraph<V, E> search() { IVertex<V> source =
	 * this.graph.getAllVertex().iterator().next();
	 * 
	 * for (IVertex<V> iVertex : this.graph.getAllVertex()) { if
	 * (iVertex.getColor() == Colors.WHITE) this.runDFS(iVertex, null); }
	 * 
	 * return this.graph; }
	 * 
	 * 
	 */

}
