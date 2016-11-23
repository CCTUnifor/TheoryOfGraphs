package main;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import algorithms.Fluery;
import entities.NoVertex;
import exceptions.IllegalGraphFormatException;
import interfaces.IConvertGraph;
import interfaces.IFleury;
import interfaces.IGraph;
import util.ConvertGraphAdjacent;

public class Quest01 {
	
	File directores[];
	
	public static void main(String[] args) throws Exception {

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Show me a Fleury Path.\n");
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
	
	private static void executeFleury(String filePath) throws IOException {
		
		IConvertGraph convert = new ConvertGraphAdjacent();
		IGraph graph = null;
		
		try {
			graph = convert.convert(filePath);
		} catch (IOException e) {
			System.out.println("Não foi possível encontrar esse arquivo.");
			throw new IOException();
		}
		
		System.out.println("Graph Converted to .txt from Computational Representation\n");
		System.out.println(graph.toString());
		
		IFleury fleury = new Fluery(graph);
		
		NoVertex u = new NoVertex("0");
		
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
