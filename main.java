import java.math.BigDecimal;
class main{

	public static void main(String[] args){

		//Product coca = new Product("coca",new BigDecimal("0.90"));
		Product fanta = new Drink("pepsi",new BigDecimal("0.85"));  
		//System.out.println(coca.toString());
		System.out.println(fanta.toString());
		
		testEuro();
	}
	
	static void testEuro(){
		System.out.println(Euro.TWOEUROS);
		
		for (int i = 0; i<Euro.values().length; i++)
		{
			System.out.println(Euro.values()[i]);
		}
	}
}
