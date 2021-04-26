package stringwz.etc;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class EtcParser {

    public static EtcImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element etcImgXmlElement = (Element) doc.getRootElement();
            EtcImgXml etcImgXml = new EtcImgXml();

            for (Object etcElementObject : etcImgXmlElement.elements()) {
                Element etcElement = (Element) etcElementObject;
                EtcImgXml.Etc etc = new EtcImgXml.Etc();

                for (Object etcItemObject : etcElement.elements()) {
                    Element etcItemElement = (Element) etcItemObject;
                    EtcImgXml.EtcItem etaItem = new EtcImgXml.EtcItem();

                    Attribute eqpTypeAttr = etcItemElement.attribute("name");
                    etaItem.id = eqpTypeAttr == null ? "" : eqpTypeAttr.getValue();

                    for (Object etcItemDetailObject : etcItemElement.elements()) {
                        Element etcItemDetailElement = (Element) etcItemDetailObject;
                        EtcImgXml.EtcItemDetail etcItemDetail = new EtcImgXml.EtcItemDetail();

                        Attribute etcItemDetailNameAttr = etcItemDetailElement.attribute("name");
                        etcItemDetail.name = etcItemDetailNameAttr == null ? "" : etcItemDetailNameAttr.getValue();
                        Attribute etcItemDetailValueAttr = etcItemDetailElement.attribute("value");
                        etcItemDetail.value = etcItemDetailValueAttr == null ? "" : etcItemDetailValueAttr.getValue();

                        etaItem.itemDetailList.add(etcItemDetail);
                    }
                    etc.etcItemList.add(etaItem);
                }
                etcImgXml.etcList.add(etc);
            }
            return etcImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
