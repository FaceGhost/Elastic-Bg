package com.faceghost.elasticbg.base.utils.ip;


public class IPTest {

	public static void main(String[] args) {
		IPSeeker seeker = IPSeeker.getInstance();
		String ip ="101.81.136.143";
        System.out.println(seeker.getAddress(ip));
	}
}
