package DDD.framework.basic;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;

public class Numbers {

    public static int requireInRange(int value, int min, int max) {
        Preconditions.checkArgument(min <= value, "min (%s) must be less than or equal to value1 (%s)", min, value);
        Preconditions.checkArgument(value <= max, "value1 (%s) must be less than or equal to max (%s)", value, max);
        return value;
    }

    public static BigDecimal requirePositif(BigDecimal bigDecimal, String msg) {
        if (bigDecimal.compareTo(BigDecimal.ZERO) != 1)
            throw new IllegalArgumentException(msg);
        return bigDecimal;
    }
}
