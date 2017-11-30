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

  private def generateDNA(beginFrame: Array[String], endFrame: Array[String], iterations: Int): Array[String] = (beginFrame, endFrame, iterations) match {
    case (b, e, i) if e.length >= i && e(0).size >= i => return e
    case (b, e, i) => generateBigFrame(b, e, i)
  }

  private def generateBigFrame(b: Array[String], e: Array[String], i: Int): Array[String] = {
    val bigFrame = (1 to i).map({ seq =>
      val position = seq % b.length
      val line = b(position) * (i / b(position).length())
      line match {
        case line if line.length() < i => line + line.substring(0, i - line.length())
        case line => line
      }
    })

    return bigFrame.zipWithIndex.map({
      case (item, index) =>
        val position = e.length + index - (bigFrame.length - 1)
        position match {
          case position if position > 0 => item.dropRight(e(position - 1).length) + e(position - 1)
          case _ => item
        }
    }).toArray
  }

  "Mutant dna" should " be a mutant" in {
    val dnaStr = Array("ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Mutant dna 2" should " be a mutant" in {
    val dnaStr = Array("ATGCGA", "CAGTGC", "TTATGT", "AGTCGG", "CCCTTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Mutant dna 3" should " be a mutant" in {
    val dnaStr = Array("AGGCGA", "CAGGGC", "TTATGT", "AGGCGG", "CCCTTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Mutant dna 4" should " be a mutant" in {
    val dnaStr = Array("ATGCGAATGCGA", "CAGTGCATACGC", "TTATGTATGCGA", "AGTCGGATACGC", "CCCTTAATGCGA", "CAGTTAATACGC", "CCCGTAATGCGA", "CAGTACCTGCGC",
      "CCCTTAATACGA", "CACGTACTGCGC", "CCCTCAATACGA", "TCACTGATGCGA")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Mutant dna with inverted diagonal" should " not be a mutant" in {
    val dnaStr = Array("ATGCGA", "CCGCGC", "TTCTGT", "ACAAGG", "CCTCTA", "TCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(false)
  }

  "Mutant dna with diagonal" should " be a mutant" in {
    val dnaStr = Array("ATGCTA", "CCGGGC", "TTCTGT", "ATAAGG", "CCTCTA", "TCATTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Mutant complex test" should "be a mutant" in {
    val dnaStr = Array("ATGCGAATGCGAATGCGAATGCGAATGCGAATGCGA", "CAGTGCCAGTGCCAGTGCCAGTGCCAGTGCCAGTGC", "TTATGTTTATGTTTATGTTTATGTTTATGTTTATGT",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
      "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTG",
      "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
      "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
      "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG",
      "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA",
      "AGAAGGAGAAGGAGAAGGAGAAGGAGAAGGAGAAGG", "CCCCTACCCCTACCCCTACCCCTACCCCTACCCCTA", "TCACTGTCACTGTCACTGTCACTGTCACTGTCACTG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  "Not a mutant test" should "be a normal person" in {
    val dnaStr = Array("ACGCG", "TCTAG", "GCACC", "TATAG", "GCGCG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(false)
  }

  behavior of "Not a valid dna"
  it should "throw an exception" in {
    val dnaStr = Array("ACGCG", "TCTA", "GCACC", "TAG", "GCGCG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    intercept[IllegalArgumentException] {
      mutantService.hasMutation()
    }
  }

  it should "2 throw an exception" in {
    val dnaStr = Array("ACGCG", "TCTAB", "GCACC", "TAGYY", "GCGCG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    intercept[IllegalArgumentException] {
      mutantService.hasMutation()
    }
  }

  it should "3 throw an exception" in {
    val dnaStr = Array("", "TCTAG", "GCACC", "TATAG", "GCGCG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    intercept[IllegalArgumentException] {
      mutantService.hasMutation()
    }
  }

  it should "4 throw an exception" in {
    val dnaStr = Array("TCTAG", "GCACC", "TATAG", "GCGCG")
    val mutantService = new MutantService(Some(dnaStr), seqPattern)
    intercept[IllegalArgumentException] {
      mutantService.hasMutation()
    }
  }

  behavior of "Worst case"
  it should "not be a mutant" in {
    val mutantService = new MutantService(Some(generateDNA(Array("AT", "CG"), Array("ACGCG", "TATAG", "GCCCT", "TATAG", "GCGCG"), 12)), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(false)
  }

  it should "be a mutant" in {
    val mutantService = new MutantService(Some(generateDNA(Array("AT", "CG"), Array("ACGCG", "TATAG", "GCACG", "TATAG", "GCGCG"), 12)), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

  it should "2 be a mutant" in {
    val mutantService = new MutantService(Some(generateDNA(Array("AT", "CG"), Array(
      "ACCGCG",
      "AACGCG",
      "TCTCAT",
      "GGCACG",
      "ATACAG",
      "AGCGCG"), 12)), seqPattern)
    val dna = mutantService.hasMutation()
    dna.mutant should be(true)
  }

}