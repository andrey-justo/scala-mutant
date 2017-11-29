package com.justo.mutant.scala.components.dna.mutant

import java.util.regex.Pattern

case class SequencePatternTO(val validChars: Array[String] = Array[String]("T", "C", "G", "A"), val seqSize: Long = 4, val minSeqs: Long = 2) extends SequencePattern {

  def pattern = Pattern.compile("[^" + validChars.mkString("") + "]")

  def validate(dna: Array[String]): Boolean = dna match {
    case dna if dna.length > 0 => !dna.map(line => validate(line, dna.length)).exists(validation => !validation)
    case _ => return false
  }

  private def validate(line: String, size: Long): Boolean = line match {
    case line if !line.isEmpty() && line.length() == size => !pattern.matcher(line).find()
    case _ => false
  }

}