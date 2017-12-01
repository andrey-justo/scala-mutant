package com.justo.mutant.scala.components.dna.mutant

import com.justo.mutant.scala.components.dna.Dna

class MutantService(sequencePattern: SequencePattern = SequencePatternTO()) {

  def hasMutation(dnaStr: Option[Array[String]]): Dna = {
    dnaStr match {
      case dnaStr if dnaStr.isDefined && sequencePattern.validate(dnaStr.get) => Dna(dnaStr.get.mkString(""), mutation(dnaStr.get))
      case _ => throw new IllegalArgumentException("Dna String should be an square array")
    }
  }

  //TODO: try to use a big regex
  private def mutation(dnaStr: Array[String]): Boolean = {
    val cols = dnaStr.head.length()
    val entireDna = dnaStr.mkString("")

    @volatile var nMutations = 0
    (0 to entireDna.length() - 1).foreach { i =>
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + 1, stopRow)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols, stopCol)
      nMutations = nMutations + matching(entireDna, i, cols, (x) => x + cols + 1, stopRow)
      if (nMutations >= sequencePattern.minSeqs) {
        return true
      }
    }

    return false
  }

  private def matching(data: String, start: Int, cols: Int, jump: Int => Int, stopCondition: (Int, Int, Int) => Boolean): Int = {
    if (stopCondition(start, cols, data.length())) {
      return 0
    }

    @volatile var position = 0
    @volatile var newPosition = start
    (1 to sequencePattern.seqSize.toInt - 1).foreach {
      case (i) =>
        position = newPosition
        newPosition = jump(position)
        if (newPosition >= data.length() || data(position) != data(newPosition)) {
          return 0
        }
    }

    return 1
  }

  private def stopRow(p: Int, cols: Int, size: Int): Boolean = (cols - p % cols) - sequencePattern.seqSize.toInt < 0
  private def stopCol(p: Int, cols: Int, size: Int): Boolean = p + cols * (sequencePattern.seqSize.toInt - 1) > size

}