package entities;

import java.util.LinkedHashSet;
import java.util.Set;

import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IVertex;

public class V<T> implements IVertex<T> {

	private Set<E> adjacenteAdges;
	private T label;
	
	public V(T label){
		this.adjacenteAdges = new LinkedHashSet<E>();
		this.label = label;
	}
	
	@Override
	public T getLabel() {
		return this.label;
	}

	@Override
	public boolean containsEdge(E edgeTarget) throws InvalidEdgeException {
		return (this.adjacenteAdges.contains(edgeTarget));
	}

	@Override
	public Set<E> getAllEdge() {
		return this.adjacenteAdges;
	}

	@Override
	public boolean containsPathTo(IVertex<T> destination) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}
	
	@Override
	public int getDegree() {
		return this.adjacenteAdges.size();
	}

	@Override
	public boolean isAdjacent(IVertex<T> destination) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}



}
