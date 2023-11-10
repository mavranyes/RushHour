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
        printMap(map);

        //Adds to the hash map
        String currentPosition = convertMaptoString(map);
        //System.out.println(currentPosition);
        visitedPos.put(currentPosition, currentPosition);
        //Checks if it contains this string
        //System.out.println(visitedPos.containsKey(currentPosition));
        findMoves(vehicles.elementAt(1), 0, new PositionNode(currentPosition, null, null), vehicles, locationsQue, visitedPos);
        while(!locationsQue.isEmpty()) {
            PositionNode check = locationsQue.poll();
            String cStr = check.getPosition();
            if(cStr.charAt(17) == 'R'){
                if(check.getDistance() == 1){
                    System.out.println(check.getDistance() + " move:");
                }
                else{
                    System.out.println(check.getDistance() + " moves:");
                }
                printMoves(check);
                return;
            }
        }
    }

    private static void findMoves(Vehicle v, int pos, PositionNode parent, Vector<Vehicle> vehicles, 
                                  PriorityQueue<PositionNode> locationsQue, HashMap<String, String> visitedPos) {
        //Isolate line
        String map = parent.getPosition();
        String line = "";
        int startPos;
        int length = v.getLength();
        int nPos;
        if(v.getDir().equals("h")) {
            nPos = (int) Math.floor(pos/6);
            line = map.substring(nPos * 6, (nPos + 1) * 6);
            startPos = pos % 6;
        }
        else {
            nPos = pos % 6;
            for(int i = 0; i < 6; i++) {
                line += map.charAt(nPos + (i * 6));
            }
            startPos = (int) Math.floor(pos/6);
        }
        //System.out.println(line);
        //Iterate till collision, adding each valid space to queue
        for(int i = startPos + length; i < 6; i++) {
            if (line.charAt(i) == ' ') {
                int moveDis = i + 1 - (startPos + length);
                String move = v.getColor() + " " + Integer.toString(moveDis) + " R";
                // String A = map.substring(0, startPos);
                // String B = map.substring(startPos, startPos + length);
                // String C = map.substring(startPos + length, startPos + length + moveDis);
                // String D = map.substring(startPos + length + moveDis, 36);
                // String newMap = A + C + B + D;
                String new newMap[][] = 
                for(int j = 0; j < moveDis; j++) {
                    char swapChar = newMap[startPos + length + j][];

                }

                if(visitedPos.containsKey(newMap)) {
                    locationsQue.add(new PositionNode(newMap, parent, move));
                }
            }
            else {
                break;
            }
        }
        for(int i = startPos - 1; i > 0; i--) {
            if (line.charAt(i) == ' ') {
                
                if(visitedPos.containsKey(newMap)) {
                    locationsQue.add(new PositionNode(newMap, parent, move));
                }
            }
            else {
                break;
            }
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

    private static String[][] string2map(String map){
        String[][] array = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                array[i][j] = String.valueOf(map.charAt((i*6) + j));
            }
        }
        return array;
    }

    /*
     * This method scans a string representation of the board and passes each vehicle+position to findNext
     */
    private static void findCars(String currentPosition, Vector<Vehicle> vehicles, PriorityQueue<PositionNode> locationsQue){
        PriorityQueue<PositionNode> q = locationsQue;
        String v = "";
        for(int i = 0; i < currentPosition.length(); i++){
            int pos = v.indexOf(currentPosition.charAt(i));
            if(pos == -1) { 
                break; 
            }
            //findMoves()
            int vindex = 0;
            if(currentPosition.charAt(i) != 'R'){
                vindex = (int) currentPosition.charAt(i);
            }
            //findMoves(vehicles.get(vindex), pos, currentPosition, vehicles, q);
        }
    }

    

    private static void printMoves(PositionNode node){
        if(node.getParent() != null){
            printMoves(node.getParent());
        }
        System.out.println(node.getMove());
    }

}