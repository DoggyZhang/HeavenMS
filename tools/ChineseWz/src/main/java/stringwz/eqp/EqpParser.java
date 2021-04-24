package stringwz.eqp;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class EqpParser {

    public static EqpImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element eqpImgXmlElement = (Element) doc.getRootElement();
            EqpImgXml eqpImgXml = new EqpImgXml();

            for (Object eqpElementObject : eqpImgXmlElement.elements()) {
                Element eqpElement = (Element) eqpElementObject;
                EqpImgXml.Eqp eqp = new EqpImgXml.Eqp();

                for (Object eqpTypeObject : eqpElement.elements()) {
                    Element eqpTypeElement = (Element) eqpTypeObject;
                    EqpImgXml.EqpType eqpType = new EqpImgXml.EqpType();
                    Attribute eqpTypeAttr = eqpTypeElement.attribute("name");
                    eqpType.eqpType = eqpTypeAttr == null ? "" : eqpTypeAttr.getValue();

                    for (Object eqpItemObject : eqpTypeElement.elements()) {
                        Element eqpItemElement = (Element) eqpItemObject;
                        Attribute eqpItemIdAttr = eqpItemElement.attribute("name");
                        EqpImgXml.EqpItem eqpItem = new EqpImgXml.EqpItem();
                        eqpItem.id = eqpItemIdAttr == null ? "" : eqpItemIdAttr.getValue();

                        for (Object eqpItemDetailObject : eqpItemElement.elements()) {
                            Element eqpItemDetailElement = (Element) eqpItemDetailObject;
                            EqpImgXml.EqpItemDetail eqpItemDetail = new EqpImgXml.EqpItemDetail();

                            Attribute eqpItemDetailNameAttr = eqpItemDetailElement.attribute("name");
                            eqpItemDetail.name = eqpItemDetailNameAttr == null ? "" : eqpItemDetailNameAttr.getValue();
                            Attribute eqpItemDetailValueAttr = eqpItemDetailElement.attribute("value");
                            eqpItemDetail.value = eqpItemDetailValueAttr == null ? "" : eqpItemDetailValueAttr.getValue();

                            eqpItem.itemDetails.add(eqpItemDetail);
                        }

                        eqpType.eqpItemList.add(eqpItem);
                    }

                    eqp.eqpTypes.add(eqpType);
                }

                eqpImgXml.eqpList.add(eqp);
            }

            return eqpImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
