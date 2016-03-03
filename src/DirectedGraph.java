import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DirectedGraph<E extends Edge> {

    // We have changed the x-coordinate for Kålltorp and Torp from 280 to 281, 279 so it won't look like a cycle

    //private NodeTable nodes;
    private ArrayList<E> edges;

    public DirectedGraph(int noOfNodes) {
        //nodes = new NodeTable(noOfNodes);
        edges = new ArrayList<E>(noOfNodes);
    }

    public void addEdge(E e) {
        if (!edges.contains(e)) {
            edges.add(e);
        }
    }

    public Iterator<E> shortestPath(int from, int to) {
        ArrayList<Integer> visited = new ArrayList<Integer>();
        PriorityQueue<QueueElement> priorityQueue = new PriorityQueue<QueueElement>(new CompDijkstraPath());
        //ArrayList<QueueElement> queue = new ArrayList<QueueElement>();

        //NodeObject goalNode = nodes.find(to);

        priorityQueue.add(new QueueElement(from, 0.0, new LinkedList<E>())); //lägg (startnod, 0, tom väg) i en p-kö
        while (!priorityQueue.isEmpty()) {      //while kön inte är tom
            QueueElement temp = priorityQueue.poll(); //(nod, cost, path) = första elementet i p-kön
            final int node = temp.getNode();
            if (!visited.contains(node)) {      //if nod ej är besökt
                if (node == to) {        //if nod är slutpunkt
                    LinkedList<E> tempPath = temp.getPath();
                    tempPath.add(edges.get(node));
                    System.out.println("Nod " + node + " är lika med " + to);
                    return tempPath.iterator(); // returnera path
                } //else
                //markera nod besökt
                visited.add(node);
                //for every v on EL(nod)
                for (E v : edges) {
                    //if v ej är besökt
                    if (v.getSource()==node && !visited.contains(v.getDest())) {

                            //lägg in nytt köelement för v i p-kön
                            LinkedList<E> newPath = (LinkedList<E>) temp.getPath().clone();
                            newPath.add(v);

                            double weight = temp.getWeight()+v.getWeight();
                            priorityQueue.add(new QueueElement(to, weight, newPath));
                    }
                }
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
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges.size(), new CompKruskalEdge());
        pq.addAll(edges);

        // Så länge pq, ej är tom && |cc| < n
        while (!pq.isEmpty() && cc.size() < edges.size()) {
            // hämta e = (from, to, weight) från kön
            Edge e = pq.poll();

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
