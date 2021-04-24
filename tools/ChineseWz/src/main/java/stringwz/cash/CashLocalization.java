package stringwz.cash;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class CashLocalization implements ChineseLocalization {

    private static final String TARGET_XML = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\String.Cash.img.xml";
    private static final String CHINESE_XML = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\Cash.img.xml";

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE_NAME = "Cash.img.xml";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + OUTPUT_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\Cash_not_match_id.txt";

    @Override
    public void localize() {
        CashImgXml replaceByCashImgXml = CashParser.parse(CHINESE_XML);
        // 拿到所有的汉化信息
        Map<String, CashImgXml.Cash> replaceByCashItemMap = replaceByCashImgXml.getEqpItemMap();

        CashImgXml targetEqpImgXml = CashParser.parse(TARGET_XML);
        Map<String, CashImgXml.Cash> targetCashItemMap = targetEqpImgXml.getEqpItemMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetCashItemMap.keySet()) {
            CashImgXml.Cash replaceItem = replaceByCashItemMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            CashImgXml.Cash targetItem = targetCashItemMap.get(id);

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
