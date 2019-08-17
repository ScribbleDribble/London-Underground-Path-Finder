import java.util.ArrayList;

public class WeightedGraph {


    public static class Edge {
        public int u, v, weight;

        public Edge(int u, int v, int weight) {

            this.u = u;
            this.v = v;
            this.weight = weight;
        }

    }


    public static class Graph {

        private ArrayList<Edge>[] adjListArr;
        private int n_vertices;


        public Graph(int n_vertices) {
            this.n_vertices = n_vertices;

            adjListArr = new ArrayList[n_vertices];

            for (int i = 0; i < n_vertices; i++) {
                adjListArr[i] = new ArrayList<>();
            }

        }

        public void addEdge(int u, int v, int w) {
            Edge e = new Edge(u, v, w);
            Edge ePrime = new Edge(v, u, w);
            adjListArr[u].add(e);
            adjListArr[v].add(ePrime);
        }

        public void printGraph() {

            for (int i = 0; i < n_vertices; i++) {
                for (int j = 0; j < adjListArr[i].size(); j++) {
                    System.out.println(i + " -> " + adjListArr[i].get(j).v +" w " + adjListArr[i].get(j).weight);
                }
            }
        }

        public ArrayList<Edge>[] getAdjListArr() {
            return adjListArr;
        }

    }


}
