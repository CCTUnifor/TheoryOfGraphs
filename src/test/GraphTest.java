package test;

import org.junit.Test;

import entities.E;
import entities.Graph;
import entities.V;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class GraphTest {

	@Test
	public void test() throws InvalidVertexException, InvalidEdgeException {
		
		IGraph<Integer, Integer> graph = new Graph<Integer, Integer>();
		
		IVertex<Integer> A = new V<Integer>("A", 10); 
		IVertex<Integer> B = new V<Integer>("B", 2);
		IVertex<Integer> C = new V<Integer>("C", 6);
		
		graph.addVertex(A);
		graph.addVertex(B);
		graph.addVertex(C);
		
		IEdge<Integer> AB = new E<Integer>(A, B, 3);
		IEdge<Integer> BA = AB.getReverse();
		IEdge<Integer> AC = new E<Integer>(A,C, 10);
		IEdge<Integer> CA = AC.getReverse();
		
		graph.addEdge(AB);
		graph.addEdge(BA);
		graph.addEdge(AC);
		graph.addEdge(CA);
		
		System.out.println(graph.toString(true));
	}

}
