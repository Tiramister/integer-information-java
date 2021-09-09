package net.tiramister.integer.form;

import lombok.Getter;
import lombok.Setter;
import net.tiramister.integer.logic.PrimeJudge;

@Getter
@Setter
public class ResultForm {
  private boolean isPrime;

  public static ResultForm build(Long n) {
    ResultForm result = new ResultForm();

    result.setPrime(PrimeJudge.isPrime(n));

    return result;
  }
}
