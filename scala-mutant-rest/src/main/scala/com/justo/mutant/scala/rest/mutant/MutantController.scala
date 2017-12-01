package com.justo.mutant.scala.rest.mutant

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.beans.factory.annotation.Autowired
import com.justo.mutant.scala.components.dna.DnaService
import com.justo.mutant.scala.components.dna.mutant.MutantService
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
class MutantController {

  val mutantService = new MutantService()
  @Autowired var dnaService: DnaService = _

  @RequestMapping(path = Array("/mutant"), method = Array(RequestMethod.POST))
  def checkMutations(@RequestBody mutantRequest: MutantRequest): ResponseEntity[Unit] = {
    val checkedDna = mutantService.hasMutation(Some(mutantRequest.dna))
    dnaService.insert(mutantRequest.dna, checkedDna.mutant)
    if (checkedDna.mutant) ResponseEntity.ok().build() else ResponseEntity.status(HttpStatus.FORBIDDEN).build()
  }

}