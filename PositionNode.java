public class PositionNode {
    private String position;
    private int distance;
    private PositionNode parent;
    private String move;
    
    /**
     * Creates an object for storage in a priority queue,
     * holding relevant information for Rush Hour
     * @param position the string map representing the current board
     * @param parentNode the previous state
     * @param move the move that was performed to get here
     */
    public PositionNode(String position, PositionNode parentNode, String move) {
        this.position = position;
        this.move = move;
        setParent(parentNode);
        if(this.getParent() != null) {
            distance = parent.getDistance() + 1;
        }
        else {
            distance = 0;
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