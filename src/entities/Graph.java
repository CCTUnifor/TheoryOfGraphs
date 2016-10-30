package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IGraph;
import interfaces.IVertex;

public class Graph<T, V> implements IGraph<T, V> {

	private Set<IVertex<T>> vertexs;
	private int numberVertex;
	
	public Graph(int numberVertex) {
		this.vertexs = new LinkedHashSet<IVertex<T>>();
		this.numberVertex = numberVertex;
	}
	
	private IVertex<T> getVertex(IVertex<T> vertexTarget) throws InvalidVertexException{
		this.verify(vertexTarget);
		
		for (IVertex<T> iVertex : vertexs) {
			if (iVertex.equals(vertexTarget)) {
				return iVertex;
			}
		}
		
		return null;
	}
	
	@Override
	public boolean isEmpty() {

		for (IVertex<T> iVertex : vertexs) {
			if (iVertex.getAllEdge().size() > 0) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public int degree() {
		int countDegree = 0;
		
		for (IVertex<T> iVertex : vertexs) {
			countDegree += iVertex.getDegree();
		}
		return countDegree;
	}

	@Override
	public void verify(IVertex<T> vertexToVerify) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public Set<IVertex<T>> getAllVertex() {
		return this.vertexs;
	}

	@Override
	public boolean containsVertex(IVertex<T> vertex) throws InvalidVertexException {
		
		if (this.vertexs.contains(vertex)) 
			return true;
		
		return false;
		
	}

	@Override
	public void addVertex(IVertex<T> vertexToAdd) throws InvalidVertexException {
		this.verify(vertexToAdd);
		
		this.addVertex(vertexToAdd);
		
	}

	@Override
	public void addVertex(Set<IVertex<T>> vertexToAdd) throws InvalidVertexException {
		for (IVertex<T> iVertex : vertexToAdd) {
			this.verify(iVertex);
			this.vertexs.add(iVertex);
		}
	}

	@Override
	public boolean removeVertex(IVertex<T> vertexToRemove) throws InvalidVertexException {
		this.verify(vertexToRemove);
		
		return this.vertexs.remove(vertexToRemove);
	}

	@Override
	public boolean removeAllVertex() {
		this.vertexs = new LinkedHashSet<IVertex<T>>();
		return true;
	}

	@Override
	public boolean removeAllVertex(Set<IVertex<T>> vertexToRemove) throws InvalidVertexException {
		
		for (IVertex<T> iVertex : vertexToRemove) {
			boolean isRemoved = this.removeVertex(iVertex);
			
			if (!isRemoved) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public boolean areAdjacents(IVertex<T> source, IVertex<T> destination) throws InvalidVertexException {
		this.verify(source);

		for (IVertex<T> iVertex : vertexs) {
			boolean isAdjacent = iVertex.isAdjacent(destination);
			if (isAdjacent)
				return true;
			
		}
		
		return false;
	}

	@Override
	public int degreeOf(IVertex<T> vertex) throws InvalidVertexException {
		this.verify(vertex);
		
		return this.getVertex(vertex).getDegree();
	}

	@Override
	public Set<E<T, V>> getAllEdge() {
		Set<E<T, V>> allEdge = new LinkedHashSet<E<T,V>>();
		
		for (IVertex<T> v : this.vertexs) {
			Set edges = v.getAllEdge();
			allEdge.addAll(edges);
			
		}
		
		return allEdge;
	}

	@Override
	public Set<E<T, V>> getAllEdge(IVertex<T> vertexTarget) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean containsEdge(E<T, V> edgeTarget) throws InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public void addEdge(E<T, V> edgeToAdd) throws InvalidEdgeException, InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public void addEdge(Set<E<T, V>> edgeToAdd) throws InvalidVertexException, InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean removeEdge(E<T, V> edgeToRemove) throws InvalidVertexException, InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean removeAllEdge() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean removeAllEdge(IVertex<T> vertextTarget) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean removeAllEdge(Set<E<T, V>> edgeToRemove) throws InvalidVertexException, InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public Set<E<T, V>> getDistinctEdge() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public Set<E<T, V>> getDistinctEdge(IVertex<T> vertexTarget) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean isEdgeAllowed(E<T, V> edgeTarget) throws InvalidEdgeException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

}
