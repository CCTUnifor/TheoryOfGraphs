package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.GraphAdjacenteList;
import entities.NoVertex;

public class ConvertGraphAdjacent {
	public GraphAdjacenteList converter(String caminhoArquivo) throws IOException {

		GraphAdjacenteList graph = new GraphAdjacenteList();

		FileReader arq = new FileReader(caminhoArquivo);
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
