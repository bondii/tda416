package lab4;

import java.util.*;

public class DirectedGraph<E extends Edge> {

    // We have changed the x-coordinate for Kålltorp and Torp from 280 to 281, 279 so it won't look like a cycle

    private NodeTable nodes;
    private ArrayList<E> edges;

	public DirectedGraph(int noOfNodes) {
		nodes = new NodeTable(noOfNodes);
        edges = new ArrayList<E>();
	}

	public void addEdge(E e) {
		if (!edges.contains(e)) {
            edges.add(e);
        }
	}

	public Iterator<E> shortestPath(int from, int to) {
        ArrayList<NodeObject> visited = new ArrayList<NodeObject>();
        ArrayList<QueueElement> queue = new ArrayList<QueueElement>();

        NodeObject goalNode = nodes.find(to);

        queue.add(new QueueElement(nodes.find(from), 0.0, new LinkedList<E>()));    //lägg (startnod, 0, tom väg) i en p-kö
        while (!queue.isEmpty()) {      //while kön inte är tom
            QueueElement temp = queue.get(0); //(nod, cost, path) = första elementet i p-kön
            final NodeObject node = temp.getNode();
            double cost = temp.getWeight();
            final LinkedList<E> path = temp.getPath();

            if (!visited.contains(node)) {      //if nod ej är besökt
                if (node.equals(goalNode)) {        //if nod är slutpunkt
                    return path.iterator();
                } //else
                //markera nod besökt
                visited.add(node);
                //for every v on EL(nod)
                for (E edge : edges) {
                    if (edge.getSource()==from) {
                        NodeObject destNode = nodes.find(edge.getDest());
                        //if v ej är besökt
                        if (!visited.contains(destNode)) {
                            //lägg in nytt köelement för v i p-kön
                            LinkedList<E> newPath = (LinkedList<E>) temp.getPath().clone();
                            newPath.add(edge);

                            double weight = temp.getWeight()+edge.getWeight();
                            for (int i = 0; i < queue.size(); i++) {
                                if (weight <= queue.get(i).getWeight()) {
                                    queue.add(new QueueElement(destNode, weight, newPath));
                                    break;
                                }
                            }
                        }
                    }
                }

                queue.remove(temp);

            }
        }

		return null; //path not found
	}

    public Edge getEdge(NodeObject from, NodeObject to) {
        int fromIndex = edges.indexOf(from);
        int toIndex = edges.indexOf(to);
        for (E edge : edges) {
            if (edge.getSource() == fromIndex && edge.getDest() == toIndex) {
                return edge;
            }
        }

        return null;
    }
		
	public Iterator<E> minimumSpanningTree() {
        // Skapa ett fält cc som för varje nod innehåller en egen tom lista (som skall innehålla bågar så småningom)
        // (dvs varje nod är i en egen komponent)
        ArrayList<ArrayList<E>> cc = new ArrayList<ArrayList<E>>();

        // Lägg in alla bågar i en prioritetskö
        ArrayList<E> pq = new ArrayList<E>();
        for (E edge : edges) {
            for (int i = 0; i < pq.size(); i++) {
                if (CompKruskalEdge.isBiggerThan(pq.get(i), edge)) {
                    pq.add(i, edge);
                    break;
                }
            }
        }

        // Så länge pq, ej är tom && |cc| < n
        while (!pq.isEmpty() && cc.size() < nodes.noOfNodes()) {
            // hämta e = (from, to, weight) från kön
            Edge e = pq.get(0);
            pq.remove(0);
            // om from och to inte refererar till samma lista i cc
            int from = e.getSource();
            int to = e.getDest();
            if (!cc.get(from).equals(cc.get(to))) {
                // flytta över alla elementen från den kortare listan till den andra och se till att alla berörda noder
                // i cc refererar till den påfyllda listan
                int small, big;
                if (cc.get(from).size() > cc.get(to).size()) {
                    big = from;
                    small = to;
                } else {
                    big = to;
                    small = from;
                }
                ArrayList<E> smallList = cc.get(small);
                ArrayList<E> bigList = cc.get(big);

                for (E edge : smallList) {
                    bigList.add(edge);
                }

                cc.set(small, bigList);

                // lägg slutligen e i den påfyllda listan
                bigList.add((E) e);
            }
        }

        // Resultatet dvs det sökta MST finns i den enda kvarvarande listan i cc.
		return cc.get(0).iterator();
	}

}
  
