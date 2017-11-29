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
  
  "Mutant dna 2" should " be a mutant"  in {
    val dnaStr = Array("ATGCGA", "CAGTGC", "TTATGT", "AGTCGG", "CCCTTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be (true)
  }
  
  "Mutant dna 3" should " be a mutant"  in {
    val dnaStr = Array("AGGCGA", "CAGGGC", "TTATGT", "AGGCGG", "CCCTTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be (true)
  }
  
  "Mutant dna 4" should " be a mutant"  in {
    val dnaStr = Array("ATGCGAATGCGA", "CAGTGCATACGC", "TTATGTATGCGA", "AGTCGGATACGC", "CCCTTAATGCGA", "CAGTTAATACGC", "CCCGTAATGCGA", "CAGTACCTGCGC",
                "CCCTTAATACGA", "CACGTACTGCGC", "CCCTCAATACGA", "TCACTGATGCGA")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be (true)
  }
  
  "Mutant dna with inverted diagonal " should " not be a mutant"  in {
    val dnaStr = Array(
        "ATGCGA", 
        "CCGCGC", 
        "TTCTGT", 
        "ACAAGG", 
        "CCTCTA", 
        "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be (false)
  }
  
}