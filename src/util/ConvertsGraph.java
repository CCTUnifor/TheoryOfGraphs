package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import entities.E;
import entities.Graph;
import entities.V;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IEdge;
import interfaces.IGraph;
import interfaces.IVertex;

public class ConvertsGraph<TVertex, VEdge> {

	public IGraph<TVertex, VEdge> converter(String caminhoArquivo) throws IOException {

		IGraph<TVertex, VEdge> graph = null;

		FileReader arq = new FileReader(caminhoArquivo);
		BufferedReader lerArq = new BufferedReader(arq);

		String linha = lerArq.readLine();

		graph = new Graph<TVertex, VEdge>();

		linha = lerArq.readLine();
		int countX = 0;

		while (linha != null) {

			String[] verticesAdj = linha.split(" ");

			IVertex<TVertex> u = new V<TVertex>(countX + "");

			graph.addVertex(u);

			int countY = 0;

			for (String vertice : verticesAdj) {

				if (countX != countY) {

					if (!(vertice.equals("0") || vertice.equals("inf"))) {
						IVertex<TVertex> v = new V<TVertex>(countY + "");

						graph.addVertex(v);
						try {
							IEdge<VEdge> edge = new E<VEdge>(u, v, (VEdge) vertice);
							graph.addEdge(edge);
						} catch (InvalidVertexException | InvalidEdgeException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
						}

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
