package com.huifer.source.spring.http;

import java.net.URI;
import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.FileCopyUtils;

/**
 * {@link SimpleClientHttpRequestFactory}
 */
public class TestSimpleClientHttpRequestFactorySourceCode {
    public static void main(String[] args) throws Exception {
        SimpleClientHttpRequestFactory clientFactory = new SimpleClientHttpRequestFactory();

        clientFactory.setConnectTimeout(5000);
        clientFactory.setReadTimeout(5000);

        ClientHttpRequest client = clientFactory.createRequest(URI.create("https://www.baidu.com"), HttpMethod.GET);
        ClientHttpResponse response = client.execute();
        System.out.println(response);
        byte[] bytes = FileCopyUtils.copyToByteArray(response.getBody());
        // 输出结果
        System.out.println(new String(bytes, StandardCharsets.UTF_8));

    }
}
