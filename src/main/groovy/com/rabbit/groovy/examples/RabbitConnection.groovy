/**
 * 
 */
package com.rabbit.groovy.examples

import com.rabbitmq.client.Channel
import com.rabbitmq.client.Connection
import com.rabbitmq.client.ConnectionFactory

/**
 * @author fgutierrezcru
 *
 */
class RabbitConnection {

	protected ConnectionFactory factory
	protected Connection connection
	protected Channel channel
	
	RabbitConnection(opts){
		if(opts.who)
			println opts.who
		
		factory = new ConnectionFactory()
		factory.host = "localhost"
		
		
		if(opts.host){
			println "Connecting to host: ${opts.host}"
			factory.host = opts.host
		}
		
		if(opts.port){
			println "Connecting to port: ${opts.port}"
			factory.port = opts.port
		}
		
		if(opts.ssl_no_context){
			factory.port = opts.port
			factory.useSslProtocol()
		}
		if(opts.ssl_context){
			factory.port = opts.port
			factory.useSslProtocol(opts.ssl_context_provider)
		}

		connection = factory.newConnection()
		channel = connection.createChannel()
	}
	
	void shutdown(){
		channel.close()
		connection.close()
	}
}
