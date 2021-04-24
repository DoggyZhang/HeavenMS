package utils;

import javax.xml.bind.*;
import java.io.*;

public class XmlUtils {
    public static <T> void generateXML(T object, String path) {
        try {
            JAXBContext jc = null;
            File file = new File(path);
            //根据Person类生成上下文对象
            jc = JAXBContext.newInstance(object.getClass());
            //从上下文中获取Marshaller对象，用作将bean编组(转换)为xml
            Marshaller ma = jc.createMarshaller();
            //以下是为生成xml做的一些配置
            //格式化输出，即按标签自动换行，否则就是一行输出
            ma.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            //设置编码（默认编码就是utf-8）
            ma.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            //是否省略xml头信息，默认不省略（false）
            ma.setProperty(Marshaller.JAXB_FRAGMENT, false);

            //编组
            ma.marshal(object, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static <T> T generateBean(String xmlPath, Class<T> clazz) {
        File file = new File(xmlPath);
        JAXBContext jc = null;
        try {
            jc = JAXBContext.newInstance(clazz);
            Unmarshaller uma = jc.createUnmarshaller();
            Object unmarshal = uma.unmarshal(file);
            System.out.println(unmarshal.getClass().toString());
            return (T) unmarshal;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
}
