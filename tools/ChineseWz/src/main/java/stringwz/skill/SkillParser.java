package stringwz.skill;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class SkillParser {

    public static SkillImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element skillImgXmlElement = (Element) doc.getRootElement();
            SkillImgXml skillImgXml = new SkillImgXml();

            for (Object skillElementObject : skillImgXmlElement.elements()) {
                Element skillElement = (Element) skillElementObject;
                SkillImgXml.Skill skill = new SkillImgXml.Skill();

                Attribute skillIdAttr = skillElement.attribute("name");
                skill.id = skillIdAttr == null ? "" : skillIdAttr.getValue();

                for (Object skillItemObject : skillElement.elements()) {
                    Element skillItemElement = (Element) skillItemObject;
                    SkillImgXml.SkillItem skillItem = new SkillImgXml.SkillItem();

                    Attribute skillItemNameAttr = skillItemElement.attribute("name");
                    skillItem.name = skillItemNameAttr == null ? "" : skillItemNameAttr.getValue();
                    Attribute skillItemValueAttr = skillItemElement.attribute("value");
                    skillItem.value = skillItemValueAttr == null ? "" : skillItemValueAttr.getValue();

                    skill.skillItems.add(skillItem);
                }
                skillImgXml.skills.add(skill);
            }
            return skillImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
