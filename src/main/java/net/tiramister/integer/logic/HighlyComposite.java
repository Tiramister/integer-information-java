package net.tiramister.integer.logic;

import lombok.Getter;
import lombok.Setter;

/** 最大の高度合成数を計算するメソッド群. */
public class HighlyComposite {
  /** 再帰用のクラス. */
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

    /**
     * 高度合成数を更新.
     *
     * @param prod 整数
     * @param dnum prodの約数の個数
     */
    private void update(long prod, int dnum) {
      if (dnum > this.getMaxDnum()) {
        this.setMaxDnum(dnum);
        this.setBestProd(prod);
      }
      if (dnum == this.getMaxDnum() && prod < this.getBestProd()) {
        this.setBestProd(prod);
      }
    }

    /**
     * 再帰的に高度合成数の可能性を全探索する.
     *
     * @param p 次の素因数
     * @param prod 現在の値
     * @param prevE 直前の素因数の指数 最初は-1
     * @param dnum 現在の約数の個数
     */
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

  /**
   * n以下の最大の高度合成数を計算する.
   *
   * @param n
   * @return n以下の最大の高度合成数
   */
  public static long maximum(long n) {
    Recurse recurse = new Recurse(n);
    recurse.rec(1, 1, -1, 1);
    return recurse.getBestProd();
  }
}
