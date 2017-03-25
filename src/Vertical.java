/**
 * Created by ShreyashPatodia on 24/03/17.
 */
public class Vertical extends Agent {

    private String[] directions = {
            Board.DIR_UP,
            Board.DIR_LEFT,
            Board.DIR_RIGHT
    };

    public Vertical(int size) {

        super(size);
    }

    @Override
    public void setAllDirections() {

        for (String direction : directions) {

            this.addLegalDirection(direction);
        }

        return;
    }


}
