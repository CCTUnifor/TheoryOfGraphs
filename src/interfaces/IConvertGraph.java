package interfaces;

import java.io.IOException;

import entities.GraphListAdjacent;

/**
 * Interface to Convert a specific Computation Representation to a Graph. 
 * @author Thiago Maia
 *
 */
public interface IConvertGraph {
	
	
	/**
	 * <span style='font-size: 16px; font-weight: bold;'>
	 * 	Convert the specific txt file to Graph Computation Representation. 
	 * </span>
	 * @param path The relative path the .txt file
	 * @return Return the Graph converted.
	 * @throws IOException
	 */
	public GraphListAdjacent convert(String path) throws IOException;
}
