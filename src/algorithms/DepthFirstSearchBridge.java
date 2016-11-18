package algorithms;

import enums.Colors;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class DepthFirstSearchBridge<V, E> extends IDepthFirstSearchBridge<V, E> {

	private int time;
	private int numberConnectedComponent;
	private IGraph<V, E> graphAux;

	public DepthFirstSearchBridge(IGraph<V, E> graph) {
		graph.resetConfigs();
		this.graph = graph.clone();
		time = 0;
		numberConnectedComponent = 0;
	}

	@Override
	public boolean isBridge(IEdge<E> edge) throws InvalidVertexException, InvalidEdgeException {
		graphAux = this.graph.clone();
		
		graphAux.resetConfigs();
		this.graphAux.removeEdge(edge);
		this.graphAux.removeEdge(edge.getReverse());

		for (IVertex<V> iVertex : this.graphAux.getAllVertex()) {
			if (iVertex.getColor() == Colors.WHITE) {
				
				if (this.numberConnectedComponent > 1)
					return true;
				
				this.numberConnectedComponent++;
				this.runDFS(iVertex);
			}
		}
		
		if (this.numberConnectedComponent > 1)
			return true;
		
		return false;
	}

	@SuppressWarnings("unchecked")
	private void runDFS(IVertex<V> source) throws InvalidVertexException {

		source.setColor(Colors.GREY);
		source.setTimeStart(++time);

		if (!this.graphAux.isEmpty()) {

			for (IEdge<?> iEdge : source.getAllEdge()) {
				iEdge = (IEdge<E>) iEdge;
				IVertex<V> destination = (IVertex<V>) iEdge.getDestination();

				destination = this.graphAux.getVertex(destination);
				
				if (destination.getColor() == Colors.WHITE) {
					destination.setAncestor(source);
					this.runDFS(destination);
				}

			}
			
			source.setColor(Colors.BLACK);
			source.setTimeFinish(++time);

		}

	}
}
