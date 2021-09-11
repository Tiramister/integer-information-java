package net.tiramister.integer.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.entity.Factors;
import net.tiramister.integer.entity.Result;
import net.tiramister.integer.entity.YesNo;
import net.tiramister.integer.logic.DivisorCounter;

@Getter
@Setter
@NoArgsConstructor
public class ResultForm {
  private long n;
  private YesNo isPrime;
  private Factors factors;
  private int divisorNum;
  private long highlyComposite;
  private int maximumDivisorNum;
  private long totient;

  public static ResultForm build(long n) {
    ResultForm resultForm = new ResultForm();
    resultForm.setN(n);

    if (n != 0) {
      Result result = new Result(n);
      resultForm.setIsPrime(new YesNo(result.isPrime()));
      resultForm.setFactors(result.getFactors());
      resultForm.setDivisorNum(result.getDivisorNum());
      resultForm.setHighlyComposite(result.getHighlyComposite());
      resultForm.setMaximumDivisorNum(DivisorCounter.countDivisors(result.getHighlyComposite()));
      resultForm.setTotient(result.getTotient());
    }

    return resultForm;
  }
}
