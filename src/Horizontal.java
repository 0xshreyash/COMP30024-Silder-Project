/**
 * Created by ShreyashPatodia on 24/03/17.
 */
public class Horizontal extends Agent {

    public Horizontal(int N) {

        super(N);
    }

    @Override
    public void setAllDirections() {

        String []directions = {"up", "down", "right"};

        for(String direction : directions) {

            this.addLegalDirection(direction);
        }

        return;

    }
}
