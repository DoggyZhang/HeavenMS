package stringwz.consume;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class ConsumeParser {

    public static ConsumeImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element consumeImgXmlElement = (Element) doc.getRootElement();
            ConsumeImgXml consumeImgXml = new ConsumeImgXml();

            for (Object consumeElementObject : consumeImgXmlElement.elements()) {
                Element consumeElement = (Element) consumeElementObject;
                ConsumeImgXml.Consume consume = new ConsumeImgXml.Consume();

                Attribute consumeIdAttr = consumeElement.attribute("name");
                consume.id = consumeIdAttr == null ? "" : consumeIdAttr.getValue();

                for (Object consumeDetailObject : consumeElement.elements()) {
                    Element consumeDetailElement = (Element) consumeDetailObject;
                    ConsumeImgXml.ConsumeDetail consumeDetail = new ConsumeImgXml.ConsumeDetail();

                    Attribute consumeItemDetailNameAttr = consumeDetailElement.attribute("name");
                    consumeDetail.name = consumeItemDetailNameAttr == null ? "" : consumeItemDetailNameAttr.getValue();
                    Attribute consumeItemDetailValueAttr = consumeDetailElement.attribute("value");
                    consumeDetail.value = consumeItemDetailValueAttr == null ? "" : consumeItemDetailValueAttr.getValue();

                    consume.consumeDetailList.add(consumeDetail);
                }
                consumeImgXml.consumeList.add(consume);
            }
            return consumeImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
