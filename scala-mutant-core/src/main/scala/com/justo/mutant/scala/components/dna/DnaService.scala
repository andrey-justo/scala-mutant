package com.justo.mutant.scala.components.dna

import com.justo.mutant.scala.components.dna.stats.Stats
import com.justo.mutant.scala.configuration.log.log

class DnaService(val repository: DnaRepository) {
  
  def stats(): Stats = {
    Stats(repository.countByMutant(true), repository.countByMutant(false))
  }
  
  def insert(dna: Array[String], mutant: Boolean): Dna = {
    repository.insert(Dna(dna, mutant))
  }
  
}