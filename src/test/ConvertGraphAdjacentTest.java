package test;

import java.io.IOException;

import org.junit.Test;

import entities.GraphListAdjacent;
import interfaces.IConvertGraph;
import util.ConvertGraphAdjacent;

public class ConvertGraphAdjacentTest {

	@Test
	public void Graph01() throws IOException {

		String namePath = "Enter_Graphs/Graph01.txt";

		IConvertGraph convert = new ConvertGraphAdjacent();

		GraphListAdjacent graph = convert.convert(namePath);

		System.out.println(graph.toString());

	}

	@Test
	public void Graph02() throws IOException {

		String namePath = "Enter_Graphs/Graph02.txt";

		IConvertGraph convert = new ConvertGraphAdjacent();

		GraphListAdjacent graph = convert.convert(namePath);

		System.out.println(graph.toString());

	}

	@Test
	public void Graph04() throws IOException {

		String namePath = "Enter_Graphs/Graph04.txt";

		IConvertGraph convert = new ConvertGraphAdjacent();

		GraphListAdjacent graph = convert.convert(namePath);

		System.out.println(graph.toString());

	}

	@Test
	public void Graph05() throws IOException {

		String namePath = "Enter_Graphs/Graph05.txt";

		IConvertGraph convert = new ConvertGraphAdjacent();

		GraphListAdjacent graph = convert.convert(namePath);

		System.out.println(graph.toString());

	}
}
