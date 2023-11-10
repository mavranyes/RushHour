import java.util.PriorityQueue;
/**
 * vehicle
 */
public class Vehicle {
    private String type;
    private String color;
    private String dir;
    private int xPos;
    private int yPos;
    private int length;
    private String place;

    public Vehicle(String type, String color, String dir, int xPos, int yPos, String place) {
        setType(type);
        setColor(color);
        setDir(dir);
        setxPos(xPos - 1);
        setyPos(yPos - 1);
        setPlace(place);
    }

    //Populate map
    public void populateMap(String[][] map) {
        //Probably add checks later on
        if(dir.equals("h")) {
            for(int i = 0; i < length; i++) {
                //map[yPos][xPos + i] = String.valueOf(place);
                map[yPos][xPos + i] = place;
            }
        }
        else {
            for(int i = 0; i < length; i++) {
                //map[yPos + i][xPos] = String.valueOf(place);
                map[yPos + i][xPos] = place;
            }
        }
    }

    public void findMoves(String[][] map, PriorityQueue<PositionNode> locationsQue) {
        if(dir.equals("h")) {
            for(int i = 0; (i + xPos + length) < 6; i++) {
                if (map[yPos][xPos + length + i].equals(" ")) {
                    System.out.println("X: " + (xPos + length + i) + " Y: " + yPos);
                }
                else {
                    break;
                }
            }
            for(int i = 1; (xPos - i) > 0; i++) {
                if (map[yPos][xPos - i].equals(" ")) {
                    System.out.println("X: " + (xPos - i - 1) + " Y: " + yPos);
                }
                else {
                    break;
                }
            }
        }
        else {
            for(int i = 0; (i + yPos + length) < 6; i++) {
                if (map[yPos + length + i][xPos].equals(" ")) {
                    System.out.println("X: " + xPos + " Y: " + (yPos + length + i));
                }
                else {
                    break;
                }
            }
            for(int i = 1; (yPos - i) > 0; i++) {
                if (map[yPos - i][xPos].equals(" ")) {
                    System.out.println("X: " + xPos + " Y: " + (yPos - i - 1));
                }
                else {
                    break;
                }
            }
        }
    }

    //Getters
    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public String getDir() {
        return dir;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
    
    public String getPlace() {
        return place;
    }

    public int getLength() {
        return length;
    }

    //Setters
    private void setType(String type) {
        this.type = type;
        if (type.equals("truck")) {
            length = 3;
        }
        else {
            length = 2;
        }
    }

    private void setColor(String color) {
        this.color = color;
    }

    private void setDir(String dir) {
        this.dir = dir;
    }

    private void setxPos(int xPos) {
        this.xPos = xPos;
    }

    private void setyPos(int yPos) {
        this.yPos = yPos;
    }

    private void setPlace(String place) {
        if(color.equals("red")) {
            this.place = "R";
        }
        else {
            //this.place = String.valueOf(place);
            this.place = place;
        }
    }
}