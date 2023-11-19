package com.dropship.uploadshoppeart.service;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public class FormatmessageShopeeRule {
  public static String formatMessage(String originMessage) {
    return Optional.ofNullable(originMessage).isPresent() ? StringUtils.replaceAll(originMessage, "\n",
        StringUtils.SPACE) : StringUtils.EMPTY;

  }
}
