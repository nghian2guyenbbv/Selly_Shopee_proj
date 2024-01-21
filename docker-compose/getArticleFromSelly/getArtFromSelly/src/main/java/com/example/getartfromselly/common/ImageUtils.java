package com.example.getartfromselly.common;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class ImageUtils {
  public static BufferedImage convertByteArrayToImage(byte[] data) {
    BufferedImage bufferedImage = null;
    try {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
      bufferedImage = ImageIO.read(byteArrayInputStream);
    } catch (IOException ex) {
      log.error("can't ptocess iamges: {}", ex);
    }
    return bufferedImage;

  }

  /*BufferedImage bufferedImage = convertByteArrayToImage(imageData);

  // Save the image to D:/selly folder
  saveImage(bufferedImage, "D:/selly/myImage.png");*/
  public static void saveImage(BufferedImage bufferedImage, String filepath) {
    File fileOutput = new File(filepath);
    try {
      ImageIO.write(bufferedImage, "png", fileOutput);
    } catch (IOException e) {
      log.error("cant save images: {}", e);
    }

  }
}
