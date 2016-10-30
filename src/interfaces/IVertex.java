package interfaces;

import java.util.Set;

import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public abstract interface IVertex<T> {

	String getLabel();
	
	T getData();
	
	boolean containsEdge(IEdge<?> edgeTarget) throws InvalidEdgeException;
	
	Set<IEdge<?>> getAllEdge();
	
	boolean addEdge(IEdge<?> edgeToAdd);
	
	boolean removeEdge(IEdge<?> edgeToRemove);
	
	boolean removeAllEdge();
	
	boolean containsPathTo(IVertex<T> destination) throws InvalidVertexException;
	
	int getDegree();
	
	boolean equals(Object object);
	
	boolean isAdjacent(IVertex<T> destination);
	
	int positionArray();
	
	String toString();
	
	String toString(boolean withData);
	
}
