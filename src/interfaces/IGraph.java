package interfaces;

import java.util.Set;

import entities.E;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IGraph<T, V> {

	boolean isEmpty();
	int degree();
	void verify(IVertex<T> vertexToVerify) throws InvalidVertexException;
	
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

	Set<E<T, V>> getAllEdge();
	
	Set<E<T, V>> getAllEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean containsEdge(E<T, V> edgeTarget) throws InvalidEdgeException;
	
	void addEdge(E<T, V> edgeToAdd) throws InvalidEdgeException, InvalidEdgeException;
	
	void addEdge(Set<E<T, V>> edgeToAdd) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeEdge(E<T, V> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeAllEdge();
	
	boolean removeAllEdge(IVertex<T> vertextTarget) throws InvalidVertexException;
	
	boolean removeAllEdge(Set<E<T, V>> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	Set<E<T, V>> getDistinctEdge();
	
	Set<E<T, V>> getDistinctEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean isEdgeAllowed(E<T, V> edgeTarget) throws InvalidEdgeException;
	
}
