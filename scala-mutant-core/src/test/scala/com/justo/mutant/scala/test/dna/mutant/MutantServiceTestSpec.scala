package com.justo.mutant.scala.test.dna.mutant

import com.justo.mutant.scala.test.MainTestSpec
import com.justo.mutant.scala.components.dna.mutant.MutantService
import com.justo.mutant.scala.components.dna.mutant.SequencePattern
import com.justo.mutant.scala.components.dna.mutant.SequencePatternTO
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

class MutantServiceTestSpec extends MainTestSpec with Matchers {
  
  private val seqPattern = SequencePatternTO()
  
  "Mutant dna" should " be a mutant"  in {
    val dnaStr = Array("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be (true)
  }
  
}