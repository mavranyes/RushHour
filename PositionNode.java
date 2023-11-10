public class PositionNode {
    private String position;
    private int distance;
    private PositionNode parent;
    private String move;
    
    public PositionNode(String position, PositionNode parentNode, String move) {
        this.position = position;
        this.move = move;
        setParent(parentNode);
        if(this.getParent() != null) {
            distance = parent.getDistance() + 1;
        }
    }

    //Getters
    public String getPosition() {
        return position;
    }

    public int getDistance() {
        return distance;
    }

    public PositionNode getParent() {
        return parent;
    }

    public String getMove() {
        return move;
    }

    //Setters
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setParent(PositionNode parent) {
        this.parent = parent;
    }
}