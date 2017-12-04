package com.justo.mutant.scala.configuration.log

import com.typesafe.scalalogging.Logger

object log {
  val Core = Logger("core")
  val System = Logger("system")
  val Data = Logger("data")
  val Requests = Logger("requests")
  val Exceptions = Logger("exceptions")
}