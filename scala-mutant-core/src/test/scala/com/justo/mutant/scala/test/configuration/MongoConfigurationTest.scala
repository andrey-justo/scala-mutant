package com.justo.mutant.scala.test.configuration

import org.springframework.context.annotation.Configuration
import de.flapdoodle.embed.mongo.MongodProcess
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.distribution.Version
import org.springframework.context.annotation.Bean
import com.mongodb.Mongo
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory
import de.flapdoodle.embed.mongo.config.IMongoConfig
import de.flapdoodle.embed.mongo.config.IMongodConfig
import com.mongodb.MongoClient
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.beans.factory.annotation.Autowired
import javax.annotation.PreDestroy

object MongoConfigurationTest {
  private val DbName = "mutant_tests"
}

@Configuration
class MongoConfigurationTest {
  
	private def config: IMongodConfig = { new MongodConfigBuilder().version(Version.V3_4_1).build() }
  
  @Bean def mongoProcess(): MongodProcess = { MongodStarter.getDefaultInstance().prepare(config).start() }
  
  @Bean def mongo(): MongoClient = { new MongoClient() }
  
  @Autowired
  @Bean def mongoTemplate(mongo: MongoClient): MongoTemplate = { new MongoTemplate(mongo, MongoConfigurationTest.DbName)}
 
}