package interfaces;

import java.util.Set;

import entities.NoVertex;

public interface IGraph {

	/**
	 * @return If this Graph don't have any Edge, return True.
	 */
	public boolean isEmpty();

	/**
	 * @return The Degree this Graph.
	 */
	public int degree();

	/**
	 * @param vertex
	 * @return The Degree from specific Vertex.
	 */
	public int degreeOf(NoVertex vertex);

	/**
	 * @return All Vertex from this Graph.
	 */
	public Set<NoVertex> getAllVertex();
	
	/**
	 * 
	 * @param vertexTarget
	 * @return The Node Vertex in this Graph.
	 */
	public NoVertex getVertex(NoVertex vertexTarget);

	/**
	 * Add this vertex if it don't exist in this Graph.
	 * 
	 * @param vertexToAdd
	 * @return
	 */
	public boolean addVertex(NoVertex vertexToAdd);

	/**
	 * Remove this vertex if it exist in this Graph.
	 * 
	 * @param vertexToRemove
	 * @return
	 */
	public boolean removeVertex(NoVertex vertexToRemove);

	/**
	 * Remove all Vertex this Graph.
	 * 
	 * @return {@link boolean}
	 */
	public boolean removeAllVertex();

	/**
	 * All adjacent vertex from this Graph.
	 * 
	 * @return {@link Set}<{@link NoVertex}>
	 */
	public Set<NoVertex> getAllAdjacents();
	
	/**
	 * Get all adjacent vertex from this vertex target.
	 * 
	 * @param vertexTarget
	 * @return {@link Set}<{@link NoVertex}>
	 */
	public Set<NoVertex> getAllAdjacents(NoVertex vertexTarget);
	
	/**
	 * Verify if this two vertex are adjacent.
	 * 
	 * @param source
	 * @param destination
	 * @return {@code true} if are adjacent; {@code false} otherwise.
	 */
	public boolean areAdjacents(NoVertex source, NoVertex destination);
	
	/**
	 * Add a Edge if this two Vertex exists in this Graph. The destination
	 * NodeVertex contains the ancestor, source NodeVertex.
	 * 
	 * @param source
	 * @param destination
	 * @return {@code true} if this edge is added; {@code false} otherwise.
	 */
	public boolean addEdge(NoVertex source, NoVertex destination);

	/**
	 * Add a Edge with widthEdge if this two Vertex exists in this Graph. The destination
	 * NodeVertex contains the ancestor and the widthEdge, source NodeVertex.
	 * 
	 * @param source
	 * @param destination
	 * @param widthEdge
	 * @return {@code true} if this edge is added; {@code false} otherwise. 
	 */
	public boolean addEdge(NoVertex source, NoVertex destination, int widthEdge);
	
	/**
	 * Remove this edge if source and destination already in this Graph and destination ancestor equals this source param.
	 * 
	 * @param source
	 * @param destination
	 * @return {@code true} if this edge is removed; {@code false} otherwise.
	 */
	public boolean removeEdge(NoVertex source, NoVertex destination);
	
	
	/**
	 * Get the edge reference.
	 * 
	 * @param source
	 * @param destination
	 * @return The Edge
	 */
	public NoVertex getEdge(NoVertex source, NoVertex destination);
	
	/**
	 * Run the DepthFirstSearchListAdjacent and verify if this edge is Bridge.
	 * 
	 * @param source
	 * @param destination
	 * @return {@code true} if this edge is bridge; {@code false} otherwise.
	 */
	public boolean isBridge(NoVertex source, NoVertex destination);
	
	public IGraph clone();
	
}
