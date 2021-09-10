package net.tiramister.integer.logic;

import lombok.Getter;
import lombok.Setter;

public class HighlyComposite {
  @Getter
  @Setter
  private static class Recurse {
    long n;
    int dmax;
    long best;

    public Recurse(long n) {
      this.setN(n);
      this.setDmax(0);
      this.setBest(0);
    }

    public void rec(long p, long prod, int prevE, int dnum) {
      if (dnum > dmax) {
        this.setDmax(dnum);
        this.setBest(prod);
      }
      if (dnum == dmax && prod < this.getBest()) {
        this.setBest(prod);
      }

      while (!PrimeJudger.isPrime(p)) ++p;

      for (int e = 1; e <= prevE && prod <= this.getN() / p; ++e) {
        prod *= p;
        rec(p + 1, prod, e, dnum * (e + 1));
      }
    }
  }

  public static long maximum(long n) {
    Recurse recurse = new Recurse(n);
    recurse.rec(1L, 1L, 100, 1);
    return recurse.getBest();
  }
}
