package interfaces;

import java.util.List;

import entities.NoVertex;
import exceptions.IllegalGraphFormatException;

public abstract class IFleury {

	protected IGraph originalGraph;
	protected List<NoVertex> eulerianTourSequence;

	/**
	 * <span style="font-size: 16px"> 
	 * 	While the number of edges the original graph is different to 0(zero):<br>
	 * 	<ol>
	 * 		<li>Verify if this source is leaf. Returning a Throw Exception if True.</li>
	 * 		<li>Get the next preferably not bridge edge.</li>
	 * 		<li>Adding this vertex destination of got edge in the Eulerian Tour Sequence.</li>
	 * 		<li>Remove this edge from the graph(this edge and your reverse edge).</li>
	 * 		<li>Attribute the adjacent(destination) vertex this edge in the source variable.</li>
	 * </ol>
	 * <p>
	 * When this loop over, return the Eulerian Tour Sequence. 
	 * </span>
	 * 
	 * @param source
	 *            Type: NoVertex. Vertex to start the algorithm
	 * @return Returns a Eulerian Tour Sequence from that Graph passed by the
	 *         Constructor and started from the source vertex.
	 * @throws IllegalGraphFormatException
	 */
	public abstract List<NoVertex> search(NoVertex source) throws IllegalGraphFormatException;

	/**
	 * Print the Eulerian Tour Sequence.
	 */
	public void printEulerianSequence() {
		System.out.print("[ " + this.eulerianTourSequence.iterator().next().getLabel());

		for (int i = 1; i < this.eulerianTourSequence.size(); i++) {
			NoVertex no = this.eulerianTourSequence.get(i);
			System.out.print(String.format(" -> %s", no.getLabel()));
		}

		System.out.print(" ]\n\n");
	}

}
