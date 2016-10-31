package algorithms;

import java.util.Iterator;
import java.util.Set;

import entities.E;
import entities.Graph;
import entities.V;
import enums.GraphPrintPresentation;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
import interfaces.IEdge;
import interfaces.IFleury;
import interfaces.IGraph;
import interfaces.IVertex;

public class Fleury<Ve, Ee> implements IFleury<Ve, Ee> {

	private IGraph<Ve, Ee> originalGraph;
	private IGraph<Ve, Ee> tourEuler;
	IDepthFirstSearchBridge<Ve, Ee> search;

	public Fleury(IGraph<Ve, Ee> graph, IDepthFirstSearchBridge<Ve, Ee> search) {
		this.originalGraph = graph.clone();
		this.tourEuler = new Graph<Ve, Ee>();
		//this.tourEuler.addVertex(this.originalGraph.getAllVertex());
		this.search = search;
	}

	@SuppressWarnings("unchecked")
	@Override
	public IGraph<Ve, Ee> search(IVertex<Ve> source)
			throws IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {

		source = this.originalGraph.getVertex(source);

		int iteration = 0;

		while (!this.originalGraph.isEmpty()) {

			iteration++;
			if (source.isLeaf())
				throw new IllegalGraphFormatException("Not Eulerian Graph");

			System.out.println("------------------------ #" + iteration + " ------------------------------\n");

			IEdge<Ee> chosenEdge = this.getNotBridge(source);

			this.addVertexEdgesToTour(chosenEdge);
			this.removeEdgesToOrinalGraph(chosenEdge);

			System.out.println(String.format("\nChosen Edge: %s\n", chosenEdge.toString()));

			System.out.println("Eulerian Tour: \n");
			System.out.println(this.tourEuler.toString(GraphPrintPresentation.ADJACENT));

			source = this.originalGraph.getVertex((IVertex<Ve>) chosenEdge.getDestination());

		}

		return this.tourEuler;
	}

	@SuppressWarnings("unchecked")
	private IEdge<Ee> getNotBridge(IVertex<Ve> source) throws InvalidEdgeException, InvalidVertexException {

		IEdge<Ee> edge = (IEdge<Ee>) source.getAllEdge().iterator().next();

		this.originalGraph.resetConfigs();

		Set<IEdge<?>> allEdges = source.getAllEdge();
		Iterator<IEdge<?>> iterator = allEdges.iterator();
		int count = allEdges.size();

		for (int i = 0; i < count; i++) {
			IEdge<?> iEdge = iterator.next();

			int si = allEdges.size();

			boolean isBridge = this.originalGraph.isBridge(search, (IEdge<Ee>) iEdge);

			System.out.println(String.format("Visited Edge: %s", iEdge.toString()));
			System.out.println(String.format("   Is bridge: %s", isBridge));

			if (!isBridge) {
				return (IEdge<Ee>) iEdge;
			}

			edge = (IEdge<Ee>) iEdge;
		}

		return edge;
	}

	@SuppressWarnings("unchecked")
	private void addVertexEdgesToTour(IEdge<Ee> edge) throws InvalidVertexException, InvalidEdgeException {
		IVertex<Ve> u = new V<Ve>(edge.getSource().getLabel(), (Ve) edge.getSource().getData());
		IVertex<Ve> v = new V<Ve>(edge.getDestination().getLabel(), (Ve) edge.getDestination().getData());

		this.tourEuler.addVertex(u);
		this.tourEuler.addVertex(v);

		this.tourEuler.addEdge(edge);
		this.tourEuler.addEdge(edge.getReverse());
	}

	@SuppressWarnings("unchecked")
	private void removeEdgesToOrinalGraph(IEdge<Ee> edge) throws InvalidVertexException, InvalidEdgeException {
		IVertex<Ve> u = new V<Ve>(edge.getSource().getLabel(), (Ve) edge.getSource().getData());
		IVertex<Ve> v = new V<Ve>(edge.getDestination().getLabel(), (Ve) edge.getDestination().getData());

		IEdge<Ee> UV = new E<Ee>(u, v, edge.getData());

		this.originalGraph.removeEdge(UV);
		this.originalGraph.removeEdge(UV.getReverse());
	}

}
