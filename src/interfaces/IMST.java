package interfaces;

import entities.GraphAdjacenteList;
import exceptions.IllegalGraphFormatException;

public interface IMST {
	
	/**
	 * Execute the Algorithm searching the Minimal Spanning Tree from the Graph.<p>
	 * 
	 * While the HeapMin is not Empty, I remove the vertex with minimum width from the HeapMin. If this vertex minimum is not a Font,
	 * I will add this edge from the result Graph.<p>
	 * 
	 * And executing the relaxation, a for each in all adjacent vertex from the source vertex removed in the HeapMin, if this adjacent 
	 * vertex exist in the HeapMin and the edge width is less than adjacent vertex width, I will set the edge width value in adjacent
	 * width value and update then from HeapMin. 
	 * 
	 * @return The Graph result.
	 */
	GraphAdjacenteList search();
	
	/**
	 * 	 
	 * 
	 * @return The sum of the all width edges the Minimal Spanning Tree result.
	 */
	int getValueMST();
	
	
	/**
	 * 
	 * 
	 * @param subGraph
	 * @return
	 * @throws IllegalGraphFormatException
	 */
	boolean isMinimalSpanningTree(GraphAdjacenteList subGraph) throws IllegalGraphFormatException;
	
}
