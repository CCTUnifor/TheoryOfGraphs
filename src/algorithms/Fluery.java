package algorithms;

import java.util.ArrayList;
import java.util.List;

import entities.NoVertex;
import exceptions.IllegalGraphFormatException;
import interfaces.IFleury;
import interfaces.IGraph;

/**
 * <span style="text-font: 16px">
 * 	Implementation of Fleury's Algorithm using a Graph List Adjacent Representation. 
 * </span>
 * 
 * @author Thiago Maia
 *
 */
public class Fluery extends IFleury {

	public Fluery(IGraph graph) {
		this.originalGraph = graph.clone();
		this.eulerianTourSequence = new ArrayList<NoVertex>();
	}

	@Override
	public List<NoVertex> search(NoVertex source) throws IllegalGraphFormatException{
		
		source = this.originalGraph.getVertex(source);
		this.eulerianTourSequence.add(source);
		int iteration = 0;
		
		// If this graph dont have any edges, return a Exception.
		if (this.originalGraph.getAllAdjacents().size() == 0)
			throw new IllegalGraphFormatException("Not Eulerian Graph");
		
		while (!(this.originalGraph.getAllAdjacents().size() == 0)){
			
			iteration++;
			
			if (source.isLeaf())
				throw new IllegalGraphFormatException("Not Eulerian Graph");
			
			System.out.println("------------------------ #" + iteration + " ------------------------------\n");
			
			NoVertex selectedVertex = this.getNotBridge(source);
			this.eulerianTourSequence.add(selectedVertex);
			
			this.removeEdgesFromOriginalGraph(selectedVertex); // Remove the "edges" from the original graph
			
			System.out.println(String.format("\nSelected Edge: (%s, %s)\n", selectedVertex.getAncestor().getLabel(), selectedVertex.getLabel() ));
			System.out.println("Eulerian Tour:");
			this.printEulerianSequence();
			
			source = this.originalGraph.getVertex(selectedVertex);
			
		}
		
		return this.eulerianTourSequence;
	}
	
	private NoVertex getNotBridge(NoVertex source){
		
		NoVertex vertexSelected = source.getAdjacentes().iterator().next(); // get the first adjacent vertex
		
		for (NoVertex destination : source.getAdjacentes()) { // for all your adjacent from the source.
			boolean isBridge = this.originalGraph.isBridge(destination.getAncestor(), destination); // verify if this edge passed is a bridge or not
			
			System.out.println(String.format("Visited Edge: (%s, %s)", source.getLabel(), destination.getLabel()));
			System.out.println(String.format("   Is bridge: %s", isBridge));
			
			// if not bridge, return this vertex
			if (!isBridge)
				return destination;
			
			vertexSelected = destination;
		}
		
		return vertexSelected;
		
	}
	
	private void removeEdgesFromOriginalGraph(NoVertex edge) {
		this.originalGraph.removeEdge(edge, edge.getAncestor());
		this.originalGraph.removeEdge(edge.getAncestor(), edge);
	}
	
}
