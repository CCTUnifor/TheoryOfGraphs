package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import algorithms.PrimMinimalSpanningTree;
import entities.GraphAdjacenteList;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IGraph;
import interfaces.IMinimalSpanningTree;
import util.ConvertGraphAdjacent;
import util.ConvertsGraph;

public class Quest02 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println(
				"Tell me 2 graph, and verify if the second graph is a Minimal Spanning Tree from the first graph.\n");
		System.out.println("Select the 2 files: ");

		File directores[] = allGraphsToChose();
		printAllGraphs(directores);

		System.out.println();

		int indexSuperGraph = scanner.nextInt();
		int indexSubGraph = scanner.nextInt();

		File fileSuperGraph = directores[indexSuperGraph - 1];
		File fileSubGraph = directores[indexSubGraph - 1];

		System.out.println(String.format("\nSuper-Graph selected: %s", fileSuperGraph.getName()));
		System.out.println(String.format("Sub-Graph selected: %s", fileSubGraph.getName()));
		
		try {
			executeIsMST(fileSuperGraph.getPath(), fileSubGraph.getPath());
			System.out.print("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\nTrue");
		} catch (IOException | InvalidVertexException | InvalidEdgeException | IllegalGraphFormatException e) {
			System.out.print("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\nFalse");
		}
	}

	private static boolean executeIsMST(String superGraphPath, String subGraphPath)
			throws IOException, InvalidVertexException, InvalidEdgeException, IllegalGraphFormatException {

		ConvertGraphAdjacent convert = new ConvertGraphAdjacent();
		
		GraphAdjacenteList superGraph = null;
		GraphAdjacenteList subGraph = null;
		
		try {
			superGraph = convert.converter(superGraphPath);
			subGraph = convert.converter(subGraphPath);
		} catch (IOException e) {
			throw new IOException("Não foi possível encontrar os arquivos");
		}
		
		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString());

		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString());
		
		/*IMinimalSpanningTree<Integer, Integer> MST = new PrimMinimalSpanningTree<Integer, Integer>(superGraph);
		MST.search();

		return MST.isMinimalSpanningTree(subGraph);*/
		
		/*ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();

		IGraph<Integer, Integer> superGraph = null;
		IGraph<Integer, Integer> subGraph = null;
		try {
			superGraph = convert.converter(superGraphPath);
			subGraph = convert.converter(subGraphPath);
		} catch (IOException e) {
			throw new IOException("Não foi possível encontrar os arquivos");
		}

		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString(false));

		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString(false));

		IMinimalSpanningTree<Integer, Integer> MST = new PrimMinimalSpanningTree<Integer, Integer>(superGraph);
		MST.search();

		return MST.isMinimalSpanningTree(subGraph);*/
	}

	private static File[] allGraphsToChose() {
		File file = new File("Enter_Graphs/");
		File[] directores = file.listFiles();

		return directores;
	}

	private static void printAllGraphs(File[] directores) {

		for (int i = 0; i < directores.length; i++) {
			System.out.println(String.format("%s. %s", i + 1, directores[i].getName()));

		}

	}

}
