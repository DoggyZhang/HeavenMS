/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package server.events;

import client.MapleCharacter;
import client.SkillFactory;

/**
 * @author kevintjuh93
 */
public class RescueGaga extends MapleEvents {

    private int completed;

    public RescueGaga(int completed) {
        super();
        this.completed = completed;
    }

    public int getCompleted() {
        return completed;
    }

    public void complete() {
        completed++;
    }

    @Override
    public int getInfo() {
        return getCompleted();
    }

    public void giveSkill(MapleCharacter chr) {
        int skillid = 0;
        switch (chr.getJobType()) {
            case 0:
                skillid = 1013;
                break;
            case 1:
            case 2:
                skillid = 10001014;
        }

        long expiration = (System.currentTimeMillis() + 3600 * 24 * 20 * 1000);//20 days
        if (completed < 20) {
            chr.changeSkillLevel(SkillFactory.getSkill(skillid), (byte) 1, 1, expiration);
            chr.changeSkillLevel(SkillFactory.getSkill(skillid + 1), (byte) 1, 1, expiration);
            chr.changeSkillLevel(SkillFactory.getSkill(skillid + 2), (byte) 1, 1, expiration);
        } else {
            chr.changeSkillLevel(SkillFactory.getSkill(skillid), (byte) 2, 2, chr.getSkillExpiration(skillid));
        }
    }

}
