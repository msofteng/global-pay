package org.globalti.globalpay.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
  public static ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }
}