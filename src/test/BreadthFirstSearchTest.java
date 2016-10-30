package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.BreadthFirstSearch;
import entities.V;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import interfaces.IBreadthFirstSearch;
import interfaces.IGraph;
import interfaces.IGraphFirstSearch;
import interfaces.IVertex;
import util.ConvertsGraph;

public class BreadthFirstSearchTest {

	@Test
	public void Graph01() throws IOException, InvalidVertexException, PathDontFoundedException {

		String namePath = "Enter_Graphs/Graph01.txt";

		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));
		
		System.out.println("BFS\n");
		
		IGraphFirstSearch BFS = new BreadthFirstSearch(graph);
		
		IGraph<Integer, Integer> graphSearched = BFS.search();
		
		IVertex<Integer> u = new V<Integer>("0", 1);
		IVertex<Integer> v = new V<Integer>("2", 1);
		
		System.out.println(BFS.pathToFrom(u, v));
		
	}

}
