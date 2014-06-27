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
}
