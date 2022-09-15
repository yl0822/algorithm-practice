package org.practice.challenge.ppt.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.openxmlformats.schemas.presentationml.x2006.main.CTSlideIdListEntry;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author feikong
 * @version 2022/8/12
 */
public class SlideShowUtil {

    public static final String OUTPUT_FILE_PATH = "C:\\Users\\Administrator\\Desktop\\test_" + System.currentTimeMillis() + ".pptx";

    public static void exportPpt(XMLSlideShow ppt) {
        try {
            FileOutputStream out = new FileOutputStream(OUTPUT_FILE_PATH);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ppt.write(out);
            out.close();
            ppt.close();
        } catch (IOException e) {
            throw new RuntimeException("export ppt file error");
        }
    }

    public static List<Long> getSlideIdList(XMLSlideShow ppt){
        return ppt.getCTPresentation().getSldIdLst().getSldIdList().stream().map(CTSlideIdListEntry::getId).collect(Collectors.toList());
    }

    public static XSLFPictureData addPic2MediaRepo(XMLSlideShow ppt, String path){
        try {
            return ppt.addPicture(new File(path), getPicTypeByExtension(path));
        } catch (IOException e) {
            throw new RuntimeException("read pic data error");
        }
    }

    private static PictureData.PictureType getPicTypeByExtension(String imgPath){
        String extenion = imgPath.substring(imgPath.lastIndexOf("."));
        for (PictureData.PictureType value : PictureData.PictureType.values()) {
            if (StringUtils.equalsIgnoreCase(value.extension, extenion)){
                return value;
            }
        }
        throw new RuntimeException("not supported pic extension");
    }
}
