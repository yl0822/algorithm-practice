package org.practice.challenge.ppt;

import com.qunhe.geom.middleware.graphic.design.service.data.worker.ppt.request.PptImageData;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.ShapeType;
import org.apache.poi.xslf.usermodel.*;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author feikong
 * @version 2022/8/9
 */
public class PptTest {

    public static void createPicShape(final XSLFSlide slide, final PptImageData imageData) {
        final XSLFPictureData pictureData = addPic2MediaRepo(slide, imageData);
        final XSLFPictureShape pictureShape = slide.createPicture(pictureData);
        pictureShape.setAnchor(new Rectangle2D.Double(imageData.getX(), imageData.getY(), imageData.getW(),
                imageData.getH()));
    }

    private static XSLFPictureData addPic2MediaRepo(final XSLFSlide slide, final PptImageData imageData) {
        final PictureData.PictureType pictureType = getPicTypeByExtension(imageData.getUrl());
        try (final InputStream input = getInputStreamFromUrl(imageData.getUrl())) {
            return ((XMLSlideShow) slide.getParent()).addPicture(input, pictureType);
        } catch (final IOException e) {
            throw new RuntimeException("failed to add picture to ppt media repo");
        }
    }

    private static InputStream getInputStreamFromUrl(final String url) {
        try {
            // 这里先不做并发优化，后面看看耗时情况再说
            final byte[] bytes = getBytesByHttpGet(url);
            return new ByteArrayInputStream(bytes);
        } catch (final IOException e) {
            throw new RuntimeException("failed to get stream from element url:" + url);
        }
    }

    private static PictureData.PictureType getPicTypeByExtension(final String imgPath) {
        final String extension = imgPath.substring(imgPath.lastIndexOf("."));
        for (final PictureData.PictureType value : PictureData.PictureType.values()) {
            if (StringUtils.equalsIgnoreCase(value.extension, extension)) {
                return value;
            }
        }
        return PictureData.PictureType.JPEG;
    }
    private static final HttpClient HTTP_CLIENT;

    static {
        final PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setDefaultMaxPerRoute(20);
        connectionManager.setMaxTotal(200);
        HTTP_CLIENT = HttpClients.custom().setConnectionManager(connectionManager).build();
    }


    public static byte[] getBytesByHttpGet(final String url) throws IOException {
        try {
            final HttpGet httpGet = new HttpGet(url.trim());
            httpGet.addHeader("Cache-Control", "no-cache");
            final HttpResponse response = HTTP_CLIENT.execute(httpGet);
            try (final InputStream inputStream = response.getEntity().getContent()) {
                return toByteArray(inputStream);
            }
        } catch (final Exception e) {
            //do nothing and try again
        }
        // 获取图片失败,抛出异常
        throw new IOException();
    }

    private static byte[] toByteArray(final InputStream inStream) throws Exception {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int len;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        return outStream.toByteArray();
    }
}
