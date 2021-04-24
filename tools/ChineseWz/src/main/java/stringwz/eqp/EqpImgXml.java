package stringwz.eqp;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class EqpImgXml extends ArrayList<EqpImgXml.Eqp> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Eqp> eqpList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Eqp.img";

    public Map<String, EqpItem> getEqpItemMap() {
        Map<String, EqpItem> map = new HashMap<>();
        for (Eqp eqp : eqpList) {
            for (EqpType eqpType : eqp.eqpTypes) {
                for (EqpItem eqpItem : eqpType.eqpItemList) {
                    map.put(eqpItem.id, eqpItem);
                }
            }
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class Eqp extends ArrayList<EqpType> {

        @XmlAttribute(name = "name")
        public String name = "Eqp";

        @XmlElement(name = "imgdir")
        public List<EqpType> eqpTypes = new ArrayList<>();
    }

    @XmlRootElement(name = "imgdir")
    public static class EqpType extends ArrayList<EqpItem> {

        @XmlAttribute(name = "name")
        public String eqpType;

        @XmlElement(name = "imgdir")
        public List<EqpItem> eqpItemList = new ArrayList<>();
    }

    @XmlRootElement(name = "imgdir")
    public static class EqpItem extends ArrayList<EqpItemDetail> {
        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<EqpItemDetail> itemDetails = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (EqpItemDetail itemDetail : itemDetails) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (EqpItemDetail itemDetail : itemDetails) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class EqpItemDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public EqpItemDetail() {
        }

        public EqpItemDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
