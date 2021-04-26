package stringwz.eula;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class EULAParser {

    public static EULAImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element eulaImgXmlElement = (Element) doc.getRootElement();
            EULAImgXml eulaImgXml = new EULAImgXml();

            for (Object eulaElementObject : eulaImgXmlElement.elements()) {
                Element eulaElement = (Element) eulaElementObject;
                EULAImgXml.EULA eula = new EULAImgXml.EULA();

                Attribute eulaIdAttr = eulaElement.attribute("name");
                eula.id = eulaIdAttr == null ? "" : eulaIdAttr.getValue();

                for (Object eulaDetailObject : eulaElement.elements()) {
                    Element eulaDetailElement = (Element) eulaDetailObject;
                    EULAImgXml.EULADetail eulaDetail = new EULAImgXml.EULADetail();

                    Attribute eulaItemDetailNameAttr = eulaDetailElement.attribute("name");
                    eulaDetail.name = eulaItemDetailNameAttr == null ? "" : eulaItemDetailNameAttr.getValue();
                    Attribute eulaItemDetailValueAttr = eulaDetailElement.attribute("value");
                    eulaDetail.value = eulaItemDetailValueAttr == null ? "" : eulaItemDetailValueAttr.getValue();

                    eula.eulaDetailList.add(eulaDetail);
                }
                eulaImgXml.eulaList.add(eula);
            }
            return eulaImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
