package stringwz.cash;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class CashParser {

    public static CashImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element eqpImgXmlElement = (Element) doc.getRootElement();
            CashImgXml eqpImgXml = new CashImgXml();

            for (Object cashElementObject : eqpImgXmlElement.elements()) {
                Element cashElement = (Element) cashElementObject;
                CashImgXml.Cash cash = new CashImgXml.Cash();

                Attribute cashIdAttr = cashElement.attribute("name");
                cash.id = cashIdAttr == null ? "" : cashIdAttr.getValue();

                for (Object cashDetailObject : cashElement.elements()) {
                    Element cashDetailElement = (Element) cashDetailObject;
                    CashImgXml.CashDetail cashDetail = new CashImgXml.CashDetail();

                    Attribute eqpItemDetailNameAttr = cashDetailElement.attribute("name");
                    cashDetail.name = eqpItemDetailNameAttr == null ? "" : eqpItemDetailNameAttr.getValue();
                    Attribute eqpItemDetailValueAttr = cashDetailElement.attribute("value");
                    cashDetail.value = eqpItemDetailValueAttr == null ? "" : eqpItemDetailValueAttr.getValue();

                    cash.itemDetails.add(cashDetail);
                }
                eqpImgXml.cashList.add(cash);
            }
            return eqpImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
