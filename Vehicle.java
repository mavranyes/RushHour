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
        setxPos(xPos - 1);
        setyPos(yPos - 1);
        setPlace(place);
    }

    //Populate map
    public void populateMap(String[][] map) {
        //Probably add checks later on
        if(dir.equals("h")) {
            for(int i = 0; i < length; i++) {
                map[yPos][xPos + i] = String.valueOf(place);
            }
        }
        else {
            for(int i = 0; i < length; i++) {
                map[yPos + i][xPos] = String.valueOf(place);
            }
        }
    }

    public void findMoves(String[][] map) {
        if(dir.equals("h")) {
            for(int i = 0; (i + xPos) < 6; i++) {
                if (map[yPos][xPos + length + i].equals(" ")) {
                    //add to possible list
                }
                else {
                    break;
                }
            }
            for(int i = 0; (i + xPos) > 0; i--) {
                if (map[yPos][xPos - i - 1].equals(" ")) {
                    //add to possible list
                }
                else {
                    break;
                }
            }
        }
        else {
            for(int i = 0; (i + yPos) < 6; i++) {
                if (map[yPos + length + i][xPos].equals(" ")) {
                    //add to possible list
                }
                else {
                    break;
                }
            }
            for(int i = 0; (i + yPos) > 0; i--) {
                if (map[yPos - i - 1][xPos].equals(" ")) {
                    //add to possible list
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

    private void setPlace(int place) {
        if(color.equals("red")) {
            this.place = "R";
        }
        else {
            this.place = String.valueOf(place);
        }
    }
}
