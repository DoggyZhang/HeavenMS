package stringwz.petdialog;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class PetDialogImgXml extends ArrayList<PetDialogImgXml.PetDialog> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<PetDialog> petDialogList = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "PetDialog.img";

    public Map<String, PetDialog> getPetDialogItemMap() {
        Map<String, PetDialog> map = new HashMap<>();
        for (PetDialog npc : petDialogList) {
            map.put(npc.id, npc);
        }
        return map;
    }

    @XmlRootElement(name = "imgdir")
    public static class PetDialog extends ArrayList<PetDialogDetail> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<PetDialogDetail> petDialogDetailList = new ArrayList<>();

        public Map<String, String> toMap() {
            Map<String, String> map = new HashMap<>();
            for (PetDialogDetail itemDetail : petDialogDetailList) {
                map.put(itemDetail.name, itemDetail.value);
            }
            return map;
        }

        public void update(Map<String, String> map) {
            for (PetDialogDetail itemDetail : petDialogDetailList) {
                String newValue = map.get(itemDetail.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                itemDetail.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class PetDialogDetail {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public PetDialogDetail() {
        }

        public PetDialogDetail(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
