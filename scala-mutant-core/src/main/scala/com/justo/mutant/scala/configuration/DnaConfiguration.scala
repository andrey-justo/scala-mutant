package com.justo.mutant.scala.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import com.justo.mutant.scala.components.dna.DnaRepository
import org.springframework.context.annotation.Bean
import org.springframework.beans.factory.annotation.Autowired
import com.justo.mutant.scala.components.dna.DnaService

@Configuration
class DnaConfiguration {
  
  @Bean
  @Autowired
  def dnaRepository(mongoTemplate: MongoTemplate): DnaRepository = { new DnaRepository(mongoTemplate)}
  
  @Bean
  @Autowired
  def dnaService(dnaRepository: DnaRepository): DnaService = { new DnaService(dnaRepository)}
  
}