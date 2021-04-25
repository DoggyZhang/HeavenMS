package stringwz.map;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class MapImgXml extends ArrayList<MapImgXml.Area> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Area> areaList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Map.img";

    public HashMap<String, Map> getMapItemMap() {
        HashMap<String, Map> result = new HashMap<>();
        for (Area area : areaList) {
            for (Map map : area.maps) {
                result.put(map.id, map);
            }
        }
        return result;
    }

    @XmlRootElement(name = "imgdir")
    public static class Area extends ArrayList<Map> {

        @XmlAttribute(name = "name")
        public String name;

        @XmlElement(name = "imgdir")
        public List<Map> maps = new ArrayList<>();
    }

    @XmlRootElement(name = "imgdir")
    public static class Map extends ArrayList<MapItem> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<MapItem> mapItemList = new ArrayList<>();

        public HashMap<String, String> toMap() {
            HashMap<String, String> result = new HashMap<>();
            for (MapItem mapItem : mapItemList) {
                result.put(mapItem.name, mapItem.value);
            }
            return result;
        }

        public void update(HashMap<String, String> newValueMap) {
            for (MapItem mapItem : mapItemList) {
                String newValue = newValueMap.get(mapItem.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                mapItem.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class MapItem {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public MapItem() {
        }

        public MapItem(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
