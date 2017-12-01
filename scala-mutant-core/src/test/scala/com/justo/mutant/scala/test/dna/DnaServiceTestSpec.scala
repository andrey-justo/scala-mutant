package com.justo.mutant.scala.test.dna

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context._
import com.justo.mutant.scala.test.MainTestSpec
import com.justo.mutant.scala.test.configuration.MongoConfigurationTest
import com.justo.mutant.scala.components.dna.DnaService
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest._
import org.springframework.beans.factory.annotation.Autowired
import com.justo.mutant.scala.configuration.DnaConfiguration
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestContext
import org.springframework.test.context.TestContextManager
import com.justo.mutant.scala.test.spring.TestContextManagement
import com.justo.mutant.scala.test.configuration.MongoTestSpecs
import com.justo.mutant.scala.test.configuration.MongoConfigurationTest


@EnableAutoConfiguration()
class DnaServiceTestSpec extends MainTestSpec with MongoTestSpecs with Matchers with BeforeAndAfterEach {
  
  @Autowired var dnaService: DnaService = _
  
  val dnaStr = Array("ATAA", "ATTT", "AGCT", "ATTT")
  
  override def beforeEach() = {
    mongo.dropDatabase(MongoConfigurationTest.DbName)
  }
  
  "Persist one" should "save it in database" in {
    val dna = dnaService.insert(dnaStr, true)
    dna.isDefined should be(true)
    dna.get.mutant should be(true)
    
    val stats = dnaService.stats()
    stats.mutants should be(1)
  }
  
  "Persist many times" should "save once in database" in {
    val dna = dnaService.insert(dnaStr, true)
    dna.isDefined should be(true)
    dna.get.mutant should be(true)
    
    val dna2 = dnaService.insert(dnaStr, true)
    dna2.isDefined should be(false)
    
    val stats = dnaService.stats()
    stats.mutants should be(1)
    stats.noMutants should be(0)
  }
  
}