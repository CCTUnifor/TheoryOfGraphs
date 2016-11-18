package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.PrimMinimalSpanningTree;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IGraph;
import interfaces.IMinimalSpanningTree;
import util.ConvertsGraph;

public class IsMST {

	@Test
	public void SuperGraphIs07_SubGraphIs08() throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		String superGraphPath = "Enter_Graphs/Graph07.txt";
		String subGraphPath = "Enter_Graphs/Graph08.txt";
		
		boolean isMST = this.isMST(superGraphPath, subGraphPath);
		
		System.out.println(String.format("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\n%s", isMST, isMST));
		
	}
	
	@Test(expected = IllegalGraphFormatException.class)
	public void ThrowWhenPassFirstTheSuperGraph() throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		String superGraphPath = "Enter_Graphs/Graph05.txt";
		String subGraphPath = "Enter_Graphs/Graph06.txt";
		
		boolean isMST = this.isMST(superGraphPath, subGraphPath);
		
		System.out.println(String.format("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\n%s", isMST, isMST));
		
	}
	
	@Test
	public void SuperGraphIs04_SubGraphIs05() throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		String superGraphPath = "Enter_Graphs/Graph04.txt";
		String subGraphPath = "Enter_Graphs/Graph05.txt";
		
		boolean isMST = this.isMST(superGraphPath, subGraphPath);
		
		System.out.println(String.format("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\n%s", isMST, isMST));
		
	}

	@Test(expected = IllegalGraphFormatException.class)
	public void ThrowWhenPassTheSameGraphs() throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		String superGraphPath = "Enter_Graphs/Graph06.txt";
		String subGraphPath = "Enter_Graphs/Graph06.txt";
		
		boolean isMST = this.isMST(superGraphPath, subGraphPath);
		
		System.out.println(String.format("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\n%s", isMST, isMST));
		
	}
	

	@Test(expected = IllegalGraphFormatException.class)
	public void ThrowWhenTheWidthOfEdgesIsDiferents() throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		String superGraphPath = "Enter_Graphs/Graph09.txt";
		String subGraphPath = "Enter_Graphs/Graph10.txt";
		
		boolean isMST = this.isMST(superGraphPath, subGraphPath);
		
		System.out.println(String.format("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\n%s", isMST, isMST));
		
	}
	
	
	private boolean isMST(String superGraphPath, String subGraphPath) throws IOException, InvalidVertexException, InvalidEdgeException, IllegalGraphFormatException{
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> superGraph = convert.converter(superGraphPath);
		IGraph<Integer, Integer> subGraph = convert.converter(subGraphPath);
		
		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString(false));
		
		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString(false));
		
		IMinimalSpanningTree<Integer, Integer> MST = new PrimMinimalSpanningTree<Integer, Integer>(superGraph);
		MST.search();
		
		return MST.isMinimalSpanningTree(subGraph);
	}

}
