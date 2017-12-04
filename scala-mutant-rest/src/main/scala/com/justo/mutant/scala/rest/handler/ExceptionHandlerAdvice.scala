package com.justo.mutant.scala.rest.handler

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.http.ResponseEntity
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import com.justo.mutant.scala.configuration.log.log

@ControllerAdvice
class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler(Array(classOf[IllegalArgumentException]))
  def handleWrongArgs(ex: Exception, request: WebRequest): ResponseEntity[Unit] = {
    log.Exceptions.info("Wrong arguments: {}", request, ex)
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build()
  }
  
}