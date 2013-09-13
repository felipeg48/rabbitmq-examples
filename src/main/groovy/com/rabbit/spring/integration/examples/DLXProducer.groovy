/**
 * 
 */
package com.rabbit.spring.integration.examples

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.integration.MessageChannel
import org.springframework.integration.support.MessageBuilder
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class DLXProducer {

	@Autowired
	private MessageChannel toRabbit
	
	void send(params){
		println "Producing: $params.payload"
		toRabbit.send MessageBuilder.withPayload(params.payload).build()
	}
}
