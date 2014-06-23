import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Euro extends Devise {

	Euro(){
		this.symbol = "€";
        this.values = new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal("2"), new BigDecimal("1"), new BigDecimal("0.5"),
                new BigDecimal("0.2"), new BigDecimal("0.1"), new BigDecimal("0.05"), new BigDecimal("0.02"), new BigDecimal("0.01")));
	}
}
