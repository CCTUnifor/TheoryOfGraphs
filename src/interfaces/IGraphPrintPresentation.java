package interfaces;

public interface IGraphPrintPresentation<V, E> {
	String mountGrafoToMessage(IGraph<V, E> graph);
}
