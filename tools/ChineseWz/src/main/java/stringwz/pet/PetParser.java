package stringwz.pet;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class PetParser {

    public static PetImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element petImgXmlElement = (Element) doc.getRootElement();
            PetImgXml petImgXml = new PetImgXml();

            for (Object petElementObject : petImgXmlElement.elements()) {
                Element petElement = (Element) petElementObject;
                PetImgXml.Pet pet = new PetImgXml.Pet();

                Attribute skillIdAttr = petElement.attribute("name");
                pet.id = skillIdAttr == null ? "" : skillIdAttr.getValue();

                for (Object petItemObject : petElement.elements()) {
                    Element petItemElement = (Element) petItemObject;
                    PetImgXml.PetItem skillItem = new PetImgXml.PetItem();

                    Attribute petItemNameAttr = petItemElement.attribute("name");
                    skillItem.name = petItemNameAttr == null ? "" : petItemNameAttr.getValue();
                    Attribute petItemValueAttr = petItemElement.attribute("value");
                    skillItem.value = petItemValueAttr == null ? "" : petItemValueAttr.getValue();

                    pet.petItems.add(skillItem);
                }
                petImgXml.pets.add(pet);
            }
            return petImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
