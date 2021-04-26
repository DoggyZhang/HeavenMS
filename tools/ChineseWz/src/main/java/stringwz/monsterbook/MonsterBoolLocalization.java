package stringwz.monsterbook;

import org.apache.commons.io.*;
import stringwz.*;
import utils.*;

import java.io.*;
import java.util.*;

public class MonsterBoolLocalization implements ChineseLocalization {

    private static final String TARGET_FILE_NAME = "MonsterBook.img.xml";

    private static final String REPLACE_TARGET = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\client\\083\\string.wz\\" + TARGET_FILE_NAME;
    private static final String REPLACE_BY = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\server\\079\\string.wz\\" + TARGET_FILE_NAME;

    private static final String OUTPUT_FOLDER = "E:\\MapleStory\\HeavenMS\\tools\\ChineseWz\\wz\\wz_chinese";
    private static final String OUTPUT_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME;
    private static final String NOT_MATCH_FILE = OUTPUT_FOLDER + "\\" + TARGET_FILE_NAME + "_not_match_id.txt";

    @Override
    public void localize() {
        MonsterBookImgXml replaceImgXml = MonsterBookParser.parse(REPLACE_BY);
        // 拿到所有的汉化信息
        Map<String, MonsterBookImgXml.MonsterBook> replaceMap = replaceImgXml.getMonsterBookMap();

        MonsterBookImgXml targetImgXml = MonsterBookParser.parse(REPLACE_TARGET);
        Map<String, MonsterBookImgXml.MonsterBook> targetMap = targetImgXml.getMonsterBookMap();

        List<String> notMatchedList = new ArrayList<>();
        for (String id : targetMap.keySet()) {
            MonsterBookImgXml.MonsterBook replaceItem = replaceMap.get(id);
            if (replaceItem == null) {
                notMatchedList.add(id);
                continue;
            }

            MonsterBookImgXml.MonsterBook targetItem = targetMap.get(id);
            if (replaceItem.stringItem == null || replaceItem.stringItem.value == null || replaceItem.stringItem.value.isEmpty()) {
                continue;
            }
            targetItem.stringItem.value = replaceItem.stringItem.value;
        }

        try {
            FileUtils.writeLines(new File(NOT_MATCH_FILE), notMatchedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MonsterBookWriter.write(targetImgXml, OUTPUT_FILE);
        //XmlUtils.generateXML(targetImgXml, OUTPUT_FILE);
        System.out.println("============================================================");
        System.out.println("Localize Success: " + OUTPUT_FILE);
    }
}
