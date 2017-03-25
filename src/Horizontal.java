/**
 * Created by ShreyashPatodia on 24/03/17.
 */
public class Horizontal extends Agent {

    private String[] directions = {
            Board.DIR_UP,
            Board.DIR_DOWN,
            Board.DIR_RIGHT
    };

    public Horizontal(int size) {

        super(size);
    }

    @Override
    public void setAllDirections() {

        for (String direction : directions) {

            this.addLegalDirection(direction);
        }
    }
}
