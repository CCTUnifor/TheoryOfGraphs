package interfaces;

import java.util.Set;

import entities.E;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IGraph<T, V> {

	boolean isEmpty();
	int degree();
	void verify(IVertex<T> vertexToVerify) throws InvalidVertexException;
	int numberOfVertix();
	String toString();
	String toString(boolean withData);
	IGraph<T, V> clone();
	
	IVertex<T> getVertex(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	/* VERTEX */
	
	Set<IVertex<T>> getAllVertex();
	
	boolean containsVertex(IVertex<T> vertex) throws InvalidVertexException;
	
	void addVertex(IVertex<T> vertexToAdd) throws InvalidVertexException;
	
	void addVertex(Set<IVertex<T>> vertexToAdd) throws InvalidVertexException;
	
	boolean removeVertex(IVertex<T> vertexToRemove) throws InvalidVertexException;
	
	boolean removeAllVertex();
	
	boolean removeAllVertex(Set<IVertex<T>> vertexToRemove) throws InvalidVertexException;
	
	boolean areAdjacents(IVertex<T> source, IVertex<T> destination) throws InvalidVertexException;
	
	int degreeOf(IVertex<T> vertex) throws InvalidVertexException;
	
	/* EDGE */

	Set<IEdge<V>> getAllEdge();
	
	Set<IEdge<V>> getAllEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean containsEdge(IEdge<V> edgeTarget) throws InvalidEdgeException;
	
	void addEdge(IEdge<V> edgeToAdd) throws InvalidVertexException, InvalidEdgeException;
	
	void addEdge(Set<IEdge<V>> edgeToAdd) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeEdge(IEdge<V> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeAllEdge() throws InvalidEdgeException, InvalidVertexException;
	
	boolean removeAllEdge(IVertex<T> vertextTarget) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeAllEdge(Set<IEdge<V>> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	Set<IEdge<V>> getDistinctEdge();
	
	Set<IEdge<V>> getDistinctEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean isEdgeAllowed(IEdge<V> edgeTarget) throws InvalidEdgeException;
	
}
