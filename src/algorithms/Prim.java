package algorithms;

import entities.GraphAdjacenteList;
import entities.NoVertex;
import exceptions.IllegalGraphFormatException;
import interfaces.IHeapMin;
import interfaces.IMST;

public class Prim implements IMST {

	private GraphAdjacenteList graph;
	private GraphAdjacenteList resultMSTGraph;
	private GraphAdjacenteList subGraph;
	private IHeapMin<Integer, NoVertex> heapMin;
	private int valueMST;

	public Prim(GraphAdjacenteList graph) {
		this.graph = graph.clone();
		this.resultMSTGraph = new GraphAdjacenteList();
		this.heapMin = new HeapMin<Integer, NoVertex>();
		this.valueMST = 0;
	}

	@Override
	public GraphAdjacenteList search() throws IllegalGraphFormatException {

		this.addAllVertexToResultMSTGraph(); // Add all vertex in the Result Graph.
		this.loadHeapMin(); // Loading the Heap Min.

		while (!this.heapMin.isEmpty()) {

			//System.out.println("\n--------------------------------------------------------------\n");

			NoVertex vertexMin = this.heapMin.remove(); // Tanking the Vertex with min Width.
			/*System.out.println(
					String.format("Vertex with min width: %s[%s]", vertexMin.getLabel(), vertexMin.getWidth()));*/

			if (vertexMin != null && vertexMin.getAncestor() != null) { // If the vertexMin is not a Font
				this.resultMSTGraph.addEdge(vertexMin, vertexMin.getAncestor());
				this.resultMSTGraph.addEdge(vertexMin.getAncestor(), vertexMin);
				this.valueMST += vertexMin.getWidthEdge();
			}

			//System.out.println("\n--------------- Relaxation ------------------");

			for (NoVertex adj : this.graph.getAllAdjacents(vertexMin)) { // Start the Relaxation

				adj = this.graph.getVertex(adj); // Taking reference from the Graph.
					
				/*System.out.println("\n");
				System.out.println(String.format("  Edge: (%s, %s)[%s]", vertexMin.getLabel(), adj.getLabel(), adj.getWidthEdge(),
						adj.getWidthEdge()));
				System.out.println(String.format("  Vertex destination: (%s)[%s]", adj.getLabel(), adj.getWidth()));*/

				if (this.heapMin.isExists(adj) && adj.getWidthEdge() < adj.getWidth()) { // If this adj exist in the HeapMin and your Edge Width is less than your width

					adj.setWidth(adj.getWidthEdge());
					adj.setAncestor(vertexMin);

					this.heapMin.update(adj.getWidth(), adj);

					//System.out.println(String.format("  Destination changed: (%s)[%s]", adj.getLabel(), adj.getWidth()));
				}

			}

		}

		return this.resultMSTGraph;
	}

	@Override
	public int getValueMST() {
		return this.valueMST;
	}

	@Override
	public boolean isMinimalSpanningTree(GraphAdjacenteList subGraph) throws IllegalGraphFormatException {
		
		this.search(); // Execute the PRIM Algorithm.
		this.subGraph = subGraph.clone();

		this.verifyVertex(); // Verify all Vertex from subGraph
		this.verifyEdges(); // Verify all edges from subGraph
		this.verifyConnectedComponent(); // If this subGraph is disconnected, throw an IllegalGraphFormatException.
		
		int minValueSubGraph = 0; // sum of each widthEdge
		for (NoVertex adj : this.subGraph.getAllAdjacents()) {
			minValueSubGraph += adj.getWidthEdge();
		}
		
		return (minValueSubGraph == this.valueMST); // compare the search sum and this sum of subGraph

	}

	private void verifyConnectedComponent() throws IllegalGraphFormatException {

		DepthFirstSearchBridgeAdjacent dfs = new DepthFirstSearchBridgeAdjacent(this.subGraph);
		int numberConnectedComponents = dfs.getNumberConnectedComponent();

		if (numberConnectedComponents > 1)
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

	}

	private void verifyEdges() throws IllegalGraphFormatException {

		if (this.subGraph.getAllAdjacents().size() == 0)
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

		for (NoVertex adj : this.subGraph.getAllAdjacents()) {
			if (!this.subGraph.isBridge(adj))
				throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

		}
	}

	private void verifyVertex() throws IllegalGraphFormatException {
		
		if (this.subGraph.getAllVertex().size() != this.graph.getAllVertex().size())
			throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");

		for (NoVertex vertex : this.subGraph.getAllVertex()) {
			if (this.graph.getVertex(vertex) == null)
				throw new IllegalGraphFormatException("Is not a Minimal Spanning Tree");
		}

	}

	private void loadHeapMin() {

		// Preparing the HeapMin
		NoVertex firstVertex = this.graph.getAllVertex().iterator().next();
		firstVertex.setWidth(0);
		this.heapMin.insert(firstVertex.getWidth(), firstVertex);
		
		for (NoVertex vertex : this.graph.getAllVertex()) {
			if (!vertex.equals(firstVertex)) {
				vertex.setWidth(Integer.MAX_VALUE);
				this.heapMin.insert(vertex.getWidth(), vertex);
			}
		}

	}

	private void addAllVertexToResultMSTGraph() {
		for (NoVertex vertex : this.graph.getAllVertex()) {
			this.graph.addVertex(vertex.clone());
		}
	}

}
