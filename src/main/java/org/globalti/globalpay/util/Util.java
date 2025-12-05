package org.globalti.globalpay.util;

import java.math.BigInteger;
import java.security.*;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
  public static ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
  }

  public static String generateMD5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");

      md.update(input.getBytes());

      byte[] digest = md.digest();

      BigInteger bigInt = new BigInteger(1, digest);
      String hashtext = bigInt.toString(16);

      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Long generateAccountNumber(int length) {
    if (length <= 0) {
      throw new IllegalArgumentException("Length must be a positive integer.");
    }

    Random random = new Random();
    StringBuilder sb = new StringBuilder();

    sb.append(random.nextInt(9) + 1);

    for (int i = 1; i < length; i++) {
      sb.append(random.nextInt(10));
    }

    return Long.parseLong(sb.toString());
  }
}