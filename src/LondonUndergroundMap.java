import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LondonUndergroundMap extends WeightedGraph.Graph {

    // correspond vertex number with station name
    private HashMap<Integer, String> station = new HashMap<>();
    private HashMap<Set<Integer>, Integer> train_line = new HashMap<>();

    // there isn't 350 stations (actually 302) but the data on vertex id numbers are not 1:1 with the station number
    // so to fit in all the other vertex ids >
    private static final int V = 305;

    public LondonUndergroundMap() throws IOException {
        super(V);

        // read file data and correct number of vertices in graph
        readStationConnections();

    }


    public void readStationConnections() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(
                                            new FileReader("data/london_stations.csv"));

        // skip over field names
        String l = bufferedReader.readLine();

        for(l = bufferedReader.readLine(); l != null; l = bufferedReader.readLine())
        {
            String[] contents = l.split(",");
            station.put(Integer.parseInt(contents[0]), contents[3]);
        }

        bufferedReader.close();

        BufferedReader in = new BufferedReader(
                new FileReader("data/london_connections.csv"));

        String line = in.readLine();


        for (line = in.readLine(); line != null; line = in.readLine())
        {
            String[] contents = line.split(",");
            addEdge(Integer.parseInt(contents[0]), Integer.parseInt(contents[1]), Integer.parseInt(contents[3]));

            // correspond edge w/ train line
            HashSet<Integer> edge = new HashSet<>();
            edge.add(Integer.parseInt(contents[0]));
            edge.add(Integer.parseInt(contents[1]));
            train_line.put(edge, Integer.parseInt(contents[2]));

        }

        in.close();
    }


    public String getStation(int vertex) {
        return station.get(vertex);
    }

    // bethenal green to liv  u = 24 -> v = 156
//    public int findPath(int u, int v) {
//
//    }

    @Override
    public void printGraph() {

        for (int i = 0; i < n_vertices; i++) {
            for (int j = 0; j < adjListArr[i].size(); j++) {
                System.out.println(i + " -> " + adjListArr[i].get(j).v +" w " + adjListArr[i].get(j).weight);
            }
        }
    }

}


class Test {

    public static void main(String[] args) throws IOException{
        LondonUndergroundMap ld = new LondonUndergroundMap();

        DijkstraPath path = new DijkstraPath(305, ld);
        System.out.println(path.shortestPath(145, 164));
    }

}