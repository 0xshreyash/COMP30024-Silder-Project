/**
 * Created by Shreyash Patodia and Ho Suk Lee.
 * Student numbers: Shreyash - 767336, Hoe - Add student number here.
 * Subject: COMP30024 Artificial Intelligence.
 * Semester 1, 2016.
 */

import java.util.ArrayList;

public abstract class Agent {
    ArrayList<Cell> myCells;
    ArrayList<String> legalDirections;

    public Agent(int size) {

        // cells that I control
        this.myCells = new ArrayList<>(size);
        this.legalDirections = new ArrayList<>();
        this.setAllDirections();
    }

    public void addLegalDirection(String direction) {

        this.legalDirections.add(direction);
    }

    public void addCell(Cell newCell) {

        this.myCells.add(newCell);

    }

    public void removeCell(Cell oldCell) {

        this.myCells.remove(oldCell);

    }

    public boolean hasCell(Cell cell) {

        return this.myCells.contains(cell);
    }

    public int getLegalMoves() {

        int legalMoves = 0;

        for (Cell myCell : this.myCells) {

            for (String legalDirection : this.legalDirections) {

                char value;

                if (myCell.neighbours.containsKey(legalDirection)) {

                    value = myCell.getNeighbour(legalDirection).getValue();
                } else {

                    value = Board.CELL_UNKNOWN;
                }

                if (value == Board.CELL_EMPTY) {

                    legalMoves++;

                }
            }
        }

        return legalMoves;
    }

    public abstract void setAllDirections();
}
