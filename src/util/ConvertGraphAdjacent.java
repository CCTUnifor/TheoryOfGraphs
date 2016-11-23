package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.GraphListAdjacent;
import entities.NoVertex;
import interfaces.IConvertGraph;

/**
 * <span style='font-size: 16px; font-weight: bold;'>
 * 	Implementing of IConvertGraph. <p>
 * 	Taking a specific .txt file and convert this content from a Graph with Adjacent List Computation Representation. 
 * </span>
 * 
 * @author Thiago Maia
 *
 */
public class ConvertGraphAdjacent implements IConvertGraph {
	
	public GraphListAdjacent convert(String path) throws IOException {

		GraphListAdjacent graph = new GraphListAdjacent();

		FileReader arq = new FileReader(path);
		BufferedReader lerArq = new BufferedReader(arq);

		String linha = lerArq.readLine();

		linha = lerArq.readLine();
		int countX = 0;

		while (linha != null) {

			String[] verticesAdj = linha.split(" ");

			NoVertex u = new NoVertex(countX + "");

			graph.addVertex(u);

			int countY = 0;

			for (String vertice : verticesAdj) {

				if (countX != countY) {

					if (!(vertice.equals("0") || vertice.equals("inf"))) {
						NoVertex v = new NoVertex(countY + "");

						graph.addVertex(v);
						graph.addEdge(u, v, Integer.parseInt(vertice));

					}
				}

				countY++;

			}

			countX++;

			linha = lerArq.readLine();
		}

		return graph;

	}
}
