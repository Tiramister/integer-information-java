package net.tiramister.integer.form;

import lombok.Getter;
import lombok.Setter;
import net.tiramister.integer.logic.PrimeJudge;

@Getter
@Setter
public class ResultForm {
  private Long n;
  private Boolean isPrime;

  public static ResultForm build(Long n) {
    if (n == null) return null;

    ResultForm result = new ResultForm();

    result.setN(n);
    result.setIsPrime(PrimeJudge.isPrime(n));

    return result;
  }
}
