import java.math.BigDecimal;

public class Product{

	protected String name;
	protected BigDecimal price;

	Product(String n, BigDecimal p){
		this.name=n;
		this.price=p;
	}

	public String getName(){
		return name;
	}

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice(){
		return price;
	}

	public String toString(){

		String s = "Le produit "+name+" coûte : "+price;
		return s;

	}
}