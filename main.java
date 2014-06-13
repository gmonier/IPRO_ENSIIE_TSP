import java.math.BigDecimal;
import static org.junit.Assert.*;

class main{

	public static void main(String[] args){

		//Product coca = new Product("coca",new BigDecimal("0.90"));
		Product fanta = new Drink("pepsi",new BigDecimal("0.85"));  
		//System.out.println(coca.toString());
		System.out.println(fanta.toString());


		testEuro();
        testDrinkMachine();
	}
	
	static void testEuro(){
        System.out.println("Test Euro");
		System.out.println(Euro.TWOEUROS);
		
		for (int i = 0; i<Euro.values().length; i++)
		{
			System.out.println(Euro.values()[i]);
		}
	}

    static void testDrinkMachine(){
        System.out.println("Test Drink Machine");
        //Integer lol = null;
        //assertNotNull("lol can't be null", lol);
        DrinkMachine machine = new DrinkMachine<Euro>(Euro.ONECENT);
        machine.printDevise();
    }
}
