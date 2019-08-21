package A5_Dijkstra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class DiGraph implements DiGraphInterface {

	// in here go all your data and methods for the graph
	long size;
	long edges;
	HashSet<Long> nodeIDs;
	HashSet<Long> edgeIDs;
	HashMap<String, Node> stringNodes;
	HashMap<String, ArrayList<Edge>> stringEdges;

	public DiGraph() { // default constructor
		// explicitly include this
		// we need to have the default constructor
		// if you then write others, this one will still be there

		size = 0;
		edges = 0;
		stringNodes = new HashMap<>();
		nodeIDs = new HashSet<Long>();
		stringEdges = new HashMap<>();
		edgeIDs = new HashSet<Long>();
	}

	public boolean addNode(long idNum, String label) {
		if (stringNodes.containsKey(label) || idNum < 0 || nodeIDs.contains(idNum)) {
			return false;
		} else {
			Node newNode = new Node(idNum, label);
			stringNodes.put(label, newNode);
			nodeIDs.add(idNum);
			size++;
			return true;
		}
	}

	public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
		if (!stringNodes.containsKey(sLabel) || !stringNodes.containsKey(dLabel) || edgeIDs.contains(idNum) || idNum < 0) {
			return false;
		} else {
			Edge newEdge = new Edge(idNum, stringNodes.get(sLabel), stringNodes.get(dLabel), weight, eLabel);
			if(stringEdges.containsKey(sLabel)) {
				ArrayList<Edge> edgey = stringEdges.get(sLabel);
				for (Edge e : edgey) {
					if (e.to.label.compareTo(dLabel) == 0) {
						return false;
					}
				}
				stringEdges.get(sLabel).add(newEdge);
			} else {
				ArrayList<Edge> newAList = new ArrayList<Edge>();
				newAList.add(newEdge);
				stringEdges.put(sLabel, newAList);
			}
			edgeIDs.add(idNum);
			edges++;
			return true;
		}
	}

	
	public boolean delNode(String label) {
		if (stringNodes.containsKey(label)) {
			nodeIDs.remove(stringNodes.get(label).idNum);
			stringNodes.remove(label);
			size--;
			return true;
		} else {
			return false;
		}
	}

	
	public boolean delEdge(String sLabel, String dLabel) {
		if (stringEdges.containsKey(sLabel)) {
			Edge remove = null;
			ArrayList<Edge> edgey = stringEdges.get(sLabel);
			for (Edge e : edgey) {
				if (e.to.label.compareTo(dLabel) == 0) {
					remove = e;
					break;
				}
			}
			if (remove == null) {
				return false;
			} else {
				edgey.remove(remove);
				edgeIDs.remove(remove.id);
				edges--;
				return true;
			}
		} else {
			return false;
		}
	}

	
	public long numNodes() {
		return size;
	}

	
	public long numEdges() {
		return edges;
	}


	HashSet<String> toDoNodes;
	HashMap<String, Long> distance;
	
	public ShortestPathInfo[] shortestPath(String label) {
		toDoNodes = new HashSet<>();
		distance = new HashMap<>();

		distance.put(label, (long) 0);
		toDoNodes.add(label);
		while (toDoNodes.size() > 0) {
			String node = getMinimum(toDoNodes);
			toDoNodes.remove(node);
			findMinDistances(node);
		}

		ShortestPathInfo[] output = new ShortestPathInfo[(int) size];
		int i = 0;
		for (String s : stringNodes.keySet()) {
			try {
				output[i] = new ShortestPathInfo(s, distance.get(s));
			} catch (NullPointerException e) {
				output[i] = new ShortestPathInfo(s, -1);
			} finally {
				i++;
			}

		}

		return output;
	}

	public String getMinimum(HashSet<String> nodes) {
		Iterator<String> i = nodes.iterator();
		String minimum = i.next();
		while (i.hasNext()) {
			String curr = i.next();
			if (shortestTo(curr) < shortestTo(minimum)) {
				minimum = curr;
			}
		}
		
		return minimum;
	}

	public long shortestTo(String to) {
		if (distance.containsKey(to)) {
			return distance.get(to);
		} else {
			return Long.MAX_VALUE;
		}
	}

	public void findMinDistances(String node) {
		HashSet<String> adjacentNodes = neighborNodes(node);
		Iterator<String> i = adjacentNodes.iterator();
		while (i.hasNext()) {
			String adj = i.next();
			if (shortestTo(adj) > shortestTo(node) + getDistance(node, adj)) {
				distance.put(adj, shortestTo(node) + getDistance(node, adj));
				toDoNodes.add(adj);
			}
		}
	}

	public HashSet<String> neighborNodes(String node) {
		HashSet<String> neighbors = new HashSet<>();
		if (stringEdges.containsKey(node)) {
			for (Edge edge : stringEdges.get(node)) {
				neighbors.add(edge.to.label);
			}
		}

		return neighbors;
	}

	public long getDistance(String node, String target) {
		if (stringEdges.containsKey(node)) {
			for (Edge edge : stringEdges.get(node)) {
				if (edge.to.label.compareTo(target) == 0) {
					return edge.weight;
				}
			}
		} 
		return -1;
	}


	// rest of your code to implement the various operations
}