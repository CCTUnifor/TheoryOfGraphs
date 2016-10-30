package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.DepthFirstSearch;
import entities.V;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import interfaces.IGraph;
import interfaces.IGraphFirstSearch;
import interfaces.IVertex;
import util.ConvertsGraph;

public class DepthFirstSearchTest {

	@Test
	public void Graph01SerchPathToVertex00From00()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "0";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test
	public void Graph01SerchPathToVertex00From01()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "1";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test
	public void Graph01SerchPathToVertex00From02()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "2";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test
	public void Graph01SerchPathToVertex00From03()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "3";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test
	public void Graph01SerchPathToVertex00From04()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "4";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test
	public void Graph01SerchPathToVertex00From08()
			throws IOException, InvalidVertexException, PathDontFoundedException {

		String source = "0";
		String destination = "8";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	@Test(expected = PathDontFoundedException.class)
	public void Graph01SerchPathToVertex07From01()
			throws IOException, PathDontFoundedException, InvalidVertexException {

		String source = "7";
		String destination = "1";
		String namePath = "Enter_Graphs/Graph01.txt";

		this.search(namePath, source, destination);
	}

	private void search(String namePath, String source, String destination)
			throws IOException, InvalidVertexException, PathDontFoundedException {

		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));

		System.out.println("Execute DFS...\n");

		IGraphFirstSearch<Integer, Integer> DFS = new DepthFirstSearch<Integer, Integer>(graph);
		IGraph<Integer, Integer> graphSearched = DFS.search();

		System.out.println("Executed DFS\n");

		IVertex<Integer> u = new V<Integer>(source, 1);
		IVertex<Integer> v = new V<Integer>(destination, 1);

		System.out.println("Search to " + source + " from " + destination + ":\n");

		System.out.println(DFS.pathToFrom(u, v));
	}

}
