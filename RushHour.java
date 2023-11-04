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

    private static void parseInput() {

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