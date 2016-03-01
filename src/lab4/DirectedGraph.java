package lab4;

import java.util.*;

public class DirectedGraph<E extends Edge> {

    private NodeTable nodes;
    private ArrayList<E> edges;

	public DirectedGraph(int noOfNodes) {
		nodes = new NodeTable(noOfNodes);
        edges = new ArrayList<E>();
	}

	public void addEdge(E e) {
		edges.add(e);
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
		return null;
	}

}
  
