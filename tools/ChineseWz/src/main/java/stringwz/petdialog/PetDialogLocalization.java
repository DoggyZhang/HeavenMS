package stringwz.petdialog;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class PetDialogLocalization implements ChineseLocalization {

    private static final String TARGET_FILE_NAME = "PetDialog.img.xml";

    private static final String REPLACE_TARGET = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\" + TARGET_FILE_NAME;
    private static final String REPLACE_BY = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\" + TARGET_FILE_NAME;

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME + "_not_match_id.txt";

    @Override
    public void localize() {
        PetDialogImgXml replaceByCashImgXml = PetDialogParser.parse(REPLACE_BY);
        // 拿到所有的汉化信息
        Map<String, PetDialogImgXml.PetDialog> replaceByCashItemMap = replaceByCashImgXml.getPetDialogItemMap();

        PetDialogImgXml targetEqpImgXml = PetDialogParser.parse(REPLACE_TARGET);
        Map<String, PetDialogImgXml.PetDialog> targetCashItemMap = targetEqpImgXml.getPetDialogItemMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetCashItemMap.keySet()) {
            PetDialogImgXml.PetDialog replaceItem = replaceByCashItemMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            PetDialogImgXml.PetDialog targetItem = targetCashItemMap.get(id);

            Map<String, String> replaceMap = replaceItem.toMap();
            Map<String, String> targetMap = targetItem.toMap();
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
