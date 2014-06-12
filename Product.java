import java.math.BigDecimal;

public abstract class Product{

	protected String name;
	protected BigDecimal price;

	Product(String n, BigDecimal p){
		this.name=n;
		this.price=p;
	}

	public String getName(){

		return name;
	
	}

	public BigDecimal getPrice(){

		return price;
	}

	public String toString(){

		String s = "Le produit "+name+" coûte : "+price;
		return s;

	}
}