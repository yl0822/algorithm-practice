package org.practice.challenge.ppt.utils;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.PackagePart;
import org.apache.poi.openxml4j.opc.PackageRelationship;
import org.apache.poi.openxml4j.opc.TargetMode;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFRelation;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.practice.challenge.ppt.utils.SlideShowUtil.addPic2MediaRepo;

/**
 * @author feikong
 * @version 2022/8/16
 */
public class SlideUtil {

    public static List<String> getRelationIdList(XSLFSlide slide) {
        return slide.getRelationParts().stream().map(POIXMLDocumentPart.RelationPart::getRelationship).map(PackageRelationship::getId).collect(Collectors.toList());
    }

    public static String createImageRelation(XSLFSlide slide, String imgPath){
        XSLFPictureData pictureData = addPic2MediaRepo((XMLSlideShow)slide.getParent(), imgPath);
        POIXMLDocumentPart.RelationPart relationPart = slide.addRelation(null, XSLFRelation.IMAGES, pictureData);
        return relationPart.getRelationship().getId();
    }

    public static String createVideoRelation(XSLFSlide slide, String videoPath, String coverPath){
        PackagePart pp = slide.getPackagePart();
        URI mp4uri = null;
        try {
            mp4uri = new URI("./" + videoPath);
        } catch (URISyntaxException e) {
            throw new RuntimeException("文件地址异常");
        }
        PackageRelationship prsEmbed1 = pp.addRelationship(mp4uri, TargetMode.EXTERNAL, "http://schemas.microsoft.com/office/2007/relationships/media");
        PackageRelationship prsExec1 = pp.addRelationship(mp4uri, TargetMode.EXTERNAL, "http://schemas.openxmlformats.org/officeDocument/2006/relationships/video");

        return null;
    }


}
