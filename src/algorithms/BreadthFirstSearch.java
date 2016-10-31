package algorithms;

import java.util.LinkedList;
import java.util.Queue;

import enums.Colors;
import exceptions.InvalidVertexException;
import interfaces.GraphFirstSearch;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class BreadthFirstSearch<V, E> extends GraphFirstSearch<V, E> {

	public BreadthFirstSearch(IGraph<V, E> graph){
		this.graph = graph.clone();
		
	}
	
	@Override
	public IGraph<V, E> search() {
		
		for (IVertex<V> iVertex : this.graph.getAllVertex()) {
			if (iVertex.getColor() == Colors.WHITE) {
				this.runBFS(iVertex);
			}
		}
		
		return this.graph;
	}

	@Override
	public IGraph<V, E> search(IVertex<V> source) {
		this.runBFS(source);
		
		return this.graph;
	}
	
	private void runBFS(IVertex<V> source){
		source.setTimeFinish(0);
		source.setColor(Colors.GREY);
		
		Queue<IVertex<V>> q = new LinkedList<IVertex<V>>();
		q.add(source);
		
		while(!q.isEmpty()){
			source = q.poll();
			
			if (!source.getAllEdge().isEmpty())
				this.organizeAllVexter(q, source);
			
			source.setColor(Colors.BLACK);
			
		}
		
	}

	private void organizeAllVexter(Queue<IVertex<V>> q, IVertex<V> source){
		
		for (IEdge<?> iEdge : source.getAllEdge()) {
			iEdge = (IEdge<E>)iEdge;
			
			IVertex<V> target = (IVertex<V>) iEdge.getDestination();
			
			try {
				target = this.graph.getVertex(target);
			} catch (InvalidVertexException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (target.getColor() == Colors.WHITE) {
				
				this.discoverVertex(source, target);
				q.add(target);
			}
			
		}
		
	}
	
	private void  discoverVertex(IVertex<V> source, IVertex<V> target){
		target.setColor(Colors.GREY);
		target.setAncestor(source);
		target.setTimeFinish(source.getTimeFinish() + 1);
	}


	
	
}
