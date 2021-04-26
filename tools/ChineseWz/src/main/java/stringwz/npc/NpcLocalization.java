package stringwz.npc;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class NpcLocalization implements ChineseLocalization {

    private static final String TARGET_FILE_NAME = "Npc.img.xml";

    private static final String REPLACE_TARGET = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\" + TARGET_FILE_NAME;
    private static final String REPLACE_BY = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\" + TARGET_FILE_NAME;

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME + "_not_match_id.txt";

    @Override
    public void localize() {
        NpcImgXml replaceByCashImgXml = NpcParser.parse(REPLACE_BY);
        // 拿到所有的汉化信息
        Map<String, NpcImgXml.Npc> replaceByCashItemMap = replaceByCashImgXml.getNpcItemMap();

        NpcImgXml targetEqpImgXml = NpcParser.parse(REPLACE_TARGET);
        Map<String, NpcImgXml.Npc> targetCashItemMap = targetEqpImgXml.getNpcItemMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetCashItemMap.keySet()) {
            NpcImgXml.Npc replaceItem = replaceByCashItemMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            NpcImgXml.Npc targetItem = targetCashItemMap.get(id);

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
