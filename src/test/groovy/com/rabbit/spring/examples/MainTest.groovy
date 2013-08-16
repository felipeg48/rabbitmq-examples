/**
 * 
 */
package com.rabbit.spring.examples

import static org.junit.Assert.*

import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.amqp.core.MessageDeliveryMode
import org.springframework.amqp.core.MessageProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author fgutierrezcru
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml")
class MainTest {

	@Autowired
	SpringProducer springProducer
	@Autowired
	SpringConsumer springConsumer
	
	@Test
	@Ignore
	void simple() {
		
		def producer = Thread.start{
			MessageProperties props = new MessageProperties()
			props.deliveryMode = MessageDeliveryMode.PERSISTENT
			
			def msg = "Hello World from Spring/Groovy [${Date.now()}]"
			
			springProducer.send exchange:"WorkExchange",routingkey:"",msgproperties:props,msg:msg.bytes
		}
		
		def consumer = Thread.start{
			springConsumer.consume queue:"WorkQueue"
		}
		
		[producer,consumer]*.join()
	}
	
	@Test
	@Ignore
	void asyncConsumer(){
		
		def consumer = Thread.start{
			springConsumer.consumeAsync queue:"WorkQueue"
			sleep 1000
		}
		
		[consumer]*.join()
	}
	
	@Test
	@Ignore
	void asyncConsumerAdpater(){
		//Just run and wait
		sleep 3000
	}
	
	@Test
	@Ignore
	void simpleWithSSL(){
		def producer = Thread.start{
			MessageProperties props = new MessageProperties()
			props.deliveryMode = MessageDeliveryMode.PERSISTENT
			
			def msg = "Hello World from Spring/Groovy with SSL [${Date.now()}]"
			
			springProducer.send exchange:"WorkExchange",routingkey:"",msgproperties:props,msg:msg.bytes
		}
		
		def consumer = Thread.start{
			springConsumer.consume queue:"WorkQueue"
		}
		
		[producer,consumer]*.join()
	}
}
