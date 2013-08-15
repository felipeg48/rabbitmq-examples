/**
 * 
 */
package com.rabbit.spring.examples

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageListener
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class SpringConsumer {

	@Autowired
	private RabbitTemplate amqpTemplate
	
	def consume(opts){
		Message msg = amqpTemplate.receive opts.queue
		println msg.body.toReadable()
	}
	
	def consumeAsync(opts){
		def onMessage = [ onMessage: { Message msg -> println msg.body.toReadable() }] as MessageListener
		
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer()
		listenerContainer.connectionFactory = amqpTemplate.connectionFactory
		listenerContainer.queueNames = [opts.queue]
		listenerContainer.messageListener = onMessage
		listenerContainer.start()
	}
	
}

@Component
class SpringConsumerAdapter {
	void handleMessage(byte[] msg){
		println msg.toReadable()
	}
}
