import java.util.ArrayList;
import static org.junit.Assert.*;


public abstract class GenericMachine<P,D>{

	protected ArrayList<P> soldProducts;
	protected D devise;

    public void printDevise() {
        assertNotNull("Machine's devise can't be null", this.devise);
        System.out.println(this.devise.toString());
    }

	/*public String getName(){

		return name;
	
	}

	public BigDecimal getPrice(){

		return price;
	}

	public String toString(){

		String s = "Le produit "+name+" coûte : "+price;
		return s;

	}*/
}
