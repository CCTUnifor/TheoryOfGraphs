package util;

import enums.GraphPrintPresentation;
import interfaces.IGraphPrintPresentation;

public class GraphPrintPresentationFactory {
	
	public static IGraphPrintPresentation instance(GraphPrintPresentation typePrint){
		
		switch (typePrint) {
		case ADJACENT:
			return new AdjacenteGraphPrintPresentation();

		case ADJACENT_WITHDATA:
			return new AdjacenteWithDataGraphPrintPresentation();
			
		case ROW:
			return new RowGraphPrintPresentation();
			
		default:
			return null;
		}
		
	}
}
