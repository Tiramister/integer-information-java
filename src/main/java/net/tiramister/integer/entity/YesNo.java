package net.tiramister.integer.entity;

import lombok.Getter;
import lombok.Setter;

/** 真理値を保持して、"Yes"か"No"を出力するクラス. */
@Setter
@Getter
public class YesNo {
  boolean yes;

  /**
   * static コンストラクタ
   *
   * @param yes yesか否か
   * @return インスタンス
   */
  public static YesNo valueOf(boolean yes) {
    YesNo yesNo = new YesNo();
    yesNo.setYes(yes);
    return yesNo;
  }

  /** 保持している真理値がtrueなら"Yes"、falseなら"No"を返す. */
  @Override
  public String toString() {
    return this.isYes() ? "Yes" : "No";
  }
}
