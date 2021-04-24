package stringwz.cash;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "dir")
public class CashImgXml extends ArrayList<CashImgXml.Cash> implements Serializable {

    @XmlElement(name = "dir")
    public List<Cash> cashList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Cash.img";

    public Map<String, Cash> getEqpItemMap() {
        Map<String, Cash> map = new HashMap<>();
        for (Cash cash : cashList) {
            map.put(cash.id, cash);
        }
        return map;
    }

    @XmlRootElement(name = "dir")
    public static class Cash extends ArrayList<CashDetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<CashDetail> itemDetails = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (CashDetail itemDetail : itemDetails) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (CashDetail itemDetail : itemDetails) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class CashDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public CashDetail() {
        }

        public CashDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
