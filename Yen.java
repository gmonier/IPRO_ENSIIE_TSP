import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Yen extends Devise {

	Yen(){
		this.symbol = "¥";
        this.values = new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal("500"), new BigDecimal("100"), new BigDecimal("50"),
                new BigDecimal("10"), new BigDecimal("5"), new BigDecimal("1")));
	}
}
