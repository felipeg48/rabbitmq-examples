/**
 * 
 */
package com.rabbit.groovy.examples


/**
 * @author fgutierrezcru
 *
 */
class Producer extends RabbitConnection {
	
	void send(opts){
		channel.basicPublish opts.exchange,opts.routingkey, opts.msgproperties, opts.msg
	}
	
	
}
