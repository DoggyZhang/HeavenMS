package stringwz.etc;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class EtcImgXml extends ArrayList<EtcImgXml.Etc> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Etc> etcList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Etc.img";

    public Map<String, EtcItem> getEtcItemMap() {
        Map<String, EtcItem> map = new HashMap<>();
        for (Etc etc : etcList) {
            for (EtcItem etcItem : etc.etcItemList) {
                map.put(etcItem.id, etcItem);
            }
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class Etc extends ArrayList<EtcItem> {

        @XmlAttribute(name = "name")
        public String name = "Etc";

        @XmlElement(name = "imgdir")
        public List<EtcItem> etcItemList = new ArrayList<>();
    }

    @XmlRootElement(name = "imgdir")
    public static class EtcItem extends ArrayList<EtcItemDetail> {
        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<EtcItemDetail> itemDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (EtcItemDetail itemDetail : itemDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (EtcItemDetail itemDetail : itemDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class EtcItemDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public EtcItemDetail() {
        }

        public EtcItemDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
