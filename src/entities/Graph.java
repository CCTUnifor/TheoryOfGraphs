package entities;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import enums.GraphPrintPresentation;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class Graph<TVertex, VEdge> implements IGraph<TVertex, VEdge>, Cloneable {

	private Set<IVertex<TVertex>> vertexs;
	private int numberVertex;

	public Graph() {
		this.vertexs = new LinkedHashSet<IVertex<TVertex>>();
		this.numberVertex = 0;
	}

	@Override
	public void resetConfigs() {
		this.vertexs.stream().forEach(x -> x.resetConfigs());
	}

	@Override
	public IVertex<TVertex> getVertex(IVertex<TVertex> vertexTarget) {
		return this.vertexs.stream().filter(x -> x.equals(vertexTarget)).findFirst().orElse(null);
	}

	@Override
	public boolean isEmpty() {
		return (this.getAllEdge().size() == 0);
	}

	@Override
	public int degree() {
		int countDegree = 0;

		for (IVertex<TVertex> iVertex : vertexs) {
			countDegree += iVertex.getDegree();
		}

		return countDegree;
	}

	@Override
	public void verify(IVertex<TVertex> vertexToVerify) throws InvalidVertexException {
		if (vertexToVerify == null)
			throw new InvalidVertexException("vertexToVerify is null");

		if (this.containsVertex(vertexToVerify))
			throw new InvalidVertexException("This Vertex already in this Graph");

		for (IVertex<TVertex> iVertex : vertexs) {
			if (iVertex.getLabel().equals(vertexToVerify.getLabel())) {
				throw new InvalidVertexException("Exist a Vertex with this Label(" + iVertex.getLabel() + ")");
			}
		}

	}

	@Override
	public Set<IVertex<TVertex>> getAllVertex() {
		return this.vertexs;
	}

	@Override
	public boolean containsVertex(IVertex<TVertex> vertex) {
		return (this.getVertex(vertex) != null);
	}

	@Override
	public IVertex<TVertex> addVertex(IVertex<TVertex> vertexToAdd) {
		if (!this.containsVertex(vertexToAdd)) {
			this.vertexs.add(vertexToAdd);
		}
		return this.getVertex(vertexToAdd);
	}

	@Override
	public Set<IVertex<TVertex>> addVertex(Set<IVertex<TVertex>> vertexToAdd) {
		vertexToAdd.forEach(x -> {
			this.vertexs.add(x);
		});

		return vertexToAdd;
	}

	@Override
	public boolean removeVertex(IVertex<TVertex> vertexToRemove) {
		if (this.containsVertex(vertexToRemove)) {
			this.vertexs.remove(vertexToRemove);
			return true;
		}

		return false;
	}

	@Override
	public boolean removeAllVertex() {
		this.vertexs = new LinkedHashSet<IVertex<TVertex>>();
		return true;
	}

	@Override
	public boolean removeAllVertex(Set<IVertex<TVertex>> vertexToRemove) throws InvalidVertexException {

		for (IVertex<TVertex> iVertex : vertexToRemove) {
			boolean isRemoved = this.removeVertex(iVertex);

			if (!isRemoved) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean areAdjacents(IVertex<TVertex> source, IVertex<TVertex> destination) {
		if (!this.containsVertex(source) && !this.containsVertex(destination))
			return false;

		for (IVertex<TVertex> iVertex : vertexs) {
			boolean isAdjacent = iVertex.isAdjacent(destination);
			if (isAdjacent)
				return true;
		}

		return false;
	}

	@Override
	public int degreeOf(IVertex<TVertex> vertex) throws InvalidVertexException {

		return this.getVertex(vertex).getDegree();
	}

	@Override
	public IEdge<VEdge> getEdge(IVertex<TVertex> source, IVertex<TVertex> destination) {
		for (IEdge<VEdge> iEdge : this.getAllEdge()) {
			if (iEdge.getSource().equals(source) && iEdge.getDestination().equals(destination))
				return iEdge;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IEdge<VEdge>> getAllEdge() {
		Set<IEdge<VEdge>> allEdge = new LinkedHashSet<IEdge<VEdge>>();

		for (IVertex<TVertex> v : this.vertexs) {
			Set<IEdge<?>> edges = v.getAllEdge();
			allEdge.addAll((Collection<? extends IEdge<VEdge>>) edges);

		}

		return allEdge;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<IEdge<VEdge>> getAllEdge(IVertex<TVertex> vertexTarget) throws InvalidVertexException {
		Set<IEdge<VEdge>> allEdge = new LinkedHashSet<IEdge<VEdge>>();
		IVertex<TVertex> vertex = this.getVertex(vertexTarget);

		for (IEdge<?> iEdge : vertex.getAllEdge()) {
			allEdge.add((IEdge<VEdge>) iEdge);
		}

		return allEdge;
	}

	@Override
	public boolean containsEdge(IEdge<VEdge> edgeTarget) throws InvalidEdgeException {

		for (IVertex<TVertex> iVertex : vertexs) {
			if (iVertex.containsEdge(edgeTarget))
				return true;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addEdge(IEdge<VEdge> edgeToAdd) throws InvalidVertexException, InvalidEdgeException {
		IVertex<TVertex> source = (IVertex<TVertex>) edgeToAdd.getSource();

		// this.verify(source);
		IVertex<TVertex> u = this.getVertex(source);
		u.addEdge(edgeToAdd);

	}

	@Override
	public void addEdge(Set<IEdge<VEdge>> edgeToAdd) throws InvalidVertexException, InvalidEdgeException {

		for (IEdge<VEdge> iEdge : edgeToAdd) {
			this.addEdge(iEdge);
		}

	}

	@Override
	public boolean removeEdge(IEdge<VEdge> edgeToRemove) throws InvalidVertexException, InvalidEdgeException {
		IVertex<TVertex> source = (IVertex<TVertex>) edgeToRemove.getSource();

		// this.verify(source);
		source = this.getVertex(source);
		for (IEdge<?> iEdge : source.getAllEdge()) {
			iEdge = (IEdge<VEdge>) iEdge;
			if (edgeToRemove.equals(iEdge))
				return source.removeEdge(iEdge);
		}
		return source.removeEdge(edgeToRemove);
	}

	@Override
	public boolean removeAllEdge() {

		for (IVertex<TVertex> iVertex : vertexs) {
			boolean isRemoved = this.removeAllEdge(iVertex);

			if (!isRemoved) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean removeAllEdge(IVertex<TVertex> vertextTarget) {
		// this.verify(vertextTarget);
		return this.getVertex(vertextTarget).removeAllEdge();
	}

	@Override
	public boolean removeAllEdge(Set<IEdge<VEdge>> edgeToRemove) throws InvalidVertexException, InvalidEdgeException {

		for (IEdge<VEdge> iEdge : edgeToRemove) {
			boolean isRemoved = this.removeEdge(iEdge);
			if (!isRemoved) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Set<IEdge<VEdge>> getDistinctEdge() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public Set<IEdge<VEdge>> getDistinctEdge(IVertex<TVertex> vertexTarget) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public int numberOfVertix() {
		return this.numberVertex;
	}

	@Override
	public IGraph<TVertex, VEdge> clone() {
		IGraph<TVertex, VEdge> newGraph = new Graph<TVertex, VEdge>();

		Set<IEdge<VEdge>> allEdges = this.getAllEdge();

		for (IEdge<VEdge> iEdge : allEdges) {
			IVertex<TVertex> u = new V<TVertex>(iEdge.getSource().getLabel(), (TVertex) iEdge.getSource().getData());
			IVertex<TVertex> v = new V<TVertex>(iEdge.getDestination().getLabel(),
					(TVertex) iEdge.getDestination().getData());

			newGraph.addVertex(u);
			newGraph.addVertex(v);

			try {
				IEdge<VEdge> newEdge = new E<VEdge>(u, v, iEdge.getData());
				newGraph.addEdge(newEdge);
			} catch (InvalidVertexException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			} catch (InvalidEdgeException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}

		return newGraph;
	}

	@Override
	public boolean isBridge(IEdge<VEdge> edgeTarget) throws InvalidEdgeException, InvalidVertexException {

		// IDepthFirstSearchBridge<TVertex, VEdge> _search = new
		// DepthFirstSearchBridge<TVertex, VEdge>(this.clone());
		return false;
		// return _search.isBridge(edgeTarget);

	}

	@Override
	public String toString() {
		return this.mountGrafoToMessage(false);
	}

	@Override
	public String toString(boolean withData) {

		return this.mountGrafoToMessage(withData);

	}

	@SuppressWarnings("unchecked")
	public String toString(GraphPrintPresentation typePresentation) {
		return "error";
		// return
		// GraphPrintPresentationFactory.instance(typePresentation).mountGrafoToMessage(this);
	}

	private String mountGrafoToMessage(boolean withData) {

		String message = "";

		for (IVertex<TVertex> iVertex : vertexs) {

			message += String.format("%s => {", iVertex.toString(withData));

			for (IEdge<?> iEdge : iVertex.getAllEdge()) {

				if (withData) {
					message += String.format(" ((%s)[%s])", iEdge.getDestination().toString(withData), iEdge.getData());
				} else {
					message += String.format(" %s", iEdge.getDestination());
				}

			}
			message += " }\n";
		}
		return message;
	}

}
