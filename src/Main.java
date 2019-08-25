import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        final int V = 305;

        if (args.length != 2)
        {
            System.out.println("Usage: java Main starting_station destination_station");
        }

        else {

            try {
                LondonUndergroundMap ld = new LondonUndergroundMap();
                DijkstraPath dijkstraPath = new DijkstraPath(V, ld);

                System.out.println(args[0] + " --> " +args[1]);

                ld.setStations(args[0], args[1], dijkstraPath);
            }

            catch (IOException e) {
                System.out.println("Something went wrong");
            }


        }
    }
}
