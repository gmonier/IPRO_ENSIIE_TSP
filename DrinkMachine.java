import java.util.ArrayList;

public class DrinkMachine<D> extends GenericMachine<Drink,D>{

    DrinkMachine(D d)  {
        this.soldProducts = new ArrayList<Drink>();
        this.devise = d;
    }

}
