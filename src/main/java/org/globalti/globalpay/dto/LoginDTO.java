package org.globalti.globalpay.dto;

import static org.globalti.globalpay.util.Util.*;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
public class LoginDTO {
  @NotBlank(message = MESSAGE_EMPTY)
  @NotNull(message = MESSAGE_NULL)
  private String username;

  @NotBlank(message = MESSAGE_EMPTY)
  @NotNull(message = MESSAGE_NULL)
  private String password;
}