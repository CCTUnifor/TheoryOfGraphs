package interfaces;

import java.util.Set;

import entities.NoVertex;

public interface IGraphAdjacent {

	/**
	 * 
	 * @param vertexTarget
	 * @return The Node Vertex in this Graph.
	 */
	NoVertex getVertex(NoVertex vertexTarget);

	/**
	 * @return
	 */
	boolean isEmpty();

	/**
	 * @return The Degree this Graph.
	 */
	int degree();

	/**
	 * @param vertex
	 * @return The Degree from specific Vertex.
	 */
	int degreeOf(NoVertex vertex);

	/**
	 * @return All Vertex from this Graph.
	 */
	Set<NoVertex> getAllVertex();

	/**
	 * Add this vertex if they don't exist in this Graph.
	 * 
	 * @param vertexToAdd
	 * @return
	 */
	boolean addVertex(NoVertex vertexToAdd);

	/**
	 * Remove this vertex if they exist in this Graph.
	 * 
	 * @param vertexToRemove
	 * @return
	 */
	boolean removeVertex(NoVertex vertexToRemove);

	/**
	 * Remove all Vertex this Graph.
	 * 
	 * @return
	 */
	boolean removeAllVertex();

	/**
	 * Add a Edge if this two Vertex exists in this Graph. The destination
	 * NodeVertex contains the ancestor, source NodeVertex.
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	boolean addEdge(NoVertex source, NoVertex destination);

	/**
	 * Add a Edge with widthEdge if this two Vertex exists in this Graph. The destination
	 * NodeVertex contains the ancestor and the widthEdge, source NodeVertex.
	 * 
	 * @param source
	 * @param destination
	 * @param widthEdge
	 * @return
	 */
	boolean addEdge(NoVertex source, NoVertex destination, int widthEdge);
	
	/**
	 * Remove this edge if source and destination already in this Graph and destination ancestor equals this source param.
	 * 
	 * @param source
	 * @param destination
	 * @return
	 */
	boolean removeEdge(NoVertex source, NoVertex destination);
	
	
	/**
	 * Get all adjacent vertex from this vertex target.
	 * 
	 * @param vertexTarget
	 * @return
	 */
	Set<NoVertex> getAllAdjacents(NoVertex vertexTarget);
	
}
