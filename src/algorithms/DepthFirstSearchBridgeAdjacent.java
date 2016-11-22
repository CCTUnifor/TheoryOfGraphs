package algorithms;

import entities.GraphAdjacenteList;
import entities.NoVertex;
import enums.Colors;

public class DepthFirstSearchBridgeAdjacent {

	private int time;
	private int numberConnectedComponent;
	private GraphAdjacenteList graph;
	

	public DepthFirstSearchBridgeAdjacent(GraphAdjacenteList graph) {
		this.graph = graph.clone();
		time = 0;
		numberConnectedComponent = 0;
	}
	
	public boolean isBridge(NoVertex destination){
		
		this.graph.removeEdge(destination.getAncestor(), destination);
		this.graph.removeEdge(destination, destination.getAncestor());
		
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
