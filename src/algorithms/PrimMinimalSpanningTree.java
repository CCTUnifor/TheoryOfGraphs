package algorithms;

import entities.Graph;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IGraphFirstSearch;
import interfaces.IHeapMin;
import interfaces.IMinimalSpanningTree;
import interfaces.IVertex;

public class PrimMinimalSpanningTree<V, Ed> implements IMinimalSpanningTree<V, Ed> {

	private IGraph<V, Ed> graph;
	private IGraph<V, Ed> resultMSPGraph;
	private IGraph<V, Ed> subGraph;
	IHeapMin<Integer, IVertex<V>> heapMin;
	private int valueMST;

	public PrimMinimalSpanningTree(IGraph<V, Ed> graph) {
		this.graph = graph.clone();
		resultMSPGraph = new Graph<V, Ed>();
		heapMin = new HeapMin<Integer, IVertex<V>>();
		this.valueMST = 0;
	}

	@Override
	public IGraph<V, Ed> search() throws InvalidVertexException, InvalidEdgeException {

		this.loadAllVertex();
		this.loadHeap();

		while (!this.heapMin.isEmpty()) {
			
			System.out.println("\n--------------------------------------------------------------\n");
			
			IVertex<V> vertexMin = this.heapMin.remove();

			System.out.println(String.format("Vertex with min width: (%s)[%s]", vertexMin.toString(true), vertexMin.getWidth()));
			
 			if (vertexMin != null && vertexMin.getAncestor() != null) {
				IEdge<Ed> edge = this.graph.getEdge(vertexMin, vertexMin.getAncestor());
				this.resultMSPGraph.addEdge(edge);
				this.resultMSPGraph.addEdge(edge.getReverse());
				
				this.valueMST += (vertexMin.getWidth() * 2);
			}
			
			System.out.println("\n--------- Relaxation -------------");
			
			for (IEdge<Ed> iEdge : this.graph.getAllEdge(vertexMin)) {
				IVertex<V> adj = this.graph.getVertex((IVertex<V>) iEdge.getDestination());
				
				System.out.println("\n");
				System.out.println(String.format("  Edge: %s[%s]", iEdge.toString(), iEdge.getData()));
				System.out.println(String.format("  Vertex destination: (%s)[%s]", adj.toString(), adj.getWidth()));
				
				if (this.heapMin.isExists(adj) && Integer.parseInt(iEdge.getData().toString()) < adj.getWidth()) {
					int value = Integer.parseInt(iEdge.getData().toString());
					adj.setWidth(Integer.parseInt(iEdge.getData().toString()));
					adj.setAncestor(vertexMin);
					this.heapMin.insert(adj.getWidth(), adj);
					//this.heapMin.rebuildHeap();
					
					System.out.println(String.format("  Destination changed: %s[%s]", adj.toString(), adj.getWidth()));
				}
				
			}

		}

		return this.resultMSPGraph;
	}

	@Override
	public boolean isMinimalSpanningTree(IGraph<V, Ed> subGraph) throws IllegalGraphFormatException {
		
		this.subGraph = subGraph.clone();

		this.verifyVertex();
		this.verifyEdges();
		this.verifyConnectedComponent();

		int minValueSubGraph = 0;
		for (IEdge<Ed> iEdge : this.subGraph.getAllEdge()) {
			minValueSubGraph += Integer.parseInt(iEdge.getData().toString());
		}
		
		return (minValueSubGraph <= this.valueMST);
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

		if (this.subGraph.getAllEdge().size() == 0)
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
		
		for (IEdge<Ed> iEdge : this.subGraph.getAllEdge()) {
			try {
				if (!this.subGraph.isBridge(iEdge) || !this.graph.containsEdge(iEdge))
					throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

			} catch (InvalidEdgeException | InvalidVertexException e) {

			}
		}
	}

	private void verifyConnectedComponent() throws IllegalGraphFormatException {
		IGraphFirstSearch<V, Ed> DFS = new DepthFirstSearch<V, Ed>(this.subGraph);
		IVertex<V> source = this.subGraph.getAllVertex().iterator().next();
		
		DFS.search(source);
		
		if (DFS.getNumberConnectedComponent() > 1 )
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
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
				this.heapMin.insert(iVertex.getWidth(), iVertex);
			}
		}

	}

	@Override
	public int getValueMST() {
		return this.valueMST;
	}

}
