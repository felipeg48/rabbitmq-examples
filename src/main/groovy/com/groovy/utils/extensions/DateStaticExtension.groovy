package com.groovy.utils.extensions

class DateStaticExtension {

	static String now(Date self,String format='%tD %<tT'){
		String.format(format, new Date())
	}
}
