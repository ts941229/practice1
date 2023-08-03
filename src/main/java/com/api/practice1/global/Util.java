package com.api.practice1.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private Util() {}
	
	private static Util util;
	
	public static Util getInstance() {
		if(util==null) {
			util = new Util();
		}
		return util;
	}
	
	// Date 포맷 ex) 2023-08-03
	public String dateFormat(Date date) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		return sdf1.format(date);
	}
	
}
