import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Currency {

    protected String symbol;
    protected ArrayList <BigDecimal> values ;

    public String getSymbol() {
        return symbol;
    }

    public ArrayList <BigDecimal> getValues() {
        return this.values;
    }

    public ArrayList <String> getStringValues() {
        ArrayList <String> result= new ArrayList<String>();
        for (BigDecimal value : this.values) {
            result.add(value.toString());
        }
        return result;
    }
}
