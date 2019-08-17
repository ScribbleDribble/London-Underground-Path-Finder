import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class DijkstraPath {

    private int n_vertices;

    private HashMap<Integer, Double> dist = new HashMap<>();

    private HashSet<Integer> visited = new HashSet<>();

    private WeightedGraph.Graph G;

    public DijkstraPath(int n_vertices, WeightedGraph.Graph G) {

        this.n_vertices = n_vertices;
        this.G = G;

        for (int i = 0; i < n_vertices; i++)
        {
            dist.put(i, Double.POSITIVE_INFINITY);
        }

    }

    public int findClosestVertex(int current_vertex, int src) {

        ArrayList<WeightedGraph.Edge>[] adj = G.getAdjListArr();

        int smallest_dist = 99;
        int closest_vertex = -1;

        for (int i = 0; i < adj[current_vertex].size(); i++ )
        {
            if ( adj[current_vertex].get(i).weight < smallest_dist && !visited.contains(adj[current_vertex].get(i).v))
            {
                smallest_dist = adj[current_vertex].get(i).weight;

                int v = adj[current_vertex].get(i).v;
                closest_vertex = v;
            }
        }

        if (closest_vertex == -1)
        {
            return src;
        }

        return closest_vertex;
    }



    public double shortestPath(int u, int v) {

        ArrayList<WeightedGraph.Edge>[] adj = G.getAdjListArr();

        int src = u;
        int w, u2;
        double tempDist;
        dist.put(u, 0.0);
        visited.add(u);

        while ( !(visited.size() == n_vertices) || v == u)
        {

            for (int i = 0; i < adj[u].size(); i++)
            {
                w = adj[u].get(i).weight;
                u2 = adj[u].get(i).v;
                tempDist = (double) w + dist.get(u2);


                if (tempDist < dist.get(u))
                {
                    // update shortest distance to get to current vertex
                    dist.put(u, tempDist);
                }

            }

            u = findClosestVertex(u, src);
            visited.add(u);

            if (visited.size() == n_vertices)
                return dist.get(v);

        }

        return dist.get(v);

    }



}
