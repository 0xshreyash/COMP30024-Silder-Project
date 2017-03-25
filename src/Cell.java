/**
 * Created by ShreyashPatodia on 18/03/17.
 */

import java.util.HashMap;


public class Cell {

    Vector2 pos;
    char value;

    HashMap<String, Cell> neighbours;

    public Cell(Vector2 Position) {

        this.pos = Position;
        this.neighbours = new HashMap<>();
    }

    public Cell(Vector2 Position, char initialValue) {

        this.value = initialValue;
        this.pos = Position;
        this.neighbours = new HashMap<>();
    }

    public char getValue() {

        return this.value;
    }

    public void setValue(char val) {

        this.value = val;
    }

    public Vector2 getPos() {

        return this.pos;
    }

    public void setNeighbour(String key, Cell neighbour) {

        this.neighbours.put(key, neighbour);
    }

    public Cell getNeighbour(String key) {

        return this.neighbours.get(key);
    }

    @Override
    public String toString() {

        return String.valueOf(value);
    }
}
