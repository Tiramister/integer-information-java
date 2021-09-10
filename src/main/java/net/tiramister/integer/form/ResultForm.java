package net.tiramister.integer.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.entity.Result;
import net.tiramister.integer.logic.DivisorCounter;

@Getter
@Setter
@NoArgsConstructor
public class ResultForm {
  private long n;
  private String isPrime;
  private String factors;
  private int divisorNum;
  private long highlyComposite;
  private int maximumDivisorNum;

  public static ResultForm build(long n) {
    if (n == 0) return null;

    Result result = new Result(n);

    ResultForm resultForm = new ResultForm();
    resultForm.setN(n);
    resultForm.setIsPrime(result.isPrime() ? "Yes" : "No");
    resultForm.setFactors(result.getFactors().toString());
    resultForm.setDivisorNum(result.getDivisorNum());
    resultForm.setHighlyComposite(result.getHighlyComposite());
    resultForm.setMaximumDivisorNum(DivisorCounter.countDivisors(result.getHighlyComposite()));

    return resultForm;
  }
}
