package stringwz;

import stringwz.cash.*;
import stringwz.eqp.*;

public class LocalizeProxy {

    public static final int EQP = 0;
    public static final int CASH = 1;

    public static void localize(int target) {
        switch (target) {
            case EQP:
                new EqpLocalization().localize();
                break;
            case CASH:
                new CashLocalization().localize();
                break;
            default:
                throw new RuntimeException("Unknown target: " + target);
        }
    }
}
