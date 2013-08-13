/**
 * 
 */
package com.rabbit.groovy.examples


/**
 * @author fgutierrezcru
 *
 */
class Producer extends RabbitConnection {
	
	void send(params){
		channel.basicPublish(params.exchange,params.routingkey, params.msgproperties, params.msg)
	}
	
	
}
