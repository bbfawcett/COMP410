package A5_Dijkstra;

public class Edge {
	Node from;
	Node to;
	String label;
	long weight;
	long id;

	public Edge(long id, Node from, Node to, long weight, String label) {
		this.from = from;
		this.to = to;
		this.weight = weight;
		this.label = label;
		this.id = id;
	}
}
