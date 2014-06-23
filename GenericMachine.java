import java.util.ArrayList;
import java.util.HashMap;


public abstract class GenericMachine<I>{

    // Items available in the machine
	protected ArrayList<I> availableItems;
    // Amount of each items available in the machine
    protected HashMap<I, Integer> itemsAmount;

    GenericMachine() {
        availableItems = new ArrayList<I>();
        itemsAmount = new HashMap<I, Integer>();
    }

    protected void addAvailableItem(I availableItem) {
        this.availableItems.add(availableItem);
    }

    protected void addAvailableItems(ArrayList<I> availableItems) {
        this.availableItems.addAll(availableItems);
    }

    protected void deleteAvailableItem(I availableItem) {
        this.availableItems.remove(availableItem);
    }

   /* public String toString() {
       return availableItems.get(1).toString();
    }*/

    protected void setItemAmount(I item, Integer amount) {
        itemsAmount.put(item, amount);
    }

    protected Integer getItemAmount(I item) {
        return itemsAmount.get(item);
    }

    protected void printDevise() {
        //assertNotNull("Machine's currency can't be null", this.availableCoins);
        //System.out.println(this.availableCoins.toString());
        //D.values();
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
