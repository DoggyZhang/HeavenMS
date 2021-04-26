package stringwz.mob;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class MobImgXml extends ArrayList<MobImgXml.Mob> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Mob> mobList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Mob.img";

    public Map<String, Mob> getMobItemMap() {
        Map<String, Mob> map = new HashMap<>();
        for (Mob npc : mobList) {
            map.put(npc.id, npc);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class Mob extends ArrayList<MobDetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<MobDetail> mobDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (MobDetail itemDetail : mobDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (MobDetail itemDetail : mobDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class MobDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public MobDetail() {
        }

        public MobDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
