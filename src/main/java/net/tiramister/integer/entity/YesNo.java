package net.tiramister.integer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** 真理値を保持して、"Yes"か"No"を出力するクラス. */
@Setter
@Getter
@AllArgsConstructor
public class YesNo {
  boolean yes;

  /** 保持している真理値がtrueなら"Yes"、falseなら"No"を返す. */
  @Override
  public String toString() {
    return this.isYes() ? "Yes" : "No";
  }
}
