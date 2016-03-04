import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class DirectedGraph<E extends Edge> {
    private ArrayList<ArrayList<E>> edges;
    private final int noOfNodes;

    public DirectedGraph(int noOfNodes) {
        edges = new ArrayList<ArrayList<E>>(noOfNodes);
        for (int i = 0; i < noOfNodes; i++) {
            edges.add(new ArrayList<E>());
        }
        this.noOfNodes = noOfNodes;
    }

    public void addEdge(E e) {
        if (!edges.get(e.getSource()).contains(e)) {
            edges.get(e.getSource()).add(e);
        }
    }

    public Iterator<E> shortestPath(int from, int to) {
        boolean[] visited = new boolean[noOfNodes];
        PriorityQueue<QueueElement> priorityQueue = new PriorityQueue<QueueElement>(new CompDijkstraPath());

        priorityQueue.add(new QueueElement(from, 0.0, new LinkedList<E>())); //lägg (startnod, 0, tom väg) i en p-kö
        while (!priorityQueue.isEmpty()) {      //while kön inte är tom
            QueueElement temp = priorityQueue.poll(); //(nod, cost, path) = första elementet i p-kön
            final int node = temp.getNode();
            if (!visited[node]) {      //if nod ej är besökt
                if (node == to) {        //if nod är slutpunkt
                    return temp.getPath().iterator(); // returnera path
                } //else
                //markera nod besökt
                visited[node] = true;

                //for every v on EL(nod)
                for (E v : edges.get(node)) {
                    //if v ej är besökt
                    if (!visited[v.getDest()]) {
                            //lägg in nytt köelement för v i p-kön
                            LinkedList<E> newPath = (LinkedList<E>) temp.getPath().clone();
                            newPath.add(v);

                            double weight = temp.getWeight()+v.getWeight();
                            priorityQueue.add(new QueueElement(v.getDest(), weight, newPath));
                    }
                }
            }
        }

        return null; //path not found
    }

    public Edge getEdge(NodeObject from, NodeObject to) {
        int toIndex = edges.indexOf(to);
        for (E edge : edges.get(edges.indexOf(from))) {
            if (edge.getDest() == toIndex) {
                return edge;
            }
        }

        return null;
    }

    public Iterator<E> minimumSpanningTree() {
        // Skapa ett fält cc som för varje nod innehåller en egen tom lista (som skall innehålla bågar så småningom)
        // (dvs varje nod är i en egen komponent)
        ArrayList<ArrayList<E>> cc = new ArrayList<ArrayList<E>>(noOfNodes);
        for (int i = 0; i < noOfNodes; i++) {
            cc.add(new ArrayList<E>());
        }

        int ccCount = 0;

        // Lägg in alla bågar i en prioritetskö
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>(noOfNodes, new CompKruskalEdge());
        for (ArrayList<E> edgeList : edges) {
            pq.addAll(edgeList);
        }

        // Så länge pq, ej är tom && |cc| < n
        while (!pq.isEmpty() && ccCount < noOfNodes) {
            // hämta e = (from, to, weight) från kön
            Edge e = pq.poll();

            // om from och to inte refererar till samma lista i cc
            int from = e.getSource();
            int to = e.getDest();
            if (!(cc.get(from)==(cc.get(to)))) {
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
                    cc.set(edge.getSource(), bigList);
                    cc.set(edge.getDest(), bigList);
                }

                cc.set(small, bigList);
                ccCount++;

                // lägg slutligen e i den påfyllda listan
                bigList.add((E) e);
            }
        }

        // Resultatet dvs det sökta MST finns i den enda kvarvarande listan i cc.
        return cc.get(0).iterator();
    }

}
