package algorithms;

import entities.NoVertex;
import enums.Colors;
import interfaces.IGraph;

public class DepthFirstSearchListAdjacent {

	private int time;
	private int numberConnectedComponent;
	private IGraph graph;
	

	public DepthFirstSearchListAdjacent(IGraph graph) {
		this.graph = graph.clone();
		time = 0;
		numberConnectedComponent = 0;
	}
	
	public boolean isBridge(NoVertex source, NoVertex destination){
		
		this.graph.removeEdge(source, destination);
		this.graph.removeEdge(destination, source);
		
		for (NoVertex vertex : this.graph.getAllVertex()) {
			if (vertex.getColor() == Colors.WHITE){
				
				this.numberConnectedComponent++;
				
				if (this.numberConnectedComponent > 1)
					return true;
				
				this.runDFS(vertex);
			}
		}
		if (this.numberConnectedComponent > 1)
			return true;
		
		return false;
	}
	
	private void runDFS(NoVertex source){
		source.setColor(Colors.GREY);
		source.setTimeStart(++time);
		
		if (!this.graph.isEmpty()){
			for (NoVertex adj : source.getAdjacentes()) {
				adj = this.graph.getVertex(adj); // taking the reference from the Graph
				if (adj.getColor() == Colors.WHITE){
					adj.setAncestor(source);
					this.runDFS(adj);
				}
			}
		}
		source.setColor(Colors.BLACK);
		source.setTimeFinish(++time);
	}
	
	public int getNumberConnectedComponent(){
		
		for (NoVertex vertex : this.graph.getAllVertex()) {
			if (vertex.getColor() == Colors.WHITE){
				this.numberConnectedComponent++;
				
				this.runDFS(vertex);
			}
		}
		
		return this.numberConnectedComponent;
		
	}
	
}
