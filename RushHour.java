import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.Comparator;

/*
 * Created to solve the Rush Hour board game
 * in a minimum number of moves
 */
class RushHour {
    public static void main(String[] args) {
        //Initalizes supporting data structures
        PriorityQueue<PositionNode> locationsQue = new PriorityQueue<PositionNode>(distComp);
        Vector<Vehicle> vehicles = new Vector<Vehicle>();
        HashMap<String, String> visitedPos = new HashMap<String, String>();

        String[][] map = new String[6][6];
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 6; j++) {
                map[i][j] = " ";
            }
        }
        parseInput(map, vehicles);

        //Populate hash map
        String currentPosition = convertMaptoString(map);
        visitedPos.put(currentPosition, currentPosition);
        
        //Popuate priority queue
        PositionNode firstParent = new PositionNode(currentPosition, null, "x");
        locationsQue.add(firstParent);
        
        //Iterate through each possible position
        //till solution is found or no moves are left
        while(!locationsQue.isEmpty()) {
            PositionNode check = locationsQue.poll();

            //Check for a win
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

            //Find adjacent moves
            findCars(check, vehicles, locationsQue, visitedPos);
        }
    }

    /**
     * Finds the possible moves for the car provided
     * and adds the valid ones to the priority queue
     * @param v the vehicle object found
     * @param pos the position of the vehicle
     * @param parent the object from where this vehicle was found
     * @param locationsQue the priority queue to store new states
     * @param visitedPos the previously visted positions
     */
    private static void findMoves(Vehicle v, int pos, PositionNode parent, 
                                  PriorityQueue<PositionNode> locationsQue, HashMap<String, String> visitedPos) {
        //Isolate line for analysis of possible moves
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

        //Iterate till collision, adding each valid space to queue
        //For down and to the right moves
        for(int i = startPos + length; i < 6; i++) {
            if (line.charAt(i) == ' ') {
                int moveDis = i + 1 - (startPos + length);

                //Select which direction it is moving
                String dir = " D";
                if(v.getDir().equals("h")) {
                    dir = " R";
                }

                //Perform move
                String move = v.getColor() + " " + Integer.toString(moveDis) + dir;
                String newMap[][] = string2map(map);
                if(v.getDir().equals("h")) {
                    for(int j = 0; j < moveDis; j++) {
                        String swapChar = newMap[nPos][startPos + length + j];
                        newMap[nPos][startPos + length + j] = newMap[nPos][startPos + j];
                        newMap[nPos][startPos + j] = swapChar;
                    }
                }
                else {
                    for(int j = 0; j < moveDis; j++) {
                        String swapChar = newMap[startPos + length + j][nPos];
                        newMap[startPos + length + j][nPos] = newMap[startPos + j][nPos];
                        newMap[startPos + j][nPos] = swapChar;
                    }
                }
                String curPos = convertMaptoString(newMap);

                //Check if move is valid
                if(!visitedPos.containsKey(curPos)) {
                    visitedPos.put(curPos, curPos);
                    locationsQue.add(new PositionNode(curPos, parent, move));
                }
            }
            else {
                break;
            }
        }

        //For up and to the left moves
        for(int i = startPos - 1; i >= 0; i--) {
            if (line.charAt(i) == ' ') {
                int moveDis = startPos - i;

                //Select which direction it is moving
                String dir = " U";
                if(v.getDir().equals("h")) {
                    dir = " L";
                }

                //Perform move
                String move = v.getColor() + " " + Integer.toString(moveDis) + dir;
                String newMap[][] = string2map(map);
                if(v.getDir().equals("h")) {
                    for(int j = 0; j < moveDis; j++) {
                        if((startPos + length - 1 - j) == 6){
                            int x = 1;
                        }
                        String swapChar = newMap[nPos][startPos - j - 1];
                        newMap[nPos][startPos - j - 1] = newMap[nPos][startPos + length - 1 - j];
                        newMap[nPos][startPos + length - 1 - j] = swapChar;
                    }
                    printMap(newMap);
                }
                else {
                    for(int j = 0; j < moveDis; j++) {
                        String swapChar = newMap[startPos - j - 1][nPos];
                        newMap[startPos - j - 1][nPos] = newMap[startPos + length - 1 - j][nPos];
                        newMap[startPos + length - 1 - j][nPos] = swapChar;
                    }
                }
                String curPos = convertMaptoString(newMap);

                //Check if move is valid
                if(!visitedPos.containsKey(curPos)) {
                    visitedPos.put(curPos, curPos);
                    locationsQue.add(new PositionNode(curPos, parent, move));
                }
            }
            else {
                break;
            }
        }
    }

    /**
     * The program is used during initalization to take in vehicle information,
     * create vehicle objects, and populate the map
     * @param map a 6 x 6 map to be populated
     * @param vehicles the list of vehicles
     */
    private static void parseInput(String[][] map, Vector<Vehicle> vehicles) {
        try{
            File file = new File("input4.txt");
            Scanner scan = new Scanner(file);
            int carCount = Integer.parseInt(scan.nextLine());

            //Creates a vehicle object and adds it to the map
            for (int i = 0; i < carCount; i++) {
                String type = scan.nextLine();
                String color = scan.nextLine();
                String dir = scan.nextLine();
                int yPos = Integer.parseInt(scan.nextLine());
                int xPos = Integer.parseInt(scan.nextLine());
                String place = moreCars(i);
                Vehicle v = new Vehicle(type, color, dir, xPos, yPos, place);
                v.populateMap(map);
                vehicles.addElement(v);
            }
            scan.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found: " + "input.txt");
        }
    }

    /**
     * Prints the map in ascii art
     * @param map the 6 x 6 map to be printed
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

    /**
     * @param map the map to be converted into a string
     * @return the two demensional map as a string
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

    /**
     * @param map the string map
     * @return the string map as a two demensional array
     */
    private static String[][] string2map(String map) {
        String[][] array = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                array[i][j] = String.valueOf(map.charAt((i*6) + j));
            }
        }
        return array;
    }
     
    /**
     * This method scans a string representation of the board and passes each vehicle+position to findNext
     * @param parent the current position to be evaluated
     * @param vehicles the list of all imported vehicles
     * @param locationsQue the queue of locations to visit
     * @param visitedPos the hash map of already visited locations
     */
    private static void findCars(PositionNode parent, Vector<Vehicle> vehicles, 
                                PriorityQueue<PositionNode> locationsQue, HashMap<String, String> visitedPos){
        String v = " ";
        String currentPosition = parent.getPosition();
        for(int i = 0; i < currentPosition.length(); i++){
            int pos = v.indexOf(currentPosition.charAt(i));
            if(pos != -1) { 
                continue; 
            }
            v += currentPosition.charAt(i);
            int vindex = 0;
            if(currentPosition.charAt(i) != 'R'){
                vindex = Character.getNumericValue(currentPosition.charAt(i));
            }
            findMoves(vehicles.elementAt(vindex), i, parent, locationsQue, visitedPos);
        }
    }

    /**
     * Used to override the default priority list comparitor
     */
    private static Comparator<PositionNode> distComp = new Comparator<PositionNode>() {
        @Override
        public int compare(PositionNode one, PositionNode two){
            return Integer.compare(one.getDistance(), two.getDistance());
        }
    };

    /**
     * Recuresively prints the moves, starting from
     * the root node
     * @param node the ending node
     */
    private static void printMoves(PositionNode node) {
        if(node.getParent() != null){
            printMoves(node.getParent());
        }
        else {
            return;
        }
        System.out.println(node.getMove());
    }

    /**
     * Used to overcome a limitation of numbers
     * @param i the int to be converted
     * @return the corresponding int
     */
    private static String moreCars(int i) {
        String[] place = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        return place[i];
    }
}