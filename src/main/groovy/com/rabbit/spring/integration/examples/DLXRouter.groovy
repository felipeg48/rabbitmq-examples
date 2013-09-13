/**
 * 
 */
package com.rabbit.spring.integration.examples

import org.springframework.integration.Message
import org.springframework.stereotype.Component

/**
 * @author fgutierrezcru
 *
 */
@Component
class DLXRouter {

	String process(Message<String> message) throws Exception{
		println "Processing...."
		String flag = "tryAgain"
		if(message.payload.failedMessage.headers.retry){
			if(message.payload.failedMessage.headers.retry > 3){
				flag = "bye"
			}
		}
		println "Processing: $flag"
		flag
	}

}
