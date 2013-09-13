/**
 * 
 */
package com.rabbit.spring.integration.examples

import org.springframework.integration.Message
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class DLXConsumer {

	@ServiceActivator
	String handler(Message<String> message) throws Exception{
		println "Consuming: $message"
		throw new Exception("Not working")
	}
}
