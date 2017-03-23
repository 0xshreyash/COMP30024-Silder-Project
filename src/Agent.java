/**
 * Created by ShreyashPatodia on 18/03/17.
 */
import java.util.ArrayList;

public abstract class Agent {

    ArrayList<Cell> myCells;
    ArrayList<String> legalDirections;

    public Agent(int N) {

        this.myCells = new ArrayList<>(N);
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

        for(Cell myCell : this.myCells) {
            for(String legalDirection : this.legalDirections) {
                char value;
                if(myCell.neighbours.containsKey(legalDirection)) {
                    value = myCell.getNeighbour(legalDirection).getValue();
                }
                else {
                    value = '-';
                }

                if(value == '+') {

                    legalMoves++;

                }
            }
        }

        return legalMoves;
    }

    public abstract void setAllDirections();
}
