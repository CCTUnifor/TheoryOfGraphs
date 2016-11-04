package test;

import java.io.IOException;

import org.junit.Test;

import enums.GraphPrintPresentation;
import interfaces.IGraph;
import util.ConvertsGraph;

public class ConvertsGraphTest {

	@Test
	public void Graph01() throws IOException {
		
		String namePath = "Enter_Graphs/Graph01.txt";
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> graph = convert.converter(namePath);
		
		System.out.println(graph.toString(false));
		
	}

	@Test
	public void Graph02() throws IOException {
		
		String namePath = "Enter_Graphs/Graph02.txt";
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> graph = convert.converter(namePath);
		
		System.out.println(graph.toString(true));
		
	}
	
	@Test
	public void Graph04() throws IOException {
		
		String namePath = "Enter_Graphs/Graph04.txt";
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> graph = convert.converter(namePath);
		
		System.out.println(graph.toString(GraphPrintPresentation.ADJACENT_WITHDATA));
		
	}
	
	@Test
	public void Graph05() throws IOException {
		
		String namePath = "Enter_Graphs/Graph05.txt";
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> graph = convert.converter(namePath);
		
		System.out.println(graph.toString(GraphPrintPresentation.ADJACENT_WITHDATA));
		
	}
	
}
