package stringwz.petdialog;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class PetDialogParser {

    public static PetDialogImgXml parse(String xmlPath) {
        File inputXml = new File(xmlPath);
        SAXReader sr = new SAXReader();
        try {
            Document doc = sr.read(inputXml);
            Element petDialogImgXmlElement = (Element) doc.getRootElement();
            PetDialogImgXml petDialogImgXml = new PetDialogImgXml();

            for (Object petDialogElementObject : petDialogImgXmlElement.elements()) {
                Element petDialogElement = (Element) petDialogElementObject;
                PetDialogImgXml.PetDialog petDialog = new PetDialogImgXml.PetDialog();

                Attribute petDialogIdAttr = petDialogElement.attribute("name");
                petDialog.id = petDialogIdAttr == null ? "" : petDialogIdAttr.getValue();

                for (Object petDialogDetailObject : petDialogElement.elements()) {
                    Element petDialogDetailElement = (Element) petDialogDetailObject;
                    PetDialogImgXml.PetDialogDetail petDialogDetail = new PetDialogImgXml.PetDialogDetail();

                    Attribute petDialogItemDetailNameAttr = petDialogDetailElement.attribute("name");
                    petDialogDetail.name = petDialogItemDetailNameAttr == null ? "" : petDialogItemDetailNameAttr.getValue();
                    Attribute petDialogItemDetailValueAttr = petDialogDetailElement.attribute("value");
                    petDialogDetail.value = petDialogItemDetailValueAttr == null ? "" : petDialogItemDetailValueAttr.getValue();

                    petDialog.petDialogDetailList.add(petDialogDetail);
                }
                petDialogImgXml.petDialogList.add(petDialog);
            }
            return petDialogImgXml;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
