package com.justo.mutant.scala.test

import org.junit.runner.RunWith

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context._
import org.scalatest._
import org.scalatest.junit.JUnitRunner
import org.springframework.context.annotation.ComponentScan
import com.justo.mutant.scala.test.configuration.MongoConfigurationTest
import com.justo.mutant.scala.configuration.DnaConfiguration

@RunWith(classOf[JUnitRunner])
@SpringBootTest(classes = Array(classOf[MongoConfigurationTest], classOf[DnaConfiguration]))
@EnableAutoConfiguration
@ComponentScan(basePackages = Array("com.justo.mutant.scala"))
abstract class MainTestSpec extends FlatSpec {
  
}