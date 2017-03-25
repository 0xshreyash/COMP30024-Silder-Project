/**
 * Created by ShreyashPatodia on 18/03/17.
 */
public class Vector2 {

    private int x;
    private int y;

    public Vector2(int xPos, int yPos) {

        this.x = xPos;
        this.y = yPos;
    }

    public boolean equals(Vector2 other) {

        return this.x == other.getX() && this.y == other.getY();
    }

    public int getX() {

        return this.x;
    }

    public void setX(int xPos) {

        this.x = xPos;
    }

    public int getY() {

        return this.y;
    }

    public void setY(int yPos) {

        this.y = yPos;
    }

    public Vector2 getPos() {

        return this;
    }

    public void setPos(Vector2 other) {

        setX(other.getX());
        setY(other.getY());
    }

    @Override
    public String toString() {

        return "Postion is: (" + this.getX() + ", " + this.getY() + ")";
    }
}
