package stringwz.monsterbook;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class MonsterBookImgXml extends ArrayList<MonsterBookImgXml.MonsterBook> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<MonsterBook> monsterBookList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "MonsterBook.img";

    public Map<String, MonsterBook> getMonsterBookMap() {
        Map<String, MonsterBook> map = new HashMap<>();
        for (MonsterBook monsterBook : monsterBookList) {
            map.put(monsterBook.id, monsterBook);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class MonsterBook {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElementWrapper(name = "imgdir")
        @XmlElement(name = "imgdir")
        public MonsterBookImgDirItem mapItem = new MonsterBookImgDirItem();

        @XmlElementWrapper(name = "imgdir")
        @XmlElement(name = "imgdir")
        public MonsterBookImgDirItem rewardItem = new MonsterBookImgDirItem();

        @XmlElement(name = "string")
        public MonsterBookStringItem stringItem = new MonsterBookStringItem();
    }

    @XmlRootElement(name = "imgdir")
    public static class MonsterBookImgDirItem extends ArrayList<MonsterBookItemDetail> {
        @XmlAttribute(name = "name")
        public String name;

        @XmlElement(name = "int")
        public List<MonsterBookItemDetail> itemDetails = new ArrayList<>();
    }

    @XmlRootElement(name = "int")
    public static class MonsterBookItemDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public MonsterBookItemDetail() {
        }

        public MonsterBookItemDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }

    @XmlRootElement(name = "string")
    public static class MonsterBookStringItem {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public MonsterBookStringItem() {
        }

        public MonsterBookStringItem(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
