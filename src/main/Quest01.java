package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import algorithms.DepthFirstSearchBridge;
import algorithms.Fleury;
import entities.V;
import exceptions.IllegalGraphFormatException;
import exceptions.InvalidEdgeException;
import exceptions.InvalidVertexException;
import interfaces.IDepthFirstSearchBridge;
import interfaces.IFleury;
import interfaces.IGraph;
import interfaces.IVertex;
import util.ConvertsGraph;

public class Quest01 {
	
	File directores[];
	
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Select a file: ");
		
		File directores[] = allGraphsToChose();
		printAllGraphs(directores);
		
		System.out.println();
		
		int indexFileChosen = scanner.nextInt();
		
		File fileChosen = directores[indexFileChosen-1];
		String fileName = fileChosen.getName();
		String filePath = fileChosen.getPath();
		
		System.out.println("\nSelected File: " + fileName+"\n");
		
		executeFleury(filePath);
	}
	
	private static void executeFleury(String filePath) throws IOException, IllegalGraphFormatException, InvalidVertexException, InvalidEdgeException {
		
		ConvertsGraph<Integer, Integer> convert = new ConvertsGraph<Integer, Integer>();
		IGraph<Integer, Integer> graph = null;
		
		try {
			graph = convert.converter(filePath);
		} catch (IOException e) {
			System.out.println("Não foi possível encontrar esse arquivo.");
			throw new IOException();
		}
		
		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString(false));
		
		IDepthFirstSearchBridge<Integer, Integer> search = new DepthFirstSearchBridge<Integer, Integer>(graph.clone());
		
		IFleury<Integer, Integer> fleury = new Fleury<Integer, Integer>(graph, search);
		
		IVertex<Integer> u = new V<Integer>("0");
		
		try {
			fleury.search(u);
		} catch (IllegalGraphFormatException e) {
			System.out.println("\n"+e.getMessage());
		}
		
	}

	private static File[] allGraphsToChose(){
		File file = new File("Enter_Graphs/");
		File[] directores = file.listFiles();
		
		return directores;
	}
	
	private static void printAllGraphs(File[] directores){

		for (int i = 0; i < directores.length; i++) {
			System.out.println(String.format("%s. %s", i+1, directores[i].getName()));
			
		}
		
	}
	
}
