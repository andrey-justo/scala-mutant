package com.justo.mutant.scala.components.dna.mutant

import com.justo.mutant.scala.components.dna.Dna

class MutantService(dnaStr: Option[Array[String]], sequencePattern: SequencePattern) {
  
  def hasMutation(): Dna = {
    dnaStr match {
      case dnaStr if dnaStr.isDefined && sequencePattern.validate(dnaStr.get) => Dna(dnaStr.get, mutation())
      case _ => throw new IllegalArgumentException("Dna String should be an square array")
    }
  }
  
  //TODO: try to use a big regex
  private def mutation(): Boolean = {
    val cols = dnaStr.get.head.length()
    val entireDna = dnaStr.mkString("")
    
    @volatile var nMutations = 0
    entireDna.zipWithIndex.foreach { case (c, i) =>
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + 1, stopRow)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols, stopCol)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols + 1, (s, cols, size) => true)
      if (nMutations > sequencePattern.minSeqs) {
        return true
      }
    }
    
    return false
  }
  
  private def matching(data: String, start:Int, cols: Int, skipFunc: Int => Int, stopCondition: (Int, Int, Int) => Boolean): Int = {
    if (stopCondition(start, cols, data.length())) {
      return 0
    }
    
    @volatile var position = start
    (0 to sequencePattern.minSeqs.toInt).map { case (i) =>
      @volatile var newPosition = skipFunc(position)
      if (newPosition >= data.length() || data(position) != data(newPosition)) {
        return 0
      }
    }
    
    return 1
  }
  
  private def stopRow(p: Int, cols: Int, size: Int): Boolean = (p % cols) - sequencePattern.minSeqs.toInt > 0
  private def stopCol(p: Int, cols: Int, size: Int): Boolean = (p * cols) >= size 
  
}