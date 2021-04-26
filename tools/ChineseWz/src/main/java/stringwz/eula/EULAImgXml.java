package stringwz.eula;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class EULAImgXml extends ArrayList<EULAImgXml.EULA> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<EULA> eulaList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "EULA.img";

    public Map<String, EULA> getEULAItemMap() {
        Map<String, EULA> map = new HashMap<>();
        for (EULA npc : eulaList) {
            map.put(npc.id, npc);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class EULA extends ArrayList<EULADetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<EULADetail> eulaDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (EULADetail itemDetail : eulaDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (EULADetail itemDetail : eulaDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class EULADetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public EULADetail() {
        }

        public EULADetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
