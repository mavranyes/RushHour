import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Vector;

class RushHour {
    public static void main(String[] args) {
        PriorityQueue<PositionNode> locations = new PriorityQueue<>();
        Vector<Vehicle> vehicles = new Vector<Vehicle>();
        HashMap<String, String> visitedPos = new HashMap<String, String>();

        String[][] map = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                map[i][j] = " ";
            }
        }
        parseInput(map, vehicles);
        printMap(map);

        //Adds to the hash map
        String currentPosition = convertMaptoString(map);
        visitedPos.put(currentPosition, currentPosition);
        //Checks if it contains this string
        System.out.println(visitedPos.containsKey(currentPosition));

        while(!locations.isEmpty()) {
            //find adj
            
        }
    }

    private static void parseInput(String[][] map, Vector<Vehicle> vehicles) {
        try{
            File file = new File("input.txt");
            Scanner scan = new Scanner(file);
            int carCount = Integer.parseInt(scan.nextLine());
            for (int i = 0; i < carCount; i++) {
                String type = scan.nextLine();
                String color = scan.nextLine();
                String dir = scan.nextLine();
                int yPos = Integer.parseInt(scan.nextLine());
                int xPos = Integer.parseInt(scan.nextLine());
                Vehicle v = new Vehicle(type, color, dir, xPos, yPos, i);
                v.populateMap(map);
                // printMap(map);
                // v.findMoves(map);
                vehicles.addElement(v);
            }
            scan.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found: " + "input.txt");
        }
    }

    private static void printMap(String[][] map) {
        System.out.println("Rush Hour Current Representation: \n");
        System.out.println(" -------------");
        for(int i = 0; i < 6; i++){
            System.out.print((i+1) + "|");
            for(int j = 0; j < 6; j++){
                System.out.print(map[i][j] + "|");
            }
            System.out.println("\n -------------");
        }
    }

    private static String convertMaptoString(String[][] map) {
        String check = "";
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                check += map[i][j];
            }
        }
        return check;
    }
}