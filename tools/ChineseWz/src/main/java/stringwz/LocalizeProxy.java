package stringwz;

import stringwz.cash.*;
import stringwz.consume.*;
import stringwz.eqp.*;
import stringwz.etc.*;
import stringwz.eula.*;
import stringwz.map.*;
import stringwz.mob.*;
import stringwz.monsterbook.*;
import stringwz.npc.*;
import stringwz.pet.*;
import stringwz.petdialog.*;
import stringwz.skill.*;

public class LocalizeProxy {

    public static final int EQP = 0;
    public static final int CASH = 1;
    public static final int MAP = 2;
    public static final int SKILL = 3;
    public static final int PET = 4;
    public static final int ETC = 5;
    public static final int NPC = 6;
    public static final int CONSUME = 7;
    public static final int MOB = 8;
    public static final int PET_DIALOG = 9;
    public static final int EULA = 10;
    public static final int MONSTER_BOOK = 11;

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
            case ETC:
                new EtcLocalization().localize();
                break;
            case NPC:
                new NpcLocalization().localize();
                break;
            case CONSUME:
                new ConsumeLocalization().localize();
                break;
            case MOB:
                new MobLocalization().localize();
                break;
            case PET_DIALOG:
                new PetDialogLocalization().localize();
                break;
            case EULA:
                new EULALocalization().localize();
                break;
            case MONSTER_BOOK:
                new MonsterBoolLocalization().localize();
                break;
            default:
                throw new RuntimeException("Unknown target: " + target);
        }
    }
}
