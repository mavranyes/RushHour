/**
 * vehicle
 */
public class vehicle {
    private String type;
    private String color;
    private String dir;
    private int xPos;
    private int yPos;
    private int length;

    public vehicle(String type, String color, String dir, int xPos, int yPos) {
        setType(type);
        setColor(color);
        setDir(dir);
        setxPos(xPos);
        setyPos(yPos);

    }

    //Populate map
    public void populateMap(String[][] map) {
        //Probably add checks later on
        
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
}
