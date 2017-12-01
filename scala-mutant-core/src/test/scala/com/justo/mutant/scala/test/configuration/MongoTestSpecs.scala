package com.justo.mutant.scala.test.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.scalatest.{BeforeAndAfterAll, Suite}
import com.mongodb.MongoClient
import org.springframework.test.context.{TestContext, TestContextManager}
import com.justo.mutant.scala.test.spring._


trait MongoTestSpecs extends TestContextManagement { this: Suite =>
  
  @Autowired protected var mongo: MongoClient = _
  
  abstract override def beforeAll(): Unit = {
    super.beforeAll
    mongo.dropDatabase(MongoConfigurationTest.DbName)
  }
  
}