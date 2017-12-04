package com.justo.mutant.scala.rest.mutant

import com.fasterxml.jackson.annotation.JsonProperty

case class MutantRequest(@JsonProperty val dna: Array[String]) {}