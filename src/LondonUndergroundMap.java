import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LondonUndergroundMap extends WeightedGraph.Graph {

    // correspond vertex number with station name
    private HashMap<Integer, String> station = new HashMap<>();

    // each edge will share a line
    private HashMap<Set<Integer>, Integer> train_line = new HashMap<>();
    // each line will have a name that can be retrieved
    private HashMap<Integer, String> name_of_line = new HashMap<>();

    private static final int V = 305;

    public LondonUndergroundMap() throws IOException {
        super(V);

        // read file data and correct number of vertices in graph
        readStationConnections();

    }


    public void readStationConnections() throws IOException {

        File f = new File("../data/london_stations.csv");

        BufferedReader bufferedReader = new BufferedReader(
                                            new FileReader("../data/london_stations.csv"));


        // skip over field names
        String l = bufferedReader.readLine();

        for(l = bufferedReader.readLine(); l != null; l = bufferedReader.readLine())
        {
            String[] contents = l.split(",");
            station.put(Integer.parseInt(contents[0]), contents[3]);
        }

        bufferedReader.close();

        BufferedReader in = new BufferedReader(
                                new FileReader("../data/london_connections.csv"));


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

        BufferedReader in2 = new BufferedReader(
                                new FileReader("../data/london_lines.csv"));

        String[] strings;

        line = in2.readLine();

        for (line = in2.readLine(); line != null; line = in2.readLine())
        {
            strings = line.split(",");
            name_of_line.put(Integer.parseInt(strings[0]), strings[1]);

        }

    }


    public String getStation(int vertex) {
        return station.get(vertex);
    }

    //public String getLine(int line) { }

    public void printJourney(int[] stations) {

        int previous_station = -1;
        int previous_line = -1;

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < stations.length; i++)
        {
            set.add(previous_station);
            set.add(stations[i]);

            if (train_line.get(set) != null && previous_line != train_line.get(set))
            {
                System.out.println("\n"+"-----"+name_of_line.get(train_line.get(set))+"-----"+"\n");
                previous_line = train_line.get(set);
            }

            else {
                System.out.println();
            }

            previous_station = stations[i];
            set.clear();
            System.out.println(getStation(stations[i]));
        }


    }

    public void setStations(String src, String dest, DijkstraPath path) {
        src = src.toLowerCase();
        dest = dest.toLowerCase();

        int length = 0;

        int u = -1, v = -1;

        for (int vertex: station.keySet())
        {
            length = station.get(vertex).length();

            String s = station.get(vertex).toLowerCase().substring(1, length - 1);

            // string of station name with outer speech marks removed
            if (s.equals(src))
            {
                u = vertex;
            }

            else if (s.equals(dest))
            {
                v = vertex;
            }

        }

        if (u != -1 && v != -1)
        {
            path.shortestDist(u, v);
            printJourney(path.shortestPath(u, v));
        }

        else
        {
            System.out.println("Couldn't find a station");
        }

    }

}
