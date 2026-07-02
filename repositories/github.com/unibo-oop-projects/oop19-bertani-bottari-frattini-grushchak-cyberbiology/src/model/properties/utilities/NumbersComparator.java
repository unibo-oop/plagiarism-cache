package model.properties.utilities;

import java.math.BigDecimal;

public interface NumbersComparator {
    
    static boolean isBiggerOrEqulalThan(final Number num1,final Number num2) {
        return compare(num2, num2) >= 0;
    }
    static boolean isBiggerThan(final Number num1,final Number num2) {
       return compare(num1, num2) > 0;
    }
    
    static int compare(final Number num1,final Number num2){
        final BigDecimal Bnum1 = new BigDecimal(num1.toString());
        final BigDecimal Bnum2 = new BigDecimal(num2.toString());
        return Bnum1.compareTo(Bnum2);
    }
}
