package algorithms;

import entities.Graph;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IHeapMin;
import interfaces.IMinimalSpanningTree;
import interfaces.IVertex;

public class PrimMinimalSpanningTree<V, Ed> implements IMinimalSpanningTree<V, Ed> {

	private IGraph<V, Ed> graph;
	private IGraph<V, Ed> resultMSPGraph;
	private IGraph<V, Ed> subGraph;
	IHeapMin<Integer, IVertex<V>> heapMin;

	public PrimMinimalSpanningTree(IGraph<V, Ed> graph) {
		this.graph = graph.clone();
		resultMSPGraph = new Graph<V, Ed>();
		heapMin = new HeapMin<Integer, IVertex<V>>();
	}

	@Override
	public IGraph<V, Ed> search() throws InvalidVertexException, InvalidEdgeException {

		this.loadAllVertex();
		this.loadHeap();

		while (this.resultMSPGraph.getAllEdge().size() < this.graph.getAllVertex().size() - 1) {

			IVertex<V> vertexMin = this.heapMin.remove();
			if (vertexMin != null && vertexMin.getAncestor() != null) {
				IEdge<Ed> edge = this.graph.getEdge(vertexMin, vertexMin.getAncestor());
				this.resultMSPGraph.addEdge(edge);
			}

			for (IEdge<Ed> iEdge : this.graph.getAllEdge(vertexMin)) {
				IVertex<V> adj = (IVertex<V>) iEdge.getDestination();

				if (this.heapMin.isExists(adj) && Integer.parseInt(iEdge.getData().toString()) < adj.getWidth()) {
					adj.setWidth(Integer.parseInt(iEdge.getData().toString()));
					adj.setAncestor(vertexMin);
					this.heapMin.insert(adj.getWidth(), adj);
				}

			}

		}

		return this.resultMSPGraph;
	}

	@Override
	public boolean isMinimalSpanningTree(IGraph<V, Ed> subGraph) throws IllegalGraphFormatException {
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

		for (IEdge<Ed> iEdge : this.subGraph.getAllEdge()) {
			try {
				if (!this.subGraph.isBridge(iEdge))
					throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

			} catch (InvalidEdgeException | InvalidVertexException e) {

			}
		}
	}

	private void loadAllVertex() {
		for (IVertex<V> iVertex : this.graph.getAllVertex()) {
			this.resultMSPGraph.addVertex(iVertex.clone());
		}
	}

	private void loadHeap() {
		IVertex<V> firstVertex = this.graph.getAllVertex().iterator().next();
		firstVertex.setWidth(0);
		this.heapMin.insert(firstVertex.getWidth(), firstVertex);
		
		for (IVertex<V> iVertex : this.graph.getAllVertex()) {
			if (!iVertex.equals(firstVertex)){
				iVertex.setWidth(Integer.MAX_VALUE);
				this.heapMin.insert(Integer.MAX_VALUE, iVertex);
			}
		}

	}

}
