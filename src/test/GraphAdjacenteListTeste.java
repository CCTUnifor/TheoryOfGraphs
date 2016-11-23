package test;

import org.junit.Test;

import entities.GraphListAdjacent;
import entities.NoVertex;

public class GraphAdjacenteListTeste {

	@Test
	public void test() {
		
		GraphListAdjacent graph = new GraphListAdjacent();
		
		NoVertex A = new NoVertex("A", 10); 
		NoVertex B = new NoVertex("B", 2);
		NoVertex C = new NoVertex("C", 6);
		
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		
		graph.addEdge(A, B, 10);
		graph.addEdge(B, A, 10);
		graph.addEdge(A, C, 2);
		
		System.out.println(graph.toString());
		
	}

}
