package net.tiramister.integer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.logic.DivisorCounter;
import net.tiramister.integer.logic.Factorizer;
import net.tiramister.integer.logic.HighlyComposite;
import net.tiramister.integer.logic.PrimeUtil;
import net.tiramister.integer.logic.PrimitiveRoot;
import net.tiramister.integer.logic.Totient;

/** 与えられた整数に関する情報をまとめるクラス. */
@Getter
@Setter
@NoArgsConstructor
public class Result {
  private boolean isPrime;
  private long nextPrime;
  private Factors factors;
  private int divisorNum;
  private long highlyComposite;
  private long totient;
  private long minimumPrimitiveRoot;

  /**
   * 整数nに関する情報で初期化する. *
   *
   * @param n 正の整数
   */
  public Result(long n) {
    this();

    Factors factors = Factorizer.factorize(n);
    this.setPrime(PrimeUtil.isPrime(n));
    this.setNextPrime(PrimeUtil.nextPrime(n));
    this.setFactors(factors);
    this.setDivisorNum(DivisorCounter.countDivisors(factors));
    this.setHighlyComposite(HighlyComposite.maximum(n));
    this.setTotient(Totient.totient(factors));
    this.setMinimumPrimitiveRoot(PrimitiveRoot.minimum(n));
  }
}
