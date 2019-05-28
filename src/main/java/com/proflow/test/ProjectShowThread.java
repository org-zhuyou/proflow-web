package com.proflow.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by fancy on 2019/5/28.
 */
public class ProjectShowThread implements Runnable {

    RestTemplate restTemplate = new RestTemplate();

    private String code;

    public ProjectShowThread(String code) {
        this.code = code;
    }

    @Override
    public void run() {

        for(;;) {
            // get
            String id = get();

            try {
                int i = (int)(1+Math.random()*(10-1+1));
                Thread.sleep(i * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // put
            put(id);
            /*
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            */
        }

    }

    private String get() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.put("code", Arrays.asList(code));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        String result = restTemplate.postForObject("http://server.mintree.cn/project/projectShow", requestEntity, String.class);
        JSONObject obj = JSON.parseObject(result);
        System.out.println("获取："+ result);
        return obj.getJSONObject("result").getJSONObject("project").getString("id");
    }

    private void put(String projectId) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.put("code", Arrays.asList(code));
        params.put("projectId", Arrays.asList(projectId));
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        String result = restTemplate.postForObject("http://server.mintree.cn/project/rebackShow", requestEntity, String.class);
        System.out.println("归还："+ projectId);
    }


    public static void main(String[] args) {

        Thread thread1 = new Thread(new ProjectShowThread("0987123"));
        Thread thread2 = new Thread(new ProjectShowThread("0987123"));
        Thread thread3 = new Thread(new ProjectShowThread("0987123"));
        Thread thread4 = new Thread(new ProjectShowThread("0987123"));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }


}
