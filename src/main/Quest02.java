package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import algorithms.Prim;
import exceptions.IllegalGraphFormatException;
import interfaces.IConvertGraph;
import interfaces.IGraph;
import util.ConvertGraphAdjacent;

public class Quest02 {

	public static void main(String[] args) throws IOException {
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
		} catch (IllegalGraphFormatException e) {
			System.out.print("\nThis sub-graph is a Minimal Spanning Tree from this super-graph?\nFalse");
		}
	}

	private static boolean executeIsMST(String superGraphPath, String subGraphPath)
			throws IOException, IllegalGraphFormatException {

		IConvertGraph convert = new ConvertGraphAdjacent();
		
		IGraph superGraph = null;
		IGraph subGraph = null;
		
		try {
			superGraph = convert.convert(superGraphPath);
			subGraph = convert.convert(subGraphPath);
		} catch (IOException e) {
			throw new IOException("Não foi possível encontrar os arquivos");
		}
		
		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString());

		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString());
		
		Prim MST = new Prim(superGraph);
		
		return MST.isMinimalSpanningTree(subGraph);
		
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
