import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class Peso extends Devise {

	Peso(){
		this.symbol = "MXN";
        this.values = new ArrayList<BigDecimal>(Arrays.asList(new BigDecimal("50"), new BigDecimal("20"), new BigDecimal("10"),
                new BigDecimal("5"), new BigDecimal("2"), new BigDecimal("1"), new BigDecimal("0.5"), new BigDecimal("0.2"),
                new BigDecimal("0.1"), new BigDecimal("0.05")));
	}
}
