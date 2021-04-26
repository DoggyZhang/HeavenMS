package stringwz.npc;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class NpcParser {

    public static NpcImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element npcImgXmlElement = (Element) doc.getRootElement();
            NpcImgXml npcImgXml = new NpcImgXml();

            for (Object npcElementObject : npcImgXmlElement.elements()) {
                Element npcElement = (Element) npcElementObject;
                NpcImgXml.Npc npc = new NpcImgXml.Npc();

                Attribute npcIdAttr = npcElement.attribute("name");
                npc.id = npcIdAttr == null ? "" : npcIdAttr.getValue();

                for (Object npcDetailObject : npcElement.elements()) {
                    Element npcDetailElement = (Element) npcDetailObject;
                    NpcImgXml.NpcDetail npcDetail = new NpcImgXml.NpcDetail();

                    Attribute npcItemDetailNameAttr = npcDetailElement.attribute("name");
                    npcDetail.name = npcItemDetailNameAttr == null ? "" : npcItemDetailNameAttr.getValue();
                    Attribute npcItemDetailValueAttr = npcDetailElement.attribute("value");
                    npcDetail.value = npcItemDetailValueAttr == null ? "" : npcItemDetailValueAttr.getValue();

                    npc.npcDetailList.add(npcDetail);
                }
                npcImgXml.npcList.add(npc);
            }
            return npcImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
