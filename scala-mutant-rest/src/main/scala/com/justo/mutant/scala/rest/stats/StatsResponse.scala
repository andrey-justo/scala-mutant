package com.justo.mutant.scala.rest.stats

import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.annotation.JsonProperty

case class StatsResponse(_countMutantDna: Long = 0, _countHumanDna: Long = 0) {
  
  @JsonProperty def countHumanDna = _countHumanDna
  @JsonProperty def countMutantDna = _countMutantDna
  @JsonProperty def ratio: Double = { val total = countHumanDna + countMutantDna; if (total == 0) 0 else countMutantDna / total }
  
}