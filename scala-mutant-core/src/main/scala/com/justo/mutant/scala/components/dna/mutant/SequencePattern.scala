package com.justo.mutant.scala.components.dna.mutant

import java.util.regex.Pattern

trait SequencePattern {
  
  def pattern: Pattern
  
  def seqSize: Long
  
  def minSeqs: Long
  
  def validate(dna: Array[String]): Boolean
  
}