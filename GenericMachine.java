import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


public abstract class GenericMachine<I, D extends Enum & Devise>{

    // Items available in the machine
	protected ArrayList<I> availableItems;
    // Amount of each items available in the machine
    protected HashMap<I, Integer> itemsAmount;
    // Devise's coins available in the machine
	protected ArrayList<D> availableCoins;
    // Amount of each devise's coins available in the machine
    protected HashMap<D, Integer> coinsAmount;

    GenericMachine() {
        availableItems = new ArrayList<I>();
        itemsAmount = new HashMap<I, Integer>();
        availableCoins = new ArrayList<D>();
        coinsAmount = new HashMap<D, Integer>();
    }

    public void addAvailableItem(I availableItem) {
        this.availableItems.add(availableItem);
    }

    public void addAvailableItems(ArrayList<I> availableItems) {
        this.availableItems.addAll(availableItems);
    }

    public void deleteAvailableItem(I availableItem) {
        this.coinsAmount.remove(availableItem);
        this.availableItems.remove(availableItem);
    }

   /* public String toString() {
       return availableItems.get(1).toString();
    }*/

    public void setItemAmount(I item, Integer amount) {
        itemsAmount.put(item, amount);
    }

    public Integer getItemAmount(I item) {
        return itemsAmount.get(item);
    }

    public void addAvailableCoin(D availableCoin) {
        this.availableCoins.add(availableCoin);
    }

    public void addAvailableCoins(ArrayList<D> availableCoins) {
        this.availableCoins.addAll(availableCoins);
    }

    public void printDevise() {
        //assertNotNull("Machine's devise can't be null", this.availableCoins);
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
