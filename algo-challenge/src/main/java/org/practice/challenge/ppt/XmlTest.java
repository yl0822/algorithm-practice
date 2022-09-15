package org.practice.challenge.ppt;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.xsd2inst.SampleXmlUtil;

import javax.xml.namespace.QName;

/**
 * @author feikong
 * @version 2022/8/11
 */
public class XmlTest {

    public static void main(String[] args) {
        XmlOptions options = new XmlOptions();
        options.put(XmlOptions.SAVE_PRETTY_PRINT);
        options.put(XmlOptions.SAVE_PRETTY_PRINT_INDENT, 3);
        options.put(XmlOptions.SAVE_AGGRESSIVE_NAMESPACES);
        options.setSaveOuter();
//        System.out.println(createSection().xmlText(options));
        System.out.println(createSection().toString());
    }

    public static XmlObject createSection(){
        XmlObject xml = XmlObject.Factory.newInstance();
        XmlCursor cursor = xml.newCursor();
        cursor.toNextToken();
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sectionLst", "p14"));

        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "section"));
        cursor.insertAttributeWithValue(new QName("name"), "啊啊所大");
        cursor.insertAttributeWithValue(new QName("id"), "{09cbc963-c158-4032-9021-3bdc7e4fcc78}");
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sldIdLst"));
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sldId"));
        cursor.insertAttributeWithValue(new QName("id"), "256");
        cursor.toParent();
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sldId"));
        cursor.insertAttributeWithValue(new QName("id"), "257");

        cursor.toParent();
        cursor.toParent();
        cursor.toParent();
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "section"));
        cursor.insertAttributeWithValue(new QName("name"), "房管局");
        cursor.insertAttributeWithValue(new QName("id"), "{5ce6b277-0ff1-4794-b9ff-345ee99e2929}");
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sldIdLst"));
        cursor.beginElement(new QName("http://schemas.microsoft.com/office/powerpoint/2010/main", "sldId"));
        cursor.insertAttributeWithValue(new QName("id"), "258");

        cursor.dispose();


        return xml;
    }

    public static String createSampleForElement(SchemaType type, String name) {
        XmlObject xml = XmlObject.Factory.newInstance();

        XmlCursor c = xml.newCursor();
        c.toNextToken();
        c.beginElement(name);

        SampleXmlUtil.createSampleForType(type);

        c.dispose();

        XmlOptions options = new XmlOptions();
        options.put(XmlOptions.SAVE_PRETTY_PRINT);
        options.put(XmlOptions.SAVE_PRETTY_PRINT_INDENT, 3);
        options.put(XmlOptions.SAVE_AGGRESSIVE_NAMESPACES);
        options.setSaveOuter();
        String result = xml.xmlText(options);

        return result;
    }
}