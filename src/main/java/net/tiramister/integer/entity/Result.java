package net.tiramister.integer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.logic.DivisorCounter;
import net.tiramister.integer.logic.Factorizer;
import net.tiramister.integer.logic.HighlyComposite;
import net.tiramister.integer.logic.PrimeJudger;
import net.tiramister.integer.logic.Totient;

@Getter
@Setter
@NoArgsConstructor
public class Result {
  private boolean isPrime;
  private Factors factors;
  private int divisorNum;
  private long highlyComposite;
  private long totient;

  public Result(long n) {
    this();

    Factors factors = Factorizer.factorize(n);
    this.setPrime(PrimeJudger.isPrime(n));
    this.setFactors(factors);
    this.setDivisorNum(DivisorCounter.countDivisors(factors));
    this.setHighlyComposite(HighlyComposite.maximum(n));
    this.setTotient(Totient.totient(factors));
  }
}
