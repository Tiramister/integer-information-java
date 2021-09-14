package net.tiramister.integer.form;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.entity.Factors;
import net.tiramister.integer.entity.Result;
import net.tiramister.integer.entity.YesNo;
import net.tiramister.integer.logic.DivisorCounter;
import net.tiramister.integer.logic.PrimitiveRoot;

/** 整数の情報を表示用の形式で持つクラス. */
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
  private YesNo hasPrimitiveRoot;
  private long minimumPrimitiveRoot;

  public static ResultForm build(long n) {
    ResultForm resultForm = new ResultForm();
    resultForm.setN(n);

    if (n != 0) {
      Result result = new Result(n);
      long minimumPrimitiveRoot = PrimitiveRoot.minimum(n);

      resultForm.setIsPrime(YesNo.valueOf(result.isPrime()));
      resultForm.setFactors(result.getFactors());
      resultForm.setDivisorNum(result.getDivisorNum());
      resultForm.setHighlyComposite(result.getHighlyComposite());
      resultForm.setMaximumDivisorNum(DivisorCounter.countDivisors(result.getHighlyComposite()));
      resultForm.setTotient(result.getTotient());
      resultForm.setHasPrimitiveRoot(YesNo.valueOf(minimumPrimitiveRoot != -1));
      resultForm.setMinimumPrimitiveRoot(minimumPrimitiveRoot);
    }

    return resultForm;
  }
}
