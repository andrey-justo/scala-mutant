package com.justo.mutant.scala.components.dna

import com.justo.mutant.scala.components.dna.stats.Stats
import com.justo.mutant.scala.configuration.log.log

class DnaService(repository: DnaRepository) {
  
  def stats(): Stats = {
    new Stats(repository.countByMutant(true), repository.countByMutant(false))
  }
  
  def insert(dna: Array[String], mutant: Boolean): Dna = {
    repository.insert(new Dna(dna, mutant))
  }
  
}