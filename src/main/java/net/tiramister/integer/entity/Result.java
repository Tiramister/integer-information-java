package net.tiramister.integer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.logic.DivisorCounter;
import net.tiramister.integer.logic.Factorizer;
import net.tiramister.integer.logic.HighlyComposite;
import net.tiramister.integer.logic.PrimeJudger;

@Getter
@Setter
@NoArgsConstructor
public class Result {
  private boolean isPrime;
  private Factors factors;
  private int divisorNum;
  private long highlyComposite;

  public Result(long n) {
    this();
    this.setPrime(PrimeJudger.isPrime(n));
    this.setFactors(Factorizer.factorize(n));
    this.setDivisorNum(DivisorCounter.countDivisors(n));
    this.setHighlyComposite(HighlyComposite.maximum(n));
  }
}
