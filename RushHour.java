import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class RushHour {
    public static void main(String[] args) {
        System.out.println("hello");

        char[][] map = new char[6][6];
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                map[i][j] = ' ';
            }
        }
        printMap(map);
    }

    private static void parseInput(char[][] map) {
        String temp = "";
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
            }
            
            scan.close();
        }
        catch(FileNotFoundException e) {
            System.err.println("File not found: " + "input.txt");
        }
    }

    private static void printMap(char[][] map) {
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

    private static void convertMaptoString() {

    }
}