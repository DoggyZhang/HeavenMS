package stringwz.npc;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class NpcImgXml extends ArrayList<NpcImgXml.Npc> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Npc> npcList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Npc.img";

    public Map<String, Npc> getNpcItemMap() {
        Map<String, Npc> map = new HashMap<>();
        for (Npc npc : npcList) {
            map.put(npc.id, npc);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class Npc extends ArrayList<NpcDetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<NpcDetail> npcDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (NpcDetail itemDetail : npcDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (NpcDetail itemDetail : npcDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class NpcDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public NpcDetail() {
        }

        public NpcDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
