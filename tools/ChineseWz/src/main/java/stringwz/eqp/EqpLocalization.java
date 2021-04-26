package stringwz.eqp;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class EqpLocalization implements ChineseLocalization {

    private static final String TARGET_FILE_NAME = "Eqp.img.xml";

    private static final String REPLACE_TARGET = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\" + TARGET_FILE_NAME;
    private static final String REPLACE_BY = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\" + TARGET_FILE_NAME;

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME + "_not_match_id.txt";

    @Override
    public void localize() {
        EqpImgXml replaceByEqpImgXml = EqpParser.parse(REPLACE_BY);
        // 拿到所有的汉化信息
        Map<String, EqpImgXml.EqpItem> replaceByEqpItemMap = replaceByEqpImgXml.getEqpItemMap();

        EqpImgXml targetEqpImgXml = EqpParser.parse(REPLACE_TARGET);
        Map<String, EqpImgXml.EqpItem> targetEqpItemMap = targetEqpImgXml.getEqpItemMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetEqpItemMap.keySet()) {
            EqpImgXml.EqpItem replaceItem = replaceByEqpItemMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            EqpImgXml.EqpItem targetItem = targetEqpItemMap.get(id);

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
