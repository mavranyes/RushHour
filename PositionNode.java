public class PositionNode {
    private String position;
    private int distance;
    private PositionNode parent;
    
    public PositionNode(String position) {
        this.position = position;
        distance = Integer.MAX_VALUE;
        parent = null;
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

    //Setters
    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setParent(PositionNode parent) {
        this.parent = parent;
    }
}
