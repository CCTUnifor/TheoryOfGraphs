package entities;

import java.util.LinkedHashSet;
import java.util.Set;

public class GraphAdjacenteList {
	
	private Set<NoVertex> vertexs;
	
	public GraphAdjacenteList(){
		this.vertexs = new LinkedHashSet<NoVertex>();
	}
	
	public NoVertex getVertex(NoVertex vertexTarget){
		return this.vertexs.stream().filter(x -> x.equals(vertexTarget)).findFirst().orElse(null);
	}
	
	public boolean isEmpty(){
		return (this.vertexs.size() == 0);
	}
	
	public int degree(){
		int countDegree = 0;

		for (NoVertex iVertex : vertexs) {
			countDegree += iVertex.getDegree();
		}

		return countDegree;
	}
	
	public int degreeOf(NoVertex vertex){
		return this.getVertex(vertex).getDegree();
	}
	
	public Set<NoVertex> getAllVertex(){
		return this.vertexs;
	}
	
	public boolean addVertex(NoVertex vertexToAdd){
		if (this.getVertex(vertexToAdd) == null)
			return this.vertexs.add(vertexToAdd);
		return false;
	}
	
	public boolean removeVertex(NoVertex vertexToRemove){
		NoVertex no = this.getVertex(vertexToRemove);
		if (no != null)
			return this.vertexs.remove(no);
		
		return false;
	}
	
	public boolean removeAllVertex() {
		this.vertexs = new LinkedHashSet<NoVertex>();
		return true;
	}
	
	public boolean addEdge(NoVertex source, NoVertex destination){
		return this.addEdge(source, destination, 0);
	}
	
	public boolean addEdge(NoVertex source, NoVertex destination, int widthEdge){
		source = this.getVertex(source);
		//destination = this.getVertex(destination);
		
		if (source == null && this.getVertex(destination) == null)
			return false;
		
		destination.setWidthEdge(widthEdge);
		//destination = new NoVertex(destination.getLabel(), destination.getWidth());
		//destination.setWidthEdge(widthEdge);
		
		return source.addAdjacente(destination);
	}
	
	public Set<NoVertex> getAdjacents(NoVertex vertexTarget){
		NoVertex no = this.getVertex(vertexTarget);

		if (no == null)
			return null;
		
		return no.getAdjacentes();
		
	}
	
	public boolean areAdjacents(NoVertex source, NoVertex destination){
		if(this.getVertex(source) != null && this.getVertex(destination) != null){
			source = this.getVertex(source);
			destination = this.getVertex(destination);
			
			return source.isAdjacent(destination);
		}
		return false;
	}
	
	@Override
	public String toString(){
		String message = "";
		
		for (NoVertex source : vertexs) {
			message += String.format("%s => {", source.getLabel());
			
			for (NoVertex destination : source.getAdjacentes()) {
				message += String.format(" %s[%s]", destination.getLabel(), destination.getWidthEdge());
			}
			
			message += " }\n";
		}
		
		return message;
	}
	
}
