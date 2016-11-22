package interfaces;

import java.util.List;

import entities.GraphAdjacenteList;
import entities.NoVertex;
import exceptions.IllegalGraphFormatException;

public abstract class IFleury {
		
	protected GraphAdjacenteList originalGraph;
	protected List<NoVertex> eulerianTourSequence;

	/**
	 * Returns a Eulerian Tour Sequence from this Graph starts of the source vertex.
	 * 
	 * @param source Type: NoVertex. Vertex to start the algorithm
	 * @return List<NoVertex>
	 * @throws IllegalGraphFormatException
	 */
	public abstract List<NoVertex> search(NoVertex source) throws IllegalGraphFormatException;
	
	
	/**
	 * Print the Eulerian Tour Sequence.
	 */
	public void printEulerianSequence(){
		System.out.print("[ " + this.eulerianTourSequence.iterator().next().getLabel());
		
		for (int i = 1; i < this.eulerianTourSequence.size(); i++) {
			NoVertex no = this.eulerianTourSequence.get(i);
			System.out.print(String.format(" -> %s", no.getLabel()));
		}
		
		System.out.print(" ]\n\n");
	}
	
}
