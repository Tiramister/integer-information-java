package net.tiramister.integer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Factor {
  private long prime;
  private int exp;
}
