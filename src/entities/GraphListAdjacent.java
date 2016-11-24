package entities;

import java.util.LinkedHashSet;
import java.util.Set;

import algorithms.DepthFirstSearchListAdjacent;
import interfaces.IGraph;

/**
 * Implementation of Graph using Adjacent List Computation Representation.
 * 
 * @author Thiago Maia
 *
 */
public class GraphListAdjacent implements IGraph {

	private Set<NoVertex> vertexs;

	public GraphListAdjacent() {
		this.vertexs = new LinkedHashSet<NoVertex>();
	}

	@Override
	public NoVertex getVertex(NoVertex vertexTarget) {
		for (NoVertex noVertex : vertexs) {
			if (noVertex.equals(vertexTarget))
				return noVertex;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return (this.vertexs.size() == 0);
	}

	@Override
	public int degree() {
		int countDegree = 0;

		for (NoVertex iVertex : vertexs) {
			countDegree += iVertex.getDegree();
		}

		return countDegree;
	}

	@Override
	public int degreeOf(NoVertex vertex) {
		return this.getVertex(vertex).getDegree();
	}

	@Override
	public Set<NoVertex> getAllVertex() {
		return this.vertexs;
	}

	@Override
	public boolean addVertex(NoVertex vertexToAdd) {
		if (this.getVertex(vertexToAdd) == null)
			return this.vertexs.add(vertexToAdd);
		return false;
	}

	@Override
	public boolean removeVertex(NoVertex vertexToRemove) {
		NoVertex no = this.getVertex(vertexToRemove);
		if (no != null)
			return this.vertexs.remove(no);

		return false;
	}

	@Override
	public boolean removeAllVertex() {
		this.vertexs = new LinkedHashSet<NoVertex>();
		return true;
	}

	@Override
	public boolean addEdge(NoVertex source, NoVertex destination) {
		return this.addEdge(source, destination, 0);
	}

	@Override
	public boolean addEdge(NoVertex source, NoVertex destination, int widthEdge) {
		source = this.getVertex(source);

		if (source == null || this.getVertex(destination) == null)
			return false;

		destination.setWidthEdge(widthEdge);
		destination.setAncestor(source);
		return source.addAdjacente(destination);
	}

	@Override
	public boolean removeEdge(NoVertex source, NoVertex destination) {
		source = this.getVertex(source);
		destination = this.getVertex(destination);

		if (source == null || destination == null)
			return false;

		return source.removeAdjacente(destination);
	}

	@Override
	public NoVertex getEdge(NoVertex source, NoVertex destination) {
		source = this.getVertex(source);

		if (source == null && this.getVertex(destination) == null)
			return null;

		for (NoVertex adj : source.getAdjacentes()) {
			if (destination.getLabel().equals(adj.getLabel())) {
				
				if (destination.getWidthEdge() == adj.getWidthEdge())
					return destination;
			}
		}

		return null;
	}

	public Set<NoVertex> getAllAdjacents(NoVertex vertexTarget) {
		NoVertex no = this.getVertex(vertexTarget);

		if (no == null)
			return null;

		return no.getAdjacentes();

	}

	@Override
	public Set<NoVertex> getAllAdjacents() {
		Set<NoVertex> all = new LinkedHashSet<>();

		for (NoVertex source : this.vertexs) {
			all.addAll(source.getAdjacentes());
		}

		return all;
	}

	@Override
	public boolean areAdjacents(NoVertex source, NoVertex destination) {
		if (this.getVertex(source) != null && this.getVertex(destination) != null) {
			source = this.getVertex(source);
			destination = this.getVertex(destination);

			return source.isAdjacent(destination);
		}
		return false;
	}

	@Override
	public String toString() {
		String message = "";

		for (NoVertex source : vertexs) {
			message += String.format("%s => {", source.getLabel());

			for (NoVertex destination : source.getAdjacentes()) {
				message += String.format(" %s[%s]", destination.getLabel(), destination.getWidthEdge());
			}

			message += " }\n";
		}

		return message;
	}

	@Override
	public GraphListAdjacent clone() {
		GraphListAdjacent newGraph = new GraphListAdjacent();

		for (NoVertex vertex : vertexs) {
			newGraph.addVertex(new NoVertex(vertex.getLabel(), vertex.getWidth(), vertex.getWidthEdge()));
		}

		for (NoVertex vertex : vertexs) {
			for (NoVertex adj : vertex.getAdjacentes()) {
				newGraph.addEdge(vertex, adj, adj.getWidthEdge());
			}
		}

		return newGraph;
	}

	@Override
	public boolean isBridge(NoVertex source, NoVertex destination) {

		DepthFirstSearchListAdjacent DFS = new DepthFirstSearchListAdjacent(this);

		return DFS.isBridge(source, destination);
	}

}
