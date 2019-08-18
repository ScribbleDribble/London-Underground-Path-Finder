import java.io.*;
import java.util.HashMap;

public class LondonUndergroundMap extends WeightedGraph.Graph {

    // correspond vertex number with station name
    private HashMap<Integer, String> station = new HashMap<>();

    private static final int V = 350;

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
        }

        in.close();
    }


    public String getStation(int vertex) {
        return station.get(vertex);
    }

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
        ld.printGraph();
    }

}