package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.PrimMinimalSpanningTree;
import exceptions.IllegalGraphFormatException;
import interfaces.IGraph;
import interfaces.IMinimalSpanningTree;
import util.ConvertsGraph;

public class IsMST {

	@Test
	public void SuperGraphIs04_SubGraphIs05() throws IOException, IllegalGraphFormatException {
		
		String namePath = "Enter_Graphs/Graph04.txt";
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> superGraph = convert.converter(namePath);

		namePath = "Enter_Graphs/Graph05.txt";
		
		IGraph<Integer, Integer> subGraph = convert.converter(namePath);
		
		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString(false));
		
		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString(false));
		
		IMinimalSpanningTree<Integer, Integer> MST = new PrimMinimalSpanningTree<Integer, Integer>(superGraph);
		
		boolean isMST = MST.isMinimalSpanningTree(subGraph);
		
		
	}

}
