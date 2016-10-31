package algorithms;

import entities.Graph;
import entities.V;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
import interfaces.IEdge;
import interfaces.IFleury;
import interfaces.IGraph;
import interfaces.IVertex;

public class Fleury<Ve, E> implements IFleury<Ve, E> {

	private IGraph<Ve, E> originalGraph;
	private IGraph<Ve, E> tourEuler;
	IDepthFirstSearchBridge<Ve, E> search;
	
	public Fleury(IGraph<Ve, E> graph, IDepthFirstSearchBridge<Ve, E> search) {
		this.originalGraph = graph;
		this.tourEuler = new Graph<Ve, E>();
		this.search = search;
	}

	@Override
	public IGraph<Ve, E> search(IVertex<Ve> source) throws IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		source = this.originalGraph.getVertex(source);
		
		while (!this.originalGraph.isEmpty()) {
			
			if (source.isLeaf())
				throw new IllegalGraphFormatException("Not Eulerian Graph");
			
			IEdge<E> chosenEdge = this.getNotBridge(source);
			
			this.addVertexEdgesToTour(chosenEdge);
			this.removeEdgesToOrinalGraph(chosenEdge);
			
			source = (IVertex<Ve>) chosenEdge.getDestination();
			
		}
		
		return this.tourEuler;
	}

	private IEdge<E> getNotBridge(IVertex<Ve> source) throws InvalidEdgeException, InvalidVertexException{
		IEdge<E> edge = (IEdge<E>) source.getAllEdge().iterator().next();
		
		for (IEdge<?> iEdge : source.getAllEdge()) {
			
			boolean isBridge = this.originalGraph.isBridge(search, (IEdge<E>) iEdge); 
			this.originalGraph.resetConfigs();
			
			if (!isBridge){
				return (IEdge<E>) iEdge;
			}
			
			edge = (IEdge<E>) iEdge;
		}
		
		return edge;
	}
	
	private void addVertexEdgesToTour(IEdge<E> edge) throws InvalidVertexException, InvalidEdgeException{
		IVertex<Ve> u = new V<Ve>(edge.getSource().getLabel(), (Ve) edge.getSource().getData());
		IVertex<Ve> v = new V<Ve>(edge.getDestination().getLabel(), (Ve) edge.getDestination().getData());
		
		this.tourEuler.addVertex(u);
		this.tourEuler.addVertex(v);
		
		this.tourEuler.addEdge(edge);
		this.tourEuler.addEdge(edge.getReverse());
	}
	
	private void removeEdgesToOrinalGraph(IEdge<E> edge) throws InvalidVertexException, InvalidEdgeException{
		this.originalGraph.removeEdge(edge);
		this.originalGraph.removeEdge(edge.getReverse());
	}
	
}
