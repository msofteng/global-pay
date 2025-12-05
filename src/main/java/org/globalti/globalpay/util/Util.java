package org.globalti.globalpay.util;

import java.math.BigInteger;
import java.security.*;
import java.util.*;

import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;

public class Util {
  public static ObjectMapper objectMapper;

  public static final String MESSAGE_EMPTY = "não pode ficar vazio";
  public static final String MESSAGE_NULL = "não pode ser nulo";
  public static final String MESSAGE_POSITIVE = "deve ser maior que zero";

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

  public static Boolean isLong(String str) {
    try {
      Long.parseLong(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public static Boolean isInt(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Cortar os centavos de um valor (digitos) Ex: R$ 100,0832848 >>> toFixed(100.0832848, 2) >>> R$ 100,08
   *
   * @param number {@link Double} = valor a ser formatado
   * @param digits {@link Integer} = quantidade de casas decimais
   * 
   * @return valor formatado {@link Double}
   */
  public static Double toFixed(Double number, Integer digits) {
    return Double.parseDouble(String.format("%." + digits + "f", number));
  }

  public static String buildFieldPath(List<Reference> path) {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < path.size(); i++) {
      JsonMappingException.Reference ref = path.get(i);

      String fieldName = ref.getFieldName();

      if (i == 0) {
        if (fieldName != null) {
          sb.append(fieldName);
        }
      } else {
        if (fieldName != null) {
          sb.append("[").append(fieldName).append("]");
        }
      }

      if (ref.getIndex() >= 0) {
        sb.append("[").append(ref.getIndex()).append("]");
      }
    }

    return sb.toString();
  }

  public static String translateToken(JsonToken token) {
    if (token == null)
      return "valor inválido";

    switch (token) {
      case VALUE_TRUE:
      case VALUE_FALSE:
        return "booleano";

      case VALUE_STRING:
        return "texto";

      case VALUE_NUMBER_INT:
      case VALUE_NUMBER_FLOAT:
        return "número";

      case START_OBJECT:
        return "objeto";

      case START_ARRAY:
        return "array";

      case VALUE_NULL:
        return "null";

      default:
        return token.toString();
    }
  }
}