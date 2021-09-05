package org.vincentyeh.http_util.example;

import org.vincentyeh.http_util.net.client.concrete.utils.LocalProxyHttpClientUtil;
import org.vincentyeh.http_util.net.client.framework.connection.Response;
import org.vincentyeh.http_util.net.client.framework.utils.HttpClientUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResponseExample {

    public static void main(String[] args) throws Exception {
        HttpClientUtil util=new LocalProxyHttpClientUtil();
        Response response=util.get(new URL("https://httpbin.org/anything"),null,2000,null);
        BufferedReader reader=new BufferedReader(new InputStreamReader(response.getBodyInputStream(), StandardCharsets.UTF_8));

        String buf;
        while ((buf=reader.readLine())!=null){
            System.out.println(buf);
        }
    }
}
