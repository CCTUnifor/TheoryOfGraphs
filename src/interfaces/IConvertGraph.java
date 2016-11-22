package interfaces;

import java.io.IOException;

import entities.GraphAdjacenteList;

/**
 * Interface to Convert a specific Computation Representation to a Graph. 
 * @author Thiago Maia
 *
 */
public interface IConvertGraph {
	
	
	/**
	 * Convert the specific txt file to Graph Computation Representation. 
	 * 
	 * @param path The relative path the .txt file
	 * @return Return the Graph converted.
	 * @throws IOException
	 */
	public GraphAdjacenteList convert(String path) throws IOException;
}
