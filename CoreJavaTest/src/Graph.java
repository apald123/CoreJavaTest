import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Graph {
	
	public static class Vertex implements Comparable<Vertex> {
		String name;
		
		public Vertex(String name) {
			super();
			this.name = name;
		}
		
		
		@Override		
		public int compareTo(Vertex v) {
			return name.compareTo(v.name);
		}
			
		@Override
		public int hashCode() {
			// TODO Auto-generated method stub
			return name.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			// TODO Auto-generated method stub
			Vertex v = (Vertex)obj;
			return name.equals(v.name);
		}

		@Override
		public String toString() {
			return "Vertex [name=" + name + "]";
		}
	}
	
	public Graph() {
		adjMap = new HashMap<>();
		visited = new HashSet<>();
	}
	
	Map<Vertex, Set<Vertex>> adjMap;
	Set<Vertex> visited;
	
	public void addVertex(Vertex v) {
		if (!adjMap.containsKey(v)) {
			adjMap.put(v, new TreeSet<Vertex>());
		}
	}
	
	public void addEdge(Vertex from, Vertex to) {
		if (!adjMap.containsKey(from)) {
			adjMap.put(from, new HashSet<Vertex>());
		}
		if (!adjMap.containsKey(to)) {
			adjMap.put(to, new HashSet<Vertex>());
		}
		
		adjMap.get(from).add(to);
	}
	
	public Vertex getVertex(String name) {
		Set<Vertex> vset = adjMap.keySet();
		Iterator<Vertex> itr = vset.iterator();
		while (itr.hasNext()) {
			Vertex v = itr.next();
			if (name.equals(v.name)) {
				return v;
			}
		}
		return null;		
	}
	
	public Vertex getUnvisitedPeer(Vertex v) {
		Set<Vertex> adjSet = adjMap.get(v);
		for (Vertex v1 : adjSet) {
			if (!visited.contains(v1)) {
				return v1;
			}
		}
		return null;
	}
	
	public void dfs() {
		Deque<Vertex> stack = new ArrayDeque<>();
		visited.clear();

		Vertex root = getVertex("A");
		stack.push(root);
		System.out.println(root);
		visited.add(root);
		
		while (!stack.isEmpty()) {
			Vertex curr = stack.peek();					
			Vertex v = getUnvisitedPeer(curr);
			if (v != null) {
				System.out.println(v);				
				visited.add(v);
				stack.push(v);
			} else {				
				stack.pop();
			}
		}
	}

	public void bfs() {
		Queue<Vertex> que = new LinkedList<>();
		visited.clear();

		Vertex root = getVertex("A");
		visited.add(root);
		que.add(root);
		System.out.println(root);
		
		while (!que.isEmpty()) {
			Vertex curr = que.remove();
			Set<Vertex> peers = adjMap.get(curr);
			for (Vertex v : peers) {
				visited.add(v);
				que.add(v);
				System.out.println(v);				
			}
		}		
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph();
		Graph.Vertex v1 = new Graph.Vertex("A");
		graph.addVertex(v1);
		Vertex v2 = new Vertex("B");
		graph.addVertex(v2);
		Vertex v3 = new Vertex("C");
		graph.addVertex(v3);
		Vertex v4 = new Vertex("D");
		graph.addVertex(v4);
		Vertex v5 = new Vertex("E");
		graph.addVertex(v5);

		
		graph.addEdge(v1, v2); //AB
		graph.addEdge(v2, v3); //BC
		graph.addEdge(v1, v4); //AD
		graph.addEdge(v4, v5); //DE

		/*
		for (Map.Entry<Vertex, Set<Vertex>> e : graph.adjMap.entrySet()) {
			System.out.println("Key="+e.getKey());
			System.out.println("Values="+e.getValue());
		}
		*/

		System.out.println("DFS ...............");
		graph.dfs();
		System.out.println("BFS ...............");
		graph.bfs();				
	}
}
