package algorithms;

import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IMinimalSpanningTree;
import interfaces.IVertex;

public class PrimMinimalSpanningTree<V, E> implements IMinimalSpanningTree<V, E> {

	private IGraph<V, E> graph;
	private IGraph<V, E> subGraph;
	
	public PrimMinimalSpanningTree(IGraph<V, E> graph) {
		this.graph = graph.clone();
	}
	
	@Override
	public IGraph<V, E> search() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean isMinimalSpanningTree(IGraph<V, E> subGraph) throws IllegalGraphFormatException {
		boolean isMST = false;
		this.subGraph = subGraph.clone();
		
		this.verifyVertex();
		this.verifyEdges();
		
		
		
		return isMST;
	}
	
	private void verifyVertex() throws IllegalGraphFormatException {

		if (this.subGraph.getAllVertex().size() != this.graph.getAllVertex().size())
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
		
		for (IVertex<V> iVertex : this.subGraph.getAllVertex()) {
			if (!this.graph.containsVertex(iVertex))
				throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
		}
		
	}
	
	private void verifyEdges() throws IllegalGraphFormatException {
		for (IEdge<E> iEdge : this.subGraph.getAllEdge()) {
			
			try {
				
				if (!this.subGraph.isBridge(null, iEdge))
					throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
								
			} catch (InvalidEdgeException | InvalidVertexException e) {
				
			}
		}
	}
	
	
}
