package net.tiramister.integer.logic;

import lombok.Getter;
import lombok.Setter;

public class HighlyComposite {
  @Getter
  @Setter
  private static class Recurse {
    long n;
    int maxDnum;
    long bestProd;

    public Recurse(long n) {
      this.setN(n);
      this.setMaxDnum(0);
      this.setBestProd(0);
    }

    private void update(long prod, int dnum) {
      if (dnum > this.getMaxDnum()) {
        this.setMaxDnum(dnum);
        this.setBestProd(prod);
      }
      if (dnum == this.getMaxDnum() && prod < this.getBestProd()) {
        this.setBestProd(prod);
      }
    }

    // 次の素因数, 現在の総積, 直前の指数, 現在の約数の個数
    public void rec(long p, long prod, int prevE, int dnum) {
      update(prod, dnum);

      while (!PrimeJudger.isPrime(p)) ++p;

      // 指数列は広義単調減少
      for (int e = 1; (prevE == -1 || e <= prevE) && prod <= this.getN() / p; ++e) {
        prod *= p;
        rec(p + 1, prod, e, dnum * (e + 1));
      }
    }
  }

  public static long maximum(long n) {
    Recurse recurse = new Recurse(n);
    recurse.rec(1, 1, -1, 1);
    return recurse.getBestProd();
  }
}
