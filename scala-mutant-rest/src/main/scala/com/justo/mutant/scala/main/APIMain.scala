package com.justo.mutant.scala.main

import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Configuration
@EnableAutoConfiguration
@ComponentScan(Array("com.justo.mutant.scala"))
@SpringBootApplication
class APIMain {
  
}

object APIMain extends App {
  SpringApplication.run(classOf[APIMain])
}