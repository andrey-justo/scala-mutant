package com.justo.mutant.scala.configuration.log

import org.slf4j.MDC
import java.util.UUID

object logManager {

  private val StampLabel = "stamp"

  def getStamp: String = {
    MDC.get(StampLabel)
  }

  def stamp(stamp: String) = {
    stamp match {
      case stamp if (!stamp.isEmpty()) => stamp.replaceAll("[^0-9a-zA-Z]", "")
      case _ => UUID.randomUUID().toString().replaceAll("[^0-9a-zA-Z]", "")
    }
  }

  def stampNull = stamp(null)

  def stampNull(stamp: String) {
    val currentStamp = getStamp
    currentStamp match {
      case currentStamp if (!currentStamp.isEmpty()) => return
      case _ => logManager.stamp(stamp)
    }
  }

  def clear {
    MDC.clear()
  }

}