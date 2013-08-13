/**
 * 
 */
package com.rabbit.groovy.examples

import static org.junit.Assert.*

import org.junit.Test

import com.rabbitmq.client.AMQP

/**
 * @author fgutierrezcru
 *
 */
class MainTest {

	@Test
	public void simple() {
		def p = new Producer()
		def producer = Thread.start {
			
			AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder()
			
			def props = builder.deliveryMode(2).build()
			def msg = "Hello World from Groovy [${Date.now()}]"
			
			p.send exchange:"WorkExchange",routingkey:"",msgproperties:props,msg:msg.bytes
		}
		
		def c = new Consumer()
		def consumer = Thread.start {
			c.consume queue:"WorkQueue",ack:false, reject:true, requeue:false, deadletter:true, deadletterex:"RetryExchange"
		}
		
		[producer,consumer]*.join()
	}

	
}
