package net.tiramister.integer.entity;

import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** 素因数分解の結果を格納するクラス. */
@Getter
@Setter
public class Factors {
  ArrayList<Factor> factors;

  public Factors() {
    this.setFactors(new ArrayList<Factor>());
  }

  /**
   * prime^exp の項を追加する.
   *
   * <p>prime は素数で重複がなく、かつ単調増加の順に追加されることが想定される.
   *
   * @param prime 素因数
   * @param exp 指数
   */
  public void add(long prime, int exp) {
    this.getFactors().add(new Factor(prime, exp));
  }

  /** 1の表現として1^1を返す. */
  public static Factors one() {
    Factors factors = new Factors();
    factors.add(1, 1);
    return factors;
  }

  /** 素因数分解が1のものと同値か判定する. */
  public boolean isOne() {
    return this.getFactors().get(0).getPrime() == 1;
  }

  /**
   * 素因数分解の結果を TeX 記法で文字列化する.
   *
   * <p>1の場合は"1"が返される.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < this.getFactors().size(); ++i) {
      sb.append(this.getFactors().get(i));
      if (i + 1 < this.getFactors().size()) {
        sb.append(" \\cdot ");
      }
    }
    return sb.toString();
  }

  /** 1つの素因数と、その指数を保持するクラス. */
  @Getter
  @Setter
  @AllArgsConstructor
  public class Factor {
    private long prime;
    private int exp;

    /**
     * p^e を TeX 記法で文字列化する.
     *
     * <p>指数が1の場合、 p のみを返す.
     */
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(this.getPrime());
      if (this.getExp() != 1) {
        sb.append("^{");
        sb.append(this.getExp());
        sb.append("}");
      }
      return sb.toString();
    }
  }
}
