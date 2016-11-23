package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.Fluery;
import entities.GraphListAdjacent;
import entities.NoVertex;
import exceptions.IllegalGraphFormatException;
import util.ConvertGraphAdjacent;

public class FleuryWithAdjacentListTest {
	
	@Test
	public void Graph01() throws IOException, IllegalGraphFormatException {

		String namePath = "Enter_Graphs/Graph01.txt";
		this.executeFleury(namePath);

	}

	@Test(expected = IllegalGraphFormatException.class)
	public void Graph03DeveRetornarAExcecaoIllegalGraphFormat() throws IOException, IllegalGraphFormatException {

		String namePath = "Enter_Graphs/Graph03.txt";
		this.executeFleury(namePath);

	}

	private void executeFleury(String namePath) throws IOException, IllegalGraphFormatException {

		ConvertGraphAdjacent convert = new ConvertGraphAdjacent();
		GraphListAdjacent graph = convert.convert(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString());

		Fluery fleury = new Fluery(graph);

		NoVertex u = new NoVertex("0");

		fleury.search(u);

	}
}
