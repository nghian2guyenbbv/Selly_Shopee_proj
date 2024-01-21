package com.example.getartfromselly.common;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageUtils {
  public static final String SHOPEE_IMAGE_FOLDER = "D:\\shopeeImage\\";
  public static BufferedImage convertByteArrayToImage(byte[] data) {
    BufferedImage bufferedImage = null;
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
      bufferedImage = ImageIO.read(byteArrayInputStream);
    } catch (IOException ex) {
      log.error("can't process images: {}", ex);
    }
    return bufferedImage;

  }

  public static void saveImage(byte[] data, String productName) {
    //get photo from db and update photo name
    BufferedImage bufferedImage = convertByteArrayToImage(data);
    String filepath = SHOPEE_IMAGE_FOLDER+ StringUtils.replace(productName, " ", "-") +".png";
    File fileOutput = new File(filepath);
    fileOutput.getParentFile().mkdirs();
    try {
      ImageIO.write(bufferedImage, "png", fileOutput);
    } catch (IOException e) {
      log.error("cant save images: {}", e);
    }

  }
}
