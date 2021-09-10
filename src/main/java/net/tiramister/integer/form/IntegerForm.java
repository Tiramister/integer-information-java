package net.tiramister.integer.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntegerForm {
  @Min(value = 1L, message = "\\(1\\) 以上の整数を入力して下さい")
  @Max(value = 1000000000000L, message = "\\(10^{12}\\) 以下の整数を入力して下さい")
  private Long integer;
}
