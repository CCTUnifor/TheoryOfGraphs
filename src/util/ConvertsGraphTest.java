package util;

import java.io.IOException;

import org.junit.Test;

import interfaces.IGraph;

public class ConvertsGraphTest {

	@Test
	public void test() throws IOException {
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		
		IGraph<Integer, Integer> graph = convert.converter("Enter_Graphs/Graph01.txt");
		
		System.out.println(graph.toString(false));
		
	}

}
