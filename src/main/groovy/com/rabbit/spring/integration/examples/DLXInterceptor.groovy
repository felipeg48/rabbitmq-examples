/**
 * 
 */
package com.rabbit.spring.integration.examples

import org.springframework.integration.Message
import org.springframework.integration.annotation.Transformer
import org.springframework.integration.support.MessageBuilder
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class DLXInterceptor {

	@Transformer
	Message<String> Message(Message<String> message){
		
		println "Intercepted: $message.payload.failedMessage.headers"
		
		if(message.payload.failedMessage.headers.retry){
			MessageBuilder
			.withPayload(message.payload.failedMessage.payload)
			.copyHeadersIfAbsent(message.payload.failedMessage.headers)
			.setHeader("retry", message.payload.failedMessage.headers.retry + 1).build()
		}else{
			MessageBuilder
			.withPayload(message.payload.failedMessage.payload)
			.copyHeadersIfAbsent(message.payload.failedMessage.headers)
			.setHeaderIfAbsent("retry", 1).build()
		}
	}
}
