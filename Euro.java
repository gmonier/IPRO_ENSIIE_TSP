import java.math.BigDecimal;

public enum Euro implement devise {

	ONECENT (new BigDecimal ("0.01")), 
	TWOCENTS (new BigDecimal ("0.02")), 
	FIVECENTS (new BigDecimal ("0.05")), 
	TENCENTS (new BigDecimal ("0.1")), 
	TWENTYCENTS (new BigDecimal ("0.2")), 
	FIFTYCENTS (new BigDecimal ("0.5")), 
	ONEEURO (new BigDecimal ("1")), 
	TWOEUROS (new BigDecimal ("2"));

	private final BigDecimal value;

	Euro(BigDecimal valeur){
		valeur.compareTo (BigDecimal.ZERO) > 0;
		value=valeur;
	}

	public BigDecimal getValue(){
		return value;
	}

	public String toString(){
		return value + "€";
	}



}