/**
 * 
 */
package com.rabbit.groovy.examples

import com.rabbitmq.client.GetResponse

/**
 * @author fgutierrezcru
 *
 */
class Consumer extends RabbitConnection {

	def consume(opts){
		GetResponse response = channel.basicGet(opts.queue,opts.ack) 
		if(!opts.ack){
			println "Ack is false, doing manual Ack"
			
			if(opts.reject && !opts.deadletter){
				println "Emulate failing..."
				println "Rejecting the message: ${response.body.toReadable()}"
				channel.basicReject(response.envelope.deliveryTag, opts.requeue?:false)
				println "Message rejected"
			}else if ( opts.deadletter){
				println "Emulate failing..."
				println "Sending to a DeadLetter: ${response.body.toReadable()}"
				channel.basicPublish(opts.deadletterex, "", opts.props, response.body)
				channel.basicReject(response.envelope.deliveryTag, false)
				println "Message rejected"
			}else{
				println response.getBody().toReadable()
				channel.basicAck(response.envelope.deliveryTag, false)
				println "Ack done"
			}
		}
		
	}
}
