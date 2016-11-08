package interfaces;

import java.util.Set;

import algorithms.DepthFirstSearchBridge;
import enums.GraphPrintPresentation;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public interface IGraph<T, V> {

	boolean isEmpty();
	int degree();
	void verify(IVertex<T> vertexToVerify) throws InvalidVertexException;
	int numberOfVertix();
	String toString();
	String toString(boolean withData);
	String toString(GraphPrintPresentation typePresentation);
	IGraph<T, V> clone();
	void resetConfigs();
	
	IVertex<T> getVertex(IVertex<T> vertexTarget);
	
	/* VERTEX */
	
	Set<IVertex<T>> getAllVertex();
	
	boolean containsVertex(IVertex<T> vertex);
	
	IVertex<T> addVertex(IVertex<T> vertexToAdd);
	
	Set<IVertex<T>> addVertex(Set<IVertex<T>> vertexToAdd);
	
	boolean removeVertex(IVertex<T> vertexToRemove) throws InvalidVertexException;
	
	boolean removeAllVertex();
	
	boolean removeAllVertex(Set<IVertex<T>> vertexToRemove) throws InvalidVertexException;
	
	boolean areAdjacents(IVertex<T> source, IVertex<T> destination);
	
	int degreeOf(IVertex<T> vertex) throws InvalidVertexException;
	
	/* EDGE */
	
	IEdge<V> getEdge(IVertex<T> source, IVertex<T> destination);
	
	Set<IEdge<V>> getAllEdge();
	
	Set<IEdge<V>> getAllEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean containsEdge(IEdge<V> edgeTarget) throws InvalidEdgeException;
	
	void addEdge(IEdge<V> edgeToAdd) throws InvalidVertexException, InvalidEdgeException;
	
	void addEdge(Set<IEdge<V>> edgeToAdd) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeEdge(IEdge<V> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeAllEdge();
	
	boolean removeAllEdge(IVertex<T> vertextTarget) throws InvalidVertexException, InvalidEdgeException;
	
	boolean removeAllEdge(Set<IEdge<V>> edgeToRemove) throws InvalidVertexException, InvalidEdgeException;
	
	Set<IEdge<V>> getDistinctEdge();
	
	Set<IEdge<V>> getDistinctEdge(IVertex<T> vertexTarget) throws InvalidVertexException;
	
	boolean isBridge(IEdge<V> edgeTarget) throws InvalidEdgeException, InvalidVertexException;
	
}
