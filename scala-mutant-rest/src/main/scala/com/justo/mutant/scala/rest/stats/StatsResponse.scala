package com.justo.mutant.scala.rest.stats

case class StatsResponse(countMutantDna: Long = 0, countHumanDna: Long = 0) {
  
  def ratio: Double = { val total = countHumanDna + countMutantDna; if (total == 0) 0 else countMutantDna / total }
  
}