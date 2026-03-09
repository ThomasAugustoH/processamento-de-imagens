package src;

public class Coordinates implements Comparable<Coordinates>{
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ")";
    }

    @Override
    public int compareTo(Coordinates o) {
        if (this.getX() > o.getX()) {
            return 1;
        } else if (this.getX() < o.getX()) {
            return -1;
        } else if (this.getY() > o.getY()) {
            return 1;
        } else if (this.getY() < o.getY()) {
            return -1;
        }

        return 0;
    }
}
