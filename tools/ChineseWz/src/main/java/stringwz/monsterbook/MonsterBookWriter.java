package stringwz.monsterbook;

import org.dom4j.*;
import org.dom4j.io.*;

import java.io.*;

public class MonsterBookWriter {

    public static void write(MonsterBookImgXml monsterBookImgXml, String outputFile) {
        Document dom = DocumentHelper.createDocument();//添加节点用addElement，添加节点属性用addAttribute,未节点赋值用setText
        Element rootElement = dom.addElement("imgdir");
        rootElement.addAttribute("name", monsterBookImgXml.name);

        for (MonsterBookImgXml.MonsterBook monsterBook : monsterBookImgXml.monsterBookList) {
            Element monsterBookElement = rootElement.addElement("imgdir");
            monsterBookElement.addAttribute("name", monsterBook.id);

            Element mapElement = monsterBookElement.addElement("imgdir");
            mapElement.addAttribute("name", monsterBook.mapItem.name);
            for (MonsterBookImgXml.MonsterBookItemDetail intDetail : monsterBook.mapItem.itemDetails) {
                Element intElement = mapElement.addElement("int");
                intElement.addAttribute("name", intDetail.name);
                intElement.addAttribute("value", intDetail.value);
            }

            Element rewardElement = monsterBookElement.addElement("imgdir");
            rewardElement.addAttribute("name", monsterBook.rewardItem.name);
            for (MonsterBookImgXml.MonsterBookItemDetail intDetail : monsterBook.rewardItem.itemDetails) {
                Element intElement = rewardElement.addElement("int");
                intElement.addAttribute("name", intDetail.name);
                intElement.addAttribute("value", intDetail.value);
            }

            Element stringElement = monsterBookElement.addElement("string");
            stringElement.addAttribute("name", monsterBook.stringItem.name);
            stringElement.addAttribute("value", monsterBook.stringItem.value);
        }
        writeXmlFile(dom, outputFile);
    }

    private static void writeXmlFile(Document dom, String outputFile) {
        //设置生成xml格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        // 设置编码格式
        format.setEncoding("UTF-8");
        File file = new File(outputFile);
        XMLWriter writer = null;
        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.setEscapeText(false); //关闭字符串中xml特殊字符转义
        try {
            writer.write(dom);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
