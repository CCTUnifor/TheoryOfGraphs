package entities;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
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
	public void resetConfigs(){
		for (IVertex<TVertex> iVertex : vertexs) {
			iVertex.resetConfigs();
		}
	}

	@Override
	public IVertex<TVertex> getVertex(IVertex<TVertex> vertexTarget) throws InvalidVertexException {

		for (IVertex<TVertex> iVertex : vertexs) {
			if (iVertex.equals(vertexTarget)) {
				return iVertex;
			}
		}

		throw new InvalidVertexException("Vetex don't exist");
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
	public boolean containsVertex(IVertex<TVertex> vertex) throws InvalidVertexException {

		for (IVertex<TVertex> iVertex : vertexs) {
			if (iVertex.equals(vertex)) 
				return true;
		}

		return false;

	}

	@Override
	public void addVertex(IVertex<TVertex> vertexToAdd) throws InvalidVertexException {
		//this.verify(vertexToAdd);

		if (!this.containsVertex(vertexToAdd))
			this.vertexs.add(vertexToAdd);
		

	}

	@Override
	public void addVertex(Set<IVertex<TVertex>> vertexToAdd) throws InvalidVertexException {
		for (IVertex<TVertex> iVertex : vertexToAdd) {
			//this.verify(iVertex);
			this.vertexs.add(iVertex);
		}
	}

	@Override
	public boolean removeVertex(IVertex<TVertex> vertexToRemove) throws InvalidVertexException {
		//this.verify(vertexToRemove);

		return this.vertexs.remove(vertexToRemove);
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
	public boolean areAdjacents(IVertex<TVertex> source, IVertex<TVertex> destination) throws InvalidVertexException {
		//this.verify(source);

		for (IVertex<TVertex> iVertex : vertexs) {
			boolean isAdjacent = iVertex.isAdjacent(destination);
			if (isAdjacent)
				return true;

		}

		return false;
	}

	@Override
	public int degreeOf(IVertex<TVertex> vertex) throws InvalidVertexException {
		//this.verify(vertex);

		return this.getVertex(vertex).getDegree();
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

		for (IVertex<TVertex> v : this.vertexs) {
			if (v.equals(vertexTarget)) {
				Set<IEdge<?>> edges = v.getAllEdge();
				allEdge.addAll((Collection<? extends IEdge<VEdge>>) edges);
			}

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

		//this.verify(source);
		source = this.getVertex(source);
		for (IEdge<?> iEdge : source.getAllEdge()) {
			iEdge = (IEdge<VEdge>) iEdge;
			if (edgeToRemove.equals(iEdge))
				return source.removeEdge(iEdge);
		}
		return source.removeEdge(edgeToRemove);
	}

	@Override
	public boolean removeAllEdge() throws InvalidEdgeException, InvalidVertexException {

		for (IVertex<TVertex> iVertex : vertexs) {
			boolean isRemoved = this.removeAllEdge(iVertex);

			if (!isRemoved) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean removeAllEdge(IVertex<TVertex> vertextTarget) throws InvalidVertexException, InvalidEdgeException {
		this.verify(vertextTarget);

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
	public String toString() {
		return this.mountGrafoToMessage(false);
	}

	private String mountGrafoToMessage(boolean withData) {

		String message = "";
		
		for (IVertex<TVertex> iVertex : vertexs) {

			message += String.format("%s => {", iVertex.toString(withData), iVertex.getData());

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

	@Override
	public String toString(boolean withData) {

		return this.mountGrafoToMessage(withData);

	}

	@Override
	public IGraph<TVertex, VEdge> clone() {
		IGraph<TVertex, VEdge> newGraph = new Graph<TVertex, VEdge>();

		Set<IEdge<VEdge>> allEdges = this.getAllEdge();

		for (IEdge<VEdge> iEdge : allEdges) {
			IVertex<TVertex> u = (IVertex<TVertex>) iEdge.getSource();
			IVertex<TVertex> v = (IVertex<TVertex>) iEdge.getDestination();
			
			try {
				newGraph.addVertex(u);
			} catch (InvalidVertexException e) {
			} 
			try {
				newGraph.addVertex(v);
			} catch (InvalidVertexException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
			}
			
			try {
				newGraph.addEdge(iEdge);
			} catch (InvalidVertexException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			} catch (InvalidEdgeException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		}

		return newGraph;
	}
	
	@Override
	public boolean isBridge(IDepthFirstSearchBridge<TVertex, VEdge> search, IEdge<VEdge> edgeTarget)
			throws InvalidEdgeException, InvalidVertexException {
		
		return search.isBridge(edgeTarget);
		
	}



}
