package algorithms;

import enums.Colors;
import exceptions.InvalidVertexException;
import interfaces.GraphFirstSearch;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class DepthFirstSearch<V, E> extends GraphFirstSearch<V, E> {

	private int time;

	public DepthFirstSearch(IGraph<V, E> graph) {
		this.graph = graph.clone();
		time = 0;
		this.numberConnectedComponent = 0;
	}

	@Override
	public IGraph<V, E> search() {

		for (IVertex<V> iVertex : this.graph.getAllVertex()) {
			if (iVertex.getColor() == Colors.WHITE)
				this.numberConnectedComponent++;
				this.runDFS(iVertex);
		}

		return this.graph;
	}

	@Override
	public IGraph<V, E> search(IVertex<V> source) {
		this.runDFS(source);

		return this.graph;
	}

	private void runDFS(IVertex<V> source) {

		source.setColor(Colors.GREY);
		source.setTimeStart(++time);

		if (this.graph != null && !this.graph.isEmpty()) {

			this.organizeAllVexter(source);

			source.setColor(Colors.BLACK);
			source.setTimeFinish(++time);

		}

	}

	@SuppressWarnings("unchecked")
	private void organizeAllVexter(IVertex<V> source) {

		for (IEdge<?> iEdge : source.getAllEdge()) {
			iEdge = (IEdge<E>) iEdge;
			IVertex<V> target = (IVertex<V>) iEdge.getDestination();

			target = this.graph.getVertex(target);

			if (target.getColor() == Colors.WHITE) {
				target.setAncestor(source);
				this.runDFS(target);
			}

		}

	}
}
