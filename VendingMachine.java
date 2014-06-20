import java.util.ArrayList;
import java.util.HashMap;

public class VendingMachine<D extends Devise> extends GenericMachine<Product,D>{

    // Amount of each product available in the machine
    protected HashMap<Product, Integer> productsPrice;


    VendingMachine()  {
        super();
        this.productsPrice = new HashMap<Product, Integer>();
    }

    public void setProductPrice(Product item, Integer amount) {
        productsPrice.put(item, amount);
    }

    public Integer getProductPrice(Product item) {
        return productsPrice.get(item);
    }

    public void deleteAvailableItem(Product availableItem) {
        super.deleteAvailableItem(availableItem);
        this.productsPrice.remove(availableItem);
    }

}
