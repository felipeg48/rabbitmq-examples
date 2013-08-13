package com.groovy.utils.extensions

class DateStaticExtension {

	static String now(Date self){
		String.format('%tT', new Date())
	}
}
