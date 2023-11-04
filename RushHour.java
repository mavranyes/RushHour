import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RushHour {
    public static void main(String[] args) {
        String[][] map = new String[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                map[i][j] = " ";
            }
        }
        parseInput(map);
        printMap(map);
    }

    private static void parseInput(String[][] map) {
        try{
            File file = new File("input.txt");
            Scanner scan = new Scanner(file);
            int carCount = Integer.parseInt(scan.nextLine());
            for (int i = 0; i < carCount; i++) {
                String type = scan.nextLine();
                String color = scan.nextLine();
                String dir = scan.nextLine();
                int xPos = Integer.parseInt(scan.nextLine());
                int yPos = Integer.parseInt(scan.nextLine());
                Vehicle v = new Vehicle(type, color, dir, xPos, yPos, i);
                v.populateMap(map);
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
        for(int i = 5; i >= 0; i--){
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