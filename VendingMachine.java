import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class VendingMachine<C extends Currency> extends GenericMachine<Product>{


    // Currency available in the machine
    protected C currency;
    // Amount of each currency's coins available in the machine
    protected HashMap<BigDecimal, Integer> coinsAmount;
    // Contains each coins entered by user in Vending Machine
    protected ArrayList<BigDecimal> enteredCoins = new ArrayList<BigDecimal>() ;

    VendingMachine(C currency)  {
        super();
        this.currency = currency;
        coinsAmount = new HashMap<BigDecimal, Integer>();
    }

    protected void addCoinsAmount(BigDecimal value, Integer amount) {
        coinsAmount.put(value, amount);
    }

    protected Integer getCoinsAmount(BigDecimal value) {
        return coinsAmount.get(value);
    }

    protected void addEnteredCoin(String coin) {
        this.enteredCoins.add(new BigDecimal(coin));
    }

    protected BigDecimal getEnteredSum() {
        BigDecimal sum = new BigDecimal("0");
        for (BigDecimal coin : enteredCoins) {
            sum.add(coin);
        }
        return sum;
    }

    protected ArrayList <BigDecimal> giveChange(BigDecimal sumToGive) {
        ArrayList<BigDecimal> returnedCoins = new ArrayList<BigDecimal>();

        //giveMaxWithUpperValue(currency.getValues(), BigDecimal upperValue)


        return returnedCoins;
    }

    /* Function called when a user wants to buy a product
     *  @return 0 when the sell is a success
     *      1 when product doesn't exist
     *      2 when user didn't enter enought coins
     *      3 when vending machine can't give user his change
     */
    protected int buyProduct(String productName) {
        Product product = this.getProduct(productName);
        if(product == null) {
            return 1;
        }
        if(product.getPrice().subtract(this.getEnteredSum()).compareTo(new BigDecimal("0")) == 1) {
            return 2;
        }

        return 154545;

    }

    protected ArrayList<String> getCurrencyStringValues() {
        return this.currency.getStringValues();
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

    /* Give list's max value
     * @return null when list is empty
     */
    private BigDecimal giveMax(ArrayList <BigDecimal> list) {
        BigDecimal max;

        if(list.size()==0) {
            return null;
        }
        else {
            max = list.get(0);
        }

        for (BigDecimal value : list) {
            if(max.subtract(value).compareTo(new BigDecimal("0")) !=1 )
                max = value;
        }

        return max;
    }

    /* Give list's max value which is less than upperValue
     * @return null when list is empty or if there is no value which is less than
     */
    private BigDecimal giveMaxWithUpperValue(ArrayList <BigDecimal> list, BigDecimal upperValue) {
        int i = 0;
        BigDecimal max = null;

        if(list.size()==0) {
            return null;
        }

        while(i<list.size()) {
            if (max == null && upperValue.subtract(list.get(i)).compareTo(new BigDecimal("0")) != 1) {
                max = list.get(i);
            }
            else if (upperValue.subtract(list.get(i)).compareTo(new BigDecimal("0")) != 1 &&
                     max.subtract(list.get(i)).compareTo(new BigDecimal("0")) != 1) {
                max = list.get(i);
            }

            i++;
        }

        return max;
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
