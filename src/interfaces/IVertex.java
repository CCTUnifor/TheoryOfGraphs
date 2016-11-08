package interfaces;

import java.util.Set;

import enums.Colors;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;

public abstract interface IVertex<T> {

	String getLabel();
	T getData();
	void resetConfigs();
	
	boolean isLeaf();
	
	boolean containsEdge(IEdge<?> edgeTarget) throws InvalidEdgeException;
	
	Set<IEdge<?>> getAllEdge();
	
	boolean addEdge(IEdge<?> edgeToAdd);
	
	boolean removeEdge(IEdge<?> edgeToRemove);
	
	boolean removeAllEdge();
	
	boolean containsPathTo(IVertex<T> destination) throws InvalidVertexException;
	
	int getDegree();
	
	boolean equals(Object object);
	
	boolean isAdjacent(IVertex<T> destination);
	
	int positionArray();
	
	Colors getColor();
	void setColor(Colors color);
	
	IVertex<T> getAncestor();
	void setAncestor(IVertex<T> ancestor);
	
	int getTimeStart();
	void setTimeStart(int timeStart);
	
	int getTimeFinish();
	void setTimeFinish(int timeFinish);
	
	int getWidth();
	void setWidth(int width);
	
	String toString();
	String toString(boolean withData);
	
	IVertex<T> clone();
	
}
