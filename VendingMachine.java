import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VendingMachine<D extends Devise> extends GenericMachine<Product>{


    // Devise available in the machine
    protected D devise;
    // Amount of each devise's coins available in the machine
    protected HashMap<BigDecimal, Integer> coinsAmount;

    VendingMachine(D devise)  {
        super();
        this.devise = devise;
        coinsAmount = new HashMap<BigDecimal, Integer>();
    }

    protected void addCoinsAmount(BigDecimal value, Integer amount) {
        coinsAmount.put(value, amount);
    }

    protected Integer getCoinsAmount(BigDecimal value) {
        return coinsAmount.get(value);
    }

    protected ArrayList<String> getDeviseStringValues() {
        return this.devise.getStringValues();
    }

    public void addDrink(String name, BigDecimal price, Integer quantity) {
        Drink drink = new Drink(name, price);
        this.availableItems.add(drink);
        setItemAmount(drink, quantity);

    }

    public void addFood(String name, BigDecimal price, Integer quantity) {
        Food food = new Food(name, price);
        this.availableItems.add(food);
        setItemAmount(food, quantity);

    }

    public void addProduct(String name, BigDecimal price, Integer quantity) {
        Product product = new Product(name, price);
        this.availableItems.add(product);
        setItemAmount(product, quantity);
    }

    public Product getProduct(String name) {
        for (Product product : availableItems) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public BigDecimal getProductPrice(String name) {
        Product product = getProduct(name);
        if (product != null) {
            return product.getPrice();
        }
        return null;
    }

    public void setProductPrice(String name, BigDecimal price) {
        Product product = getProduct(name);
        if (product != null) {
             product.setPrice(price);
        }
    }

    public Integer getProductAmount(String name) {
        Product product = getProduct(name);
        if (product != null) {
            return getItemAmount(product);
        }
        return null;
    }

    public void setProductAmount(String name, Integer amount) {
        Product product = getProduct(name);
        if (product != null) {
            setItemAmount(product, amount);
        }
    }

    public ArrayList<String> getSpecificProducts (Class specificClass) {
        ArrayList<String> specificProductList = new ArrayList<String>();
        for (Product product : availableItems) {
            if (product.getClass().equals(specificClass)) {
                specificProductList.add(product.getName());
            }
        }
        return specificProductList;
    }

    public ArrayList<String> getProducts () {
        ArrayList<String> specificProductList = new ArrayList<String>();
        for (Product product : availableItems) {
            specificProductList.add(product.getName());
        }
        return specificProductList;
    }

    public void deleteProduct(String name) {
        Product product = getProduct(name);
        if (product != null) {
            super.deleteAvailableItem(product);
            this.coinsAmount.remove(product);
        }
    }

    public void printVendingMachine (){
        System.out.println("La vending machine est composé de :");
        for (String productName : getProducts()) {
            System.out.println("   "+getProduct(productName).toString()+" et est disponible en "+getProductAmount(productName)+" exemplaire");
        }
    }

    public void printVendingMachineDetails (){
        System.out.println("La vending machine est composé de :");
        ArrayList<Class> classes = new ArrayList<Class>(Arrays.asList(Drink.class, Food.class, Product.class));
        for (Class classe : classes) {
            System.out.println("Classe : "+classe.toString());
            for (String productName : getSpecificProducts(classe)) {
                System.out.println("   "+getProduct(productName).toString()+" et est disponible en "+getProductAmount(productName)+" exemplaire");
            }
        }

    }
}
