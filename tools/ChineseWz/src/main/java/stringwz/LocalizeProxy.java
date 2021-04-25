package stringwz;

import stringwz.cash.*;
import stringwz.eqp.*;
import stringwz.map.*;
import stringwz.pet.*;
import stringwz.skill.*;

public class LocalizeProxy {

    public static final int EQP = 0;
    public static final int CASH = 1;
    public static final int MAP = 2;
    public static final int SKILL = 3;
    public static final int PET = 4;

    public static void localize(int target) {
        switch (target) {
            case EQP:
                new EqpLocalization().localize();
                break;
            case CASH:
                new CashLocalization().localize();
                break;
            case MAP:
                new MapLocalization().localize();
                break;
            case SKILL:
                new SkillLocalization().localize();
                break;
            case PET:
                new PetLocalization().localize();
                break;
            default:
                throw new RuntimeException("Unknown target: " + target);
        }
    }
}
