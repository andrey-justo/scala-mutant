package com.justo.mutant.scala.rest.stats

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired
import com.justo.mutant.scala.components.dna.DnaService
import org.springframework.http.ResponseEntity;

@RestController
class StatsController {

  @Autowired var dnaService: DnaService = _
  
  @RequestMapping(path = Array("/stats"), method = Array(RequestMethod.GET))
  def checkStatus(): ResponseEntity[StatsResponse] = {
    val stats = dnaService.stats()
    ResponseEntity.ok().body(StatsResponse(stats.mutants, stats.noMutants))
  }
  
}