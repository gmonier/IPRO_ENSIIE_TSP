import java.math.BigDecimal;

class main{

	public static void main(String[] args){

		//Product coca = new Product("coca",new BigDecimal("0.90"));
		Product fanta = new Drink("pepsi",new BigDecimal("0.85"));  
		//System.out.println(coca.toString());
		System.out.println(fanta.toString());


		//testEuro();
        testVendingMachine();

	}
	
	static void testEuro(){
        System.out.println("Test Euro");
		System.out.println(Euro.TWOEUROS);
		
		for (int i = 0; i<Euro.values().length; i++)
		{
			System.out.println(Euro.values()[i]);
		}
	}

    static void testVendingMachine(){
        System.out.println("Test Vending Machine");
        //Integer lol = null;
        //assertNotNull("lol can't be null", lol);
        VendingMachine machine = new VendingMachine<Euro>();
        machine.addDrink("Coca", new BigDecimal("0.4"),15 );
        machine.printVendingMachine();
        machine.addFood("mars", new BigDecimal("0.9"),25 );
        machine.printVendingMachine();
        machine.addProduct("Davidch's mother", new BigDecimal("0.0000000000001"), 1);
        machine.printVendingMachine();
        machine.addDrink("Coca zero", new BigDecimal("0.5"),125 );
        machine.addFood("mars light", new BigDecimal("0.9999"),255 );
        machine.addProduct("Davidch's father", new BigDecimal("1000"), 1);
        machine.printVendingMachine();
        machine.printVendingMachineDetails();
        System.out.println("Suppression de tout");
        machine.deleteProduct("Coca");
        machine.deleteProduct("j'existe pas");
        machine.deleteProduct("mars");
        machine.deleteProduct("Davidch's mother");
        machine.deleteProduct("Coca zero");
        machine.deleteProduct("mars light");
        machine.deleteProduct("Davidch's father");
        machine.printVendingMachine();
    }
}
