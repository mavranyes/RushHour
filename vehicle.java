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

    public Vehicle(String type, String color, String dir, int xPos, int yPos, int place) {
        setType(type);
        setColor(color);
        setDir(dir);
        setxPos(xPos);
        setyPos(yPos);
        setPlace(place);
    }

    //Populate map
    public void populateMap(String[][] map) {
        //Probably add checks later on
        if(dir == "h") {
            for(int i = 0; i < length; i++) {
                map[xPos - i][yPos] = String.valueOf(place);
            }
        }
        else {
            for(int i = 0; i < length; i++) {
                map[xPos][yPos - i] = String.valueOf(place);
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

    //Setters
    private void setType(String type) {
        this.type = type;
        if (type == "truck") {
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

    private void setPlace(int place) {
        if(color == "red") {
            this.place = "R";
        }
        else {
            this.place = String.valueOf(place);
        }
    }
}
