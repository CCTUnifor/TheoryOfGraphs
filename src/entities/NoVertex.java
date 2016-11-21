package entities;

import java.util.LinkedHashSet;
import java.util.Set;

import enums.Colors;

public class NoVertex {

	private Set<NoVertex> listAdjacente;

	private String label;
	private int widthVertex;
	private int widthEdge;
	
	private Colors color;
	private NoVertex ancestor;
	private int timeStart;
	private int timeFinish;
	
	public NoVertex(String label){
		this(label, 0);
	}
	
	public NoVertex(String label, int width){
		this(label, width, 0);
	}
	
	public NoVertex(String label, int width, int widthEdge) {
		this.listAdjacente = new LinkedHashSet<NoVertex>();
		this.label = label;
		this.widthVertex = width;
		this.widthEdge = widthEdge;
		
		this.color = Colors.WHITE;
		this.ancestor = null;
		this.timeStart = 0;
		this.timeFinish = 0;		
	}
	
	public Set<NoVertex> getAdjacentes(){
		return this.listAdjacente;
	}
	
	public boolean addAdjacente(NoVertex noVertex){
		return this.listAdjacente.add(noVertex);
	}
	
	public boolean removeAdjacente(NoVertex noVertex){
		noVertex = this.find(noVertex);
		if (noVertex != null)
			return this.listAdjacente.remove(noVertex);
		return false;
	}
	
	public boolean isAdjacent(NoVertex destination){
		return this.listAdjacente.stream().filter(x -> x.equals(destination)).findFirst() != null;
	}
	
	public boolean isLeaf(){
		return (this.listAdjacente.size() == 0);
	}
	
	public int getDegree(){
		return this.listAdjacente.size();
	}
	
	@Override
	public boolean equals(Object object){
		if (object == null)
			return false;

		NoVertex vertex = (NoVertex) object;

		if (this.label.equals(vertex.getLabel()))
			return true;

		return false;

	}
	
	@Override
	public String toString(){
		return String.format("%s[%s]", this.label, this.widthVertex);
	}
	
	private NoVertex find(NoVertex noVertex){
		for (NoVertex no : listAdjacente) {
			if (noVertex.equals(no))
				return no;
		}
		return null;
	}
	
	public int getWidthEdge(){
		return this.widthEdge;
	}
	
	public void setWidthEdge(int widthEdge){
		this.widthEdge = widthEdge;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getWidth() {
		return widthVertex;
	}

	public void setWidth(int width) {
		this.widthVertex = width;
	}

	public Colors getColor() {
		return color;
	}

	public void setColor(Colors color) {
		this.color = color;
	}

	public NoVertex getAncestor() {
		return ancestor;
	}

	public void setAncestor(NoVertex ancestor) {
		this.ancestor = ancestor;
	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public int getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(int timeFinish) {
		this.timeFinish = timeFinish;
	}
	
	
	
}
