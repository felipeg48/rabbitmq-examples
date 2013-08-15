/**
 * 
 */
package com.rabbit.spring.examples

import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class SpringProducer {

	@Autowired
	private RabbitTemplate amqpTemplate
	
	void send(opts){
		
		Message msg
		if(opts.msg instanceof byte[])
			msg = new Message(opts.msg,opts.msgproperties)
		
		amqpTemplate.send opts.exchange,opts.routingkey, msg
		
	}
	
}
