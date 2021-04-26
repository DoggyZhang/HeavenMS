package stringwz.mob;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class MobParser {

    public static MobImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element mobImgXmlElement = (Element) doc.getRootElement();
            MobImgXml mobImgXml = new MobImgXml();

            for (Object mobElementObject : mobImgXmlElement.elements()) {
                Element mobElement = (Element) mobElementObject;
                MobImgXml.Mob mob = new MobImgXml.Mob();

                Attribute mobIdAttr = mobElement.attribute("name");
                mob.id = mobIdAttr == null ? "" : mobIdAttr.getValue();

                for (Object mobDetailObject : mobElement.elements()) {
                    Element mobDetailElement = (Element) mobDetailObject;
                    MobImgXml.MobDetail mobDetail = new MobImgXml.MobDetail();

                    Attribute mobItemDetailNameAttr = mobDetailElement.attribute("name");
                    mobDetail.name = mobItemDetailNameAttr == null ? "" : mobItemDetailNameAttr.getValue();
                    Attribute mobItemDetailValueAttr = mobDetailElement.attribute("value");
                    mobDetail.value = mobItemDetailValueAttr == null ? "" : mobItemDetailValueAttr.getValue();

                    mob.mobDetailList.add(mobDetail);
                }
                mobImgXml.mobList.add(mob);
            }
            return mobImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
