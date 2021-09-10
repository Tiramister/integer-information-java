package net.tiramister.integer.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.entity.Result;

@Getter
@Setter
@NoArgsConstructor
public class ResultForm {
  private long n;
  private String isPrime;
  private String factors;
  private int divisorNum;

  public static ResultForm build(Long n) {
    if (n == null) return null;

    Result result = new Result(n);

    ResultForm resultForm = new ResultForm();
    resultForm.setN(n);
    resultForm.setIsPrime(result.isPrime() ? "Yes" : "No");
    resultForm.setFactors(result.getFactors().toString());
    resultForm.setDivisorNum(result.getDivisorNum());

    return resultForm;
  }
}
