package interfaces;

import java.util.Set;

import entities.E;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public abstract interface IVertex<T> {

	T getLabel();
	
	boolean containsEdge(E edgeTarget) throws InvalidEdgeException;
	
	Set<E> getAllEdge();
	
	boolean containsPathTo(IVertex<T> destination) throws InvalidVertexException;
	
	int getDegree();
	
	boolean equals(Object object);
	
	boolean isAdjacent(IVertex<T> destination);
	
}
