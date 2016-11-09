package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.PrimMinimalSpanningTree;
import enums.GraphPrintPresentation;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import interfaces.IGraph;
import interfaces.IMinimalSpanningTree;
import util.ConvertsGraph;

public class PrimTeste {

	@Test
	public void test() throws IOException, InvalidVertexException, InvalidEdgeException {
		String namePath = "Enter_Graphs/Graph06.txt";
		
		this.executePrim(namePath);
	}
	
	private void executePrim(String namePath) throws IOException, InvalidVertexException, InvalidEdgeException{

		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));

		IMinimalSpanningTree<Integer, Integer> MST = new PrimMinimalSpanningTree<Integer, Integer>(graph);
		
		System.out.println(MST.search().toString(GraphPrintPresentation.ADJACENT));
		
	}

}
