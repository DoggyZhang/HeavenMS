package stringwz.skill;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class SkillImgXml extends ArrayList<SkillImgXml.Skill> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Skill> skills = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Skill.img";

    public HashMap<String, Skill> getMapItemMap() {
        HashMap<String, Skill> result = new HashMap<>();
        for (Skill skill : skills) {
            result.put(skill.id, skill);
        }
        return result;
    }

    @XmlRootElement(name = "imgdir")
    public static class Skill extends ArrayList<SkillItem> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<SkillItem> skillItems = new ArrayList<>();

        public HashMap<String, String> toMap() {
            HashMap<String, String> result = new HashMap<>();
            for (SkillItem skillItem : skillItems) {
                result.put(skillItem.name, skillItem.value);
            }
            return result;
        }

        public void update(HashMap<String, String> newValueMap) {
            for (SkillItem skillItem : skillItems) {
                String newValue = newValueMap.get(skillItem.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                skillItem.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class SkillItem {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public SkillItem() {
        }

        public SkillItem(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
