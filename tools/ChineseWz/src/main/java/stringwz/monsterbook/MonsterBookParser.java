package stringwz.monsterbook;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class MonsterBookParser {

    public static MonsterBookImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element monsterBookImgXmlElement = (Element) doc.getRootElement();
            MonsterBookImgXml monsterBookImgXml = new MonsterBookImgXml();

            for (Object monsterBookElementObject : monsterBookImgXmlElement.elements()) {
                Element monsterBookElement = (Element) monsterBookElementObject;
                MonsterBookImgXml.MonsterBook monsterBook = new MonsterBookImgXml.MonsterBook();

                Attribute monsterBookIdAttr = monsterBookElement.attribute("name");
                monsterBook.id = monsterBookIdAttr.getValue() == null ? "" : monsterBookIdAttr.getValue();

                for (Object monsterBookItemObject : monsterBookElement.elements()) {
                    Element monsterBookItemElement = (Element) monsterBookItemObject;

                    Attribute monsterBookItemNameAttr = monsterBookItemElement.attribute("name");
                    String name = monsterBookItemNameAttr.getValue();

                    if (name.equals("map")) {
                        MonsterBookImgXml.MonsterBookImgDirItem mapItem = new MonsterBookImgXml.MonsterBookImgDirItem();
                        mapItem.name = name;
                        monsterBook.mapItem = mapItem;

                        for (Object itemDetailObject : monsterBookItemElement.elements()) {
                            Element itemDetailElement = (Element) itemDetailObject;
                            MonsterBookImgXml.MonsterBookItemDetail itemDetail = new MonsterBookImgXml.MonsterBookItemDetail();

                            Attribute itemDetailNameAttr = itemDetailElement.attribute("name");
                            itemDetail.name = itemDetailNameAttr == null ? "" : itemDetailNameAttr.getValue();
                            Attribute itemDetailValueAttr = itemDetailElement.attribute("value");
                            itemDetail.value = itemDetailValueAttr == null ? "" : itemDetailValueAttr.getValue();

                            monsterBook.mapItem.itemDetails.add(itemDetail);
                        }
                    } else if (name.equals("reward")) {
                        MonsterBookImgXml.MonsterBookImgDirItem rewardItem = new MonsterBookImgXml.MonsterBookImgDirItem();
                        rewardItem.name = name;
                        monsterBook.rewardItem = rewardItem;

                        for (Object itemDetailObject : monsterBookItemElement.elements()) {
                            Element itemDetailElement = (Element) itemDetailObject;
                            MonsterBookImgXml.MonsterBookItemDetail itemDetail = new MonsterBookImgXml.MonsterBookItemDetail();

                            Attribute itemDetailNameAttr = itemDetailElement.attribute("name");
                            itemDetail.name = itemDetailNameAttr == null ? "" : itemDetailNameAttr.getValue();
                            Attribute itemDetailValueAttr = itemDetailElement.attribute("value");
                            itemDetail.value = itemDetailValueAttr == null ? "" : itemDetailValueAttr.getValue();

                            monsterBook.rewardItem.itemDetails.add(itemDetail);
                        }
                    } else {
                        // string
                        MonsterBookImgXml.MonsterBookStringItem monsterBookItem = new MonsterBookImgXml.MonsterBookStringItem();
                        monsterBookItem.name = name;
                        Attribute monsterBookItemValueAttr = monsterBookItemElement.attribute("value");
                        monsterBookItem.value = monsterBookItemValueAttr == null ? "" : monsterBookItemValueAttr.getValue();

                        monsterBook.stringItem = monsterBookItem;
                    }
                }
                monsterBookImgXml.monsterBookList.add(monsterBook);
            }
            return monsterBookImgXml;
        } catch (
                DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
