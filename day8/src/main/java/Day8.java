import javax.vecmath.Point3d;
import java.util.*;
import java.util.stream.Collectors;

public class Day8 {
    public record Edge(Point3d a, Point3d b, double distance) {}

    public static Point3d[] getPoints(String input) {
        var lines = input.split("\n");
        var points = new Point3d[lines.length];
        for (var i=0;i<lines.length;i++) {
            var line = lines[i];
//            IO.println(line);
            var values = line.split(",");
            var x = Integer.parseInt(values[0]);
            var y = Integer.parseInt(values[1]);
            var z = Integer.parseInt(values[2]);
            points[i] = new Point3d(x, y, z);
        }
        return points;
    }
    public static Edge[] getDistances(Point3d[] points) {
        var edges = new ArrayList<Edge>();
        for (var i=0;i<points.length;i++) {
            for (int j=i+1;j<points.length;j++) {
                if (i != j){
                    var edge = new Edge(points[i], points[j], points[i].distance(points[j]));
//                    IO.println(edge);
                    edges.add(edge);
                }
            }
        }
        edges.sort(Comparator.comparing(Edge::distance));
//        IO.println(edges);
        return edges.toArray(Edge[]::new);
    }
    public static Edge[] connectClosestPoints(Edge[] edges, int numConnection) {
        var connections = new ArrayList<Edge>();
        for (var i=0;i<numConnection;i++) {
            var edge = edges[i];
            if (!arePointsConnected(connections, edge.a, edge.b)){
//                System.out.printf("Connecting points %s to %s\n", edge.a, edge.b);
                connections.add(edge);
            } else {
//                System.out.printf("Points %s and %s already connected\n", edge.a, edge.b);
            }
        }
        return connections.toArray(Edge[]::new);
    }
    public static boolean arePointsConnected(List<Edge> edges, Point3d a, Point3d b) {
        var edgesOfPointA = edges.stream().filter(edge1 -> edge1.a.equals(a) || edge1.b.equals(a)).toList();
        var filteredEdges = edges.stream().filter(edge1 -> !(edge1.a.equals(a) || edge1.b.equals(a))).toList();
//        System.out.printf("Input edges: %d, point A edges: %d, filtered edges %d\n", edges.size(), edgesOfPointA.size(), filteredEdges.size());
        for (var edge : edgesOfPointA) {
            if (edge.a.equals(a)) {
//                System.out.printf("%s -> %s\n", a, edge.b);
                if (edge.b.equals(b)) {
//                    IO.println("Connected");
                    return true;
                }
                if (arePointsConnected(filteredEdges, edge.b, b))
                    return true;
            } else {
//                System.out.printf("%s -> %s\n", a, edge.a);
                if (edge.a.equals(b)){
//                    IO.println("Connected");
                    return true;
                }
                if (arePointsConnected(filteredEdges, edge.a, b))
                    return true;
            }
        }
        return false;
    }
    public static List<Edge> getConnectedEdges(List<Edge> edges, Point3d a) {
        var edgesOfPointA = edges.stream().filter(edge1 -> edge1.a.equals(a) || edge1.b.equals(a)).toList();
        var filteredEdges = edges.stream().filter(edge1 -> !(edge1.a.equals(a) || edge1.b.equals(a))).toList();
        var connectedEdges = new ArrayList<Edge>(edgesOfPointA);
        for (var edge : edgesOfPointA) {
            if (edge.a.equals(a)) {
//                System.out.printf("%s -> %s\n", a, edge.b);
                connectedEdges.addAll(getConnectedEdges(filteredEdges, edge.b));
            } else {
//                System.out.printf("%s -> %s\n", a, edge.a);
                connectedEdges.addAll(getConnectedEdges(filteredEdges, edge.a));
            }
        }
        return connectedEdges;
    }
    public static int[] countCircuitsPoints(Edge[] connections) {
        var circuits = new ArrayList<Integer>();
        var c = new ArrayList<>(Arrays.stream(connections).toList());
        while (!c.isEmpty()) {
            var edge = c.get(0);
            var connectedEdges = getConnectedEdges(c,edge.a);
            circuits.add(connectedEdges.size()+1);
            c.removeAll(connectedEdges);
//            System.out.printf("Circuit has %d junctions\n", connectedEdges.size()+1);
        }
        return circuits.stream().mapToInt(Integer::intValue).toArray();
    }
    public static long calculateProduct(int[] circuits) {
        var largestCircuits = new ArrayList<>(Arrays.stream(circuits).boxed().sorted(Comparator.reverseOrder()).limit(3).toList());
//        IO.println("Largest Circuits: " + largestCircuits);
        var total = 1;
        for (int largestCircuit : largestCircuits) {
            total *= largestCircuit;
        }
        return total;
    }
    public static Edge[] connectClosestPointsUntilOneCircuit(Edge[] edges, int numPoints) {
        var connections = new ArrayList<Edge>();
        var i = 0;
        do {
            var edge = edges[i];
            if (!arePointsConnected(connections, edge.a, edge.b)){
//                System.out.printf("Connecting points %s to %s\n", edge.a, edge.b);
                connections.add(edge);
            } else {
//                System.out.printf("Points %s and %s already connected\n", edge.a, edge.b);
            }
            i++;
        } while (connections.size() < numPoints-1);

        return connections.toArray(Edge[]::new);
    }
}
