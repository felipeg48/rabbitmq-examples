/**
 * 
 */
package com.rabbit.spring.integration.examples;

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * @author fgutierrezcru
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/META-INF/spring/simple-context.xml")
class DLXTest {

	@Autowired
	DLXProducer producer
	
	String payload = "Hello World at #date"
	
	@Test
	void producer(){
		1.times { 
			producer.send payload:payload.replace("#date",String.format('%tT',new Date())) 
			sleep 5000
		}
	
	}

}
