package com.justo.mutant.scala.components.dna.mutant

import com.justo.mutant.scala.components.dna.Dna

class Mutant(dnaStr: Array[String], sequencePattern: SequencePattern) {
  
  def hasMutation(): Dna = {
    dnaStr match {
      case dnaStr if sequencePattern.validate(dnaStr) => new Dna(dnaStr, mutation())
      case _ => throw new IllegalArgumentException("Dna String should be an square array")
    }
  }
  
  //TODO: try to use a big regex
  private def mutation(): Boolean = {
    val cols = dnaStr.head.length()
    val entireDna = dnaStr.mkString("")
    
    var nMutations = 0
    entireDna.zipWithIndex.foreach { case (c, i) =>
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + 1, stopRow)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols, stopCol)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols + 1, (s, cols, size) => false)
      if (nMutations > sequencePattern.minSeqs) {
        return true
      }
    }
    
    return false
  }
  
  private def matching(data: String, start:Int, cols: Int, skipFunc: Int => Int, condition: (Int, Int, Int) => Boolean): Int = {
    if (!condition(start, cols, data.length())) {
      return 0
    }
    
    var position = start
    (0 to sequencePattern.minSeqs.toInt).map { case (i) =>
      var newPosition = skipFunc(position)
      if (newPosition >= data.length() || data(position) != data(newPosition)) {
        return 0
      }
    }
    
    return 1
  }
  
  private def stopRow(p: Int, cols: Int, size: Int): Boolean = (p % cols) - sequencePattern.minSeqs.toInt > 0
  private def stopCol(p: Int, cols: Int, size: Int): Boolean = (p * cols) >= size 
  
}