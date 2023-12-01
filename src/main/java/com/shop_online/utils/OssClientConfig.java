package com.shop_online.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author ycshang
 * @Date 2023/7/12
 * @Description TODO
 */
@Configuration
public class OssClientConfig {
	@Value("${aliyun.oss.endpoint}")
	String endpoint;
	@Value("${aliyun.oss.accessKeyId}")
	String accessKeyId;
	@Value("${aliyun.oss.accessKeySecret}")
	String accessKeySecret;

	@Bean
	public OSSClient createOssClient() {
		return (OSSClient) new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
	}

}
