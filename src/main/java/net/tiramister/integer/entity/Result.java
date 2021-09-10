package net.tiramister.integer.entity;

import java.util.ArrayList;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.tiramister.integer.logic.Factorizer;
import net.tiramister.integer.logic.PrimeJudger;

@Getter
@Setter
@NoArgsConstructor
public class Result {
  private boolean isPrime;
  private ArrayList<Factor> factors;

  public Result(long n) {
    this();
    this.setPrime(PrimeJudger.isPrime(n));
    this.setFactors(Factorizer.factorize(n));
  }
}
