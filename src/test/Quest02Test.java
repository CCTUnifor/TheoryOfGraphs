package test;

import java.io.IOException;

import org.junit.Test;

import algorithms.Prim;
import interfaces.IConvertGraph;
import interfaces.IGraph;
import junit.framework.Assert;
import util.ConvertGraphAdjacent;

public class Quest02Test {

	@Test
	public void WhenPassedTwoGraphsAndTheSecondIsMSTFromTheFirst_ItsCorrect() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph06.txt";
		String subGraphPath = "Enter_Graphs/Graph05.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertTrue(isMST);
		
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheSecondHaveAEdgeDifferent_ReturnFalse() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph06.txt";
		String subGraphPath = "Enter_Graphs/Graph11.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertFalse(isMST);
		
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheyAreEqualsAndTheyAreAMST_ReturnTrue() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph05.txt";
		String subGraphPath = "Enter_Graphs/Graph05.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertTrue(isMST);
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheyAreEqualsAndTheyDontAMST_ReturnFalse() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph06.txt";
		String subGraphPath = "Enter_Graphs/Graph06.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertFalse(isMST);
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheyAreNullGraphs_ReturnFalse() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph03.txt";
		String subGraphPath = "Enter_Graphs/Graph03.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertFalse(isMST);
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheSecondIsDisconnected_ReturnFalse() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph11.txt";
		String subGraphPath = "Enter_Graphs/Graph12.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertFalse(isMST);
	}
	
	@Test
	public void WhenPassedTwoGraphsAndTheyAreEqualsButTheyAreDisconnected_ReturnTrue() throws IOException {

		// Arrange
		String superGraphPath = "Enter_Graphs/Graph12.txt";
		String subGraphPath = "Enter_Graphs/Graph12.txt";
		boolean isMST = false;
		
		// Act
		IGraph superGraph = this.getGraph(superGraphPath);
		IGraph subGraph = this.getGraph(subGraphPath);
		
		isMST = this.executeIsMST(superGraph, subGraph);
		
		// Assert
		Assert.assertTrue(isMST);
	}
	
	private IGraph getGraph(String filePath) throws IOException{
		
		IGraph superGraph = null;
			
		IConvertGraph convert = new ConvertGraphAdjacent();
		try {
			superGraph = convert.convert(filePath);
		} catch (IOException e) {
			throw new IOException("Não foi possível encontrar os arquivos");
		}
		
		return superGraph;
	}
	
	private boolean executeIsMST(IGraph superGraph, IGraph subGraph){
		
		System.out.println("Super-Graph Converted to .txt from Computational Representation\n");
		System.out.println(superGraph.toString());
		
		System.out.println("Sub-Graph Converted to .txt from Computational Representation\n");
		System.out.println(subGraph.toString());
		
		Prim MST = new Prim(superGraph);
		
		try {
			MST.isMinimalSpanningTree(subGraph);
			return true;
		} catch (Exception e) {
		}
		return false;
		
	}

}
