package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.Fleury;
import entities.V;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import interfaces.IFleury;
import interfaces.IGraph;
import interfaces.IVertex;
import util.ConvertsGraph;

public class FleuryTest {

	@Test
	public void Graph01() throws IOException, InvalidVertexException, PathDontFoundedException, IllegalGraphFormatException, InvalidEdgeException {
		
		String namePath = "Enter_Graphs/Graph01.txt";
		this.executeFleury(namePath);
		
	}
	
	@Test(expected = IllegalGraphFormatException.class)
	public void Graph03DeveRetornarAExcecaoIllegalGraphFormat() throws IOException, InvalidVertexException, PathDontFoundedException, IllegalGraphFormatException, InvalidEdgeException {
		
		String namePath = "Enter_Graphs/Graph03.txt";
		this.executeFleury(namePath);
		
	}
	
	private void executeFleury(String namePath)
			throws IOException, InvalidVertexException, PathDontFoundedException, IllegalGraphFormatException, InvalidEdgeException {

		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));

		IFleury<Integer, Integer> fleury = new Fleury<Integer, Integer>(graph);
		
		IVertex<Integer> u = new V<Integer>("0");
		
		fleury.search(u);
		
	}

}
