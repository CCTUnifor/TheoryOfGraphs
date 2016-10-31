package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.DepthFirstSearchBridge;
import algorithms.Fleury;
import entities.V;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import interfaces.IDepthFirstSearchBridge;
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
	
	private void executeFleury(String namePath)
			throws IOException, InvalidVertexException, PathDontFoundedException, IllegalGraphFormatException, InvalidEdgeException {

		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));

		IDepthFirstSearchBridge<Integer, Integer> search = new DepthFirstSearchBridge<Integer, Integer>(graph.clone());
		
		IFleury<Integer, Integer> fleury = new Fleury<Integer, Integer>(graph, search);
		
		IVertex<Integer> u = new V<Integer>("0");
		
		fleury.search(u);
		
		
	}

}
