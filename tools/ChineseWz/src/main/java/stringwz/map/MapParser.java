package stringwz.map;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class MapParser {

    public static MapImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element mapImgXmlElement = (Element) doc.getRootElement();
            MapImgXml mapImgXml = new MapImgXml();

            for (Object areaElementObject : mapImgXmlElement.elements()) {
                Element areaElement = (Element) areaElementObject;
                MapImgXml.Area area = new MapImgXml.Area();

                Attribute areaAttr = areaElement.attribute("name");
                area.name = areaAttr == null ? "" : areaAttr.getValue();

                for (Object mapObject : areaElement.elements()) {
                    Element mapElement = (Element) mapObject;
                    MapImgXml.Map map = new MapImgXml.Map();

                    Attribute mapIdAttr = mapElement.attribute("name");
                    map.id = mapIdAttr.getValue() == null ? "" : mapIdAttr.getValue();

                    for (Object mapItemObject : mapElement.elements()) {
                        Element mapItemElement = (Element) mapItemObject;
                        MapImgXml.MapItem mapItem = new MapImgXml.MapItem();

                        Attribute mapItemNameAttr = mapItemElement.attribute("name");
                        mapItem.name = mapItemNameAttr == null ? "" : mapItemNameAttr.getValue();
                        Attribute mapItemValueAttr = mapItemElement.attribute("value");
                        mapItem.value = mapItemValueAttr == null ? "" : mapItemValueAttr.getValue();

                        map.mapItemList.add(mapItem);
                    }
                    area.maps.add(map);
                }
                mapImgXml.areaList.add(area);
            }
            return mapImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
