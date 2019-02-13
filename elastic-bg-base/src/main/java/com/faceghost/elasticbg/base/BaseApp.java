package com.faceghost.elasticbg.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseApp {

	public static  final  String RPC_SERVER = "ElasticBgRpc";

	static {
		log.info("------------------- Elastic-Bg 公共配置初始化 begin -------------------");
		log.info("Rpc-Server-Name : " + RPC_SERVER);
		log.info("------------------- Elastic-Bg 公共配置初始化 end -------------------");
	}
}
