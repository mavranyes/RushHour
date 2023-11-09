import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Vector;

class RushHour {
    public static void main(String[] args) {
        PriorityQueue<PositionNode> locationsQue = new PriorityQueue<>();
        Vector<Vehicle> vehicles = new Vector<Vehicle>();
        HashMap<String, String> visitedPos = new HashMap<String, String>();

        String[][] map = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                map[i][j] = " ";
            }
        }
        parseInput(map, vehicles);
        //printMap(map);

        //Adds to the hash map
        String currentPosition = convertMaptoString(map);
        System.out.println(currentPosition);
        visitedPos.put(currentPosition, currentPosition);
        //Checks if it contains this string
        System.out.println(visitedPos.containsKey(currentPosition));

        //System.out.println(vehicles.get(3).getPlace());

        while(!locationsQue.isEmpty()) {
            locationsQue.poll();
            
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

    /*
     * Print out a visual representation of the board
     */
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

    /*
     * Convert the map object to a string where vehicle objects are represented as characters
     */
    private static String convertMaptoString(String[][] map) {
        String check = "";
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                check += map[i][j];
            }
        }
        return check;
    }

    /*
     * This method scans a string representation of the board and passes each vehicle+position to findNext
     */
    private static void findCars(String currentPosition, Vector<Vehicle> vehicles){
        String v = "";
        for(int i = 0; i < currentPosition.length(); i++){
            int pos = v.indexOf(currentPosition.charAt(i));
            if(pos == -1) { 
                break; 
            }
            int vindex = 0;
            if(currentPosition.charAt(i) != 'R'){
                vindex = (int) currentPosition.charAt(i);
            }
            //findNext(vehicles.get(vindex), pos);
        }
    }

}