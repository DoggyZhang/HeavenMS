package stringwz.pet;


import javax.xml.bind.annotation.*;
import java.io.*;
import java.util.*;

@XmlAccessorType(XmlAccessType.FIELD)
// XML文件中的根标识
@XmlRootElement(name = "imgdir")
public class PetImgXml extends ArrayList<PetImgXml.Pet> implements Serializable {

    @XmlElement(name = "imgdir")
    public List<Pet> pets = new ArrayList<>();

    @XmlAttribute(name = "name")
    public String name = "Pet.img";

    public HashMap<String, Pet> getMapItemMap() {
        HashMap<String, Pet> result = new HashMap<>();
        for (Pet pet : pets) {
            result.put(pet.id, pet);
        }
        return result;
    }

    @XmlRootElement(name = "imgdir")
    public static class Pet extends ArrayList<PetItem> {

        @XmlAttribute(name = "name")
        public String id;

        @XmlElement(name = "string")
        public List<PetItem> petItems = new ArrayList<>();

        public HashMap<String, String> toMap() {
            HashMap<String, String> result = new HashMap<>();
            for (PetItem skillItem : petItems) {
                result.put(skillItem.name, skillItem.value);
            }
            return result;
        }

        public void update(HashMap<String, String> newValueMap) {
            for (PetItem skillItem : petItems) {
                String newValue = newValueMap.get(skillItem.name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                skillItem.value = newValue;
            }
        }
    }

    @XmlRootElement(name = "string")
    public static class PetItem {
        @XmlAttribute(name = "name")
        public String name;
        @XmlAttribute(name = "value")
        public String value;

        public PetItem() {
        }

        public PetItem(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
