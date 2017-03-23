/**
 * Created by ShreyashPatodia on 24/03/17.
 */
public class Vertical extends Agent {


    public Vertical(int N) {

        super(N);
    }

    @Override
    public void setAllDirections() {

        String []directions = {"up", "left", "right"};

        for(String direction : directions) {

            this.addLegalDirection(direction);
        }

        return;
    }


}
