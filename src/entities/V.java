package entities;

import java.util.LinkedHashSet;
import java.util.Set;

import enums.Colors;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IVertex;

public class V<T> implements IVertex<T> {

	private Set<IEdge<?>> adjacenteEdges;
	private String label;
	private int positionArray;
	private T data;
	
	private Colors color;
	private IVertex<T> ancestor;
	private int timeStart;
	private int timeFinish;

	public V(String label) {
		this.adjacenteEdges = new LinkedHashSet<IEdge<?>>();
		this.label = label;
		this.resetConfigs();
	}
	
	public V(String label, T data) {
		this.adjacenteEdges = new LinkedHashSet<IEdge<?>>();
		this.label = label;
		this.data = data;
		
		this.resetConfigs();
	}

	public V(int positionArray, String label, T data) {
		this.adjacenteEdges = new LinkedHashSet<IEdge<?>>();
		this.label = label;
		this.positionArray = positionArray;
		this.data = data;
		
		this.resetConfigs();
	}

	public void resetConfigs(){
		this.color = Colors.WHITE;
		this.ancestor = null;
		this.timeStart = Integer.MAX_VALUE;
		this.timeFinish = Integer.MAX_VALUE;
	}
	
	@Override
	public String getLabel() {
		return this.label;
	}

	@Override
	public T getData() {
		return this.data;
	}

	@Override
	public boolean containsEdge(IEdge<?> edgeTarget) throws InvalidEdgeException {
		return (this.adjacenteEdges.contains(edgeTarget));
	}

	@Override
	public Set<IEdge<?>> getAllEdge() {
		return this.adjacenteEdges;
	}

	@Override
	public boolean containsPathTo(IVertex<T> destination) throws InvalidVertexException {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public int getDegree() {
		return this.adjacenteEdges.size();
	}

	@Override
	public boolean isAdjacent(IVertex<T> destination) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Not implement yet");
	}

	@Override
	public boolean addEdge(IEdge<?> edgeToAdd) {
		return this.adjacenteEdges.add(edgeToAdd);
	}

	@Override
	public boolean removeEdge(IEdge<?> edgeToRemove) {

		return this.adjacenteEdges.remove(edgeToRemove);
	}

	@Override
	public boolean removeAllEdge() {
		this.adjacenteEdges = new LinkedHashSet<IEdge<?>>();

		return true;
	}

	@Override
	public int positionArray() {
		return positionArray;
	}

	@Override
	public String toString() {
		return this.toString(false);
	}

	@Override
	public String toString(boolean withData) {
		if (withData)
			return String.format("%s[%s]", this.getLabel(), this.getData());
		
		return String.format("%s",  this.getLabel());
	}
	
	@Override
	public boolean equals(Object object) {
		
		if (object == null)
			return false;

		IVertex<T> vertex = (IVertex<T>) object;

		if (this.label.equals(vertex.getLabel()))
			return true;

		return false;

	}

	@Override
	public Colors getColor() {
		return this.color;
	}

	@Override
	public void setColor(Colors color) {
		this.color = color;
	}

	@Override
	public IVertex<T> getAncestor() {
		return this.ancestor;
	}

	@Override
	public void setAncestor(IVertex<T> ancestor) {
		this.ancestor = ancestor;
	}

	@Override
	public int getTimeStart() {
		return this.timeStart;
	}

	@Override
	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	@Override
	public int getTimeFinish() {
		return this.timeFinish;
	}

	@Override
	public void setTimeFinish(int timeFinish) {
		this.timeFinish = timeFinish;
	}

	@Override
	public boolean isLeaf() { 
		return (this.adjacenteEdges.size() == 0); // DANGER!! 
	}

}
