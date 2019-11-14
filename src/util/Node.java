package util;

public class Node {
    private int x;
    private int y;
    private int cout;
    private int heuristic;
    private Node cameFrom;

    public Node(int x, int y, int cout, int heuristic, Node cameFrom) {
        this.x = x;
        this.y = y;
        this.cout = cout;
        this.heuristic = heuristic;
        this.cameFrom = cameFrom;
    }

    public Node getCameFrom() {
        return cameFrom;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCout() {
        return cout;
    }

    public int getHeuristic() {
        return heuristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return x == node.x &&
                y == node.y;
    }
}
