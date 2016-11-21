package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.FleuryWIthAdjacentList;
import entities.GraphAdjacenteList;
import entities.NoVertex;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import exceptions.PathDontFoundedException;
import util.ConvertGraphAdjacent;

public class FleuryWithAdjacentListTest {
	
	@Test
	public void Graph01() throws IOException, InvalidVertexException, PathDontFoundedException,
			IllegalGraphFormatException, InvalidEdgeException {

		String namePath = "Enter_Graphs/Graph01.txt";
		this.executeFleury(namePath);

	}

	@Test(expected = IllegalGraphFormatException.class)
	public void Graph03DeveRetornarAExcecaoIllegalGraphFormat() throws IOException, InvalidVertexException,
			PathDontFoundedException, IllegalGraphFormatException, InvalidEdgeException {

		String namePath = "Enter_Graphs/Graph03.txt";
		this.executeFleury(namePath);

	}

	private void executeFleury(String namePath) throws IOException, InvalidVertexException, PathDontFoundedException,
			IllegalGraphFormatException, InvalidEdgeException {

		ConvertGraphAdjacent convert = new ConvertGraphAdjacent();
		GraphAdjacenteList graph = convert.converter(namePath);

		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString());

		FleuryWIthAdjacentList fleury = new FleuryWIthAdjacentList(graph);

		NoVertex u = new NoVertex("0");

		fleury.search(u);

	}
}
