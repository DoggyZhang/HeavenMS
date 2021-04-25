package stringwz.pet;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class PetLocalization implements ChineseLocalization {

    private static final String TARGET_XML = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\Pet.img.xml";
    private static final String CHINESE_XML = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\Pet.img.xml";

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE_NAME = "Pet.img.xml";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + OUTPUT_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\Cash_not_match_id.txt";

    @Override
    public void localize() {
        PetImgXml replaceByPetImgXml = PetParser.parse(CHINESE_XML);
        // 拿到所有的汉化信息
        HashMap<String, PetImgXml.Pet> replaceByCashItemMap = replaceByPetImgXml.getMapItemMap();

        PetImgXml targetEqpImgXml = PetParser.parse(TARGET_XML);
        HashMap<String, PetImgXml.Pet> targetCashItemMap = targetEqpImgXml.getMapItemMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetCashItemMap.keySet()) {
            PetImgXml.Pet replaceItem = replaceByCashItemMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            PetImgXml.Pet targetItem = targetCashItemMap.get(id);

            HashMap<String, String> replaceMap = replaceItem.toMap();
            HashMap<String, String> targetMap = targetItem.toMap();
            for (String name : targetMap.keySet()) {
                String newValue = replaceMap.get(name);
                if (newValue == null || newValue.isEmpty()) {
                    continue;
                }
                targetMap.put(name, newValue);
            }
            targetItem.update(targetMap);
        }

        try {
            FileUtils.writeLines(new File(NOT_MATCH_FILE), notMatchedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        XmlUtils.generateXML(targetEqpImgXml, OUTPUT_FILE);
        System.out.println("============================================================");
        System.out.println("Localize Success: " + OUTPUT_FILE);
    }
}
