package com.justo.mutant.scala.components.dna

import org.springframework.dao.DuplicateKeyException
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.MongoTemplate
import com.justo.mutant.scala.configuration.log._

class DnaRepository(val template: MongoTemplate) {

  private val DnaCollectionName = "dna"

  def countByMutant(isMutant: Boolean): Long = {
    val query = new Query(Criteria.where("mutant").is(isMutant))
    template.count(query, DnaCollectionName)
  }

  def insert(dnaObject: Dna): Dna = {
    try {
      template.insert(dnaObject, DnaCollectionName)
    } catch {
      case duplicatedException: DuplicateKeyException => log.Data.error("This Dna have been already checked", duplicatedException)
    }

    dnaObject
  }

}