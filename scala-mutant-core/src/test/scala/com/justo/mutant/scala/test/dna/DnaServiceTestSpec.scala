package com.justo.mutant.scala.test.dna

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context._
import com.justo.mutant.scala.test.MainTestSpec
import com.justo.mutant.scala.test.configuration.MongoConfigurationTest
import com.justo.mutant.scala.components.dna.DnaService
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.springframework.beans.factory.annotation.Autowire
import org.scalatest._

@EnableAutoConfiguration
@SpringBootTest(classes = Array(classOf[MongoConfigurationTest]))
class DnaServiceTestSpec(private val dnaService: DnaService) extends MainTestSpec with Matchers {
  
  "Persist one" should "save it in database" in {
    val dna = dnaService.insert(Array("ATAA", "ATTT", "AGCT", "ATTT"), true)
    dna.isDefined should be(true)
    dna.get.mutant should be(true)
    
    val stats = dnaService.stats()
    stats.mutants should be(1)
  }
  
  "Persist many times" should "save it in database" in {
    val dnaStr = Array("ATAA", "ATTT", "AGCT", "ATTT")
    val dna = dnaService.insert(dnaStr, true)
    dna.isDefined should be(true)
    dna.get.mutant should be(true)
    
    val dna2 = dnaService.insert(dnaStr, true)
    dna2.isDefined should be(true)
    dna2.get.mutant should be(true)
    
    val stats = dnaService.stats()
    stats.mutants should be(1)
    stats.noMutants should be(0)
  }
  
}