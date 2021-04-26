package stringwz.consume;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class ConsumeImgXml extends ArrayList<ConsumeImgXml.Consume> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Consume> consumeList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Consume.img";

    public Map<String, Consume> getConsumeItemMap() {
        Map<String, Consume> map = new HashMap<>();
        for (Consume npc : consumeList) {
            map.put(npc.id, npc);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class Consume extends ArrayList<ConsumeDetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<ConsumeDetail> consumeDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (ConsumeDetail itemDetail : consumeDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (ConsumeDetail itemDetail : consumeDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class ConsumeDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public ConsumeDetail() {
        }

        public ConsumeDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
