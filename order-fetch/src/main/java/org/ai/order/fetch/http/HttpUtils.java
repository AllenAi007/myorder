package org.ai.order.fetch.http;

import org.ai.order.fetch.exception.LoginFailedException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hua.ai on 2016/4/4.
 */
public class HttpUtils {

    private final static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    private String successCode = "登录成功";
    private String charset = "gb2312";

    private HttpContext context;
    private CloseableHttpClient httpclient;

    public HttpUtils() {
        httpclient = HttpClients.createDefault();
        context = HttpClientContext.create();
    }

    public String login(String url, String username, String password) throws IOException, LoginFailedException {
        List<NameValuePair> params = new ArrayList();
        params.add(new BasicNameValuePair("action", "login"));
        params.add(new BasicNameValuePair("username", username));
        params.add(new BasicNameValuePair("password", password));
        String result = post(url, params);
        // print result
        if (LOG.isDebugEnabled()) {
            LOG.debug("Result -- > {}", result);
        }
        if (!StringUtils.isBlank(result) && result.contains(successCode)) {
            return "success";
        }
        throw new LoginFailedException("Login failed," + result);
    }

    /**
     * HTTP GET
     *
     * @param url
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpget, context);
        try {
            HttpEntity entity = response.getEntity();
            return getString(entity);
        } finally {
            if (response != null) response.close();
        }
    }

    /**
     * HTTP POST
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public String post(String url, List<NameValuePair> params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        if (params != null && !params.isEmpty()) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, Charset.forName(charset));
            httppost.setEntity(entity);
        }
        CloseableHttpResponse response = httpclient.execute(httppost, context);
        try {
            HttpEntity httpEntity = response.getEntity();
            return getString(httpEntity);
        } finally {
            if (response != null) response.close();
        }
    }


    /**
     * Get http result
     *
     * @param httpEntity
     * @return
     * @throws IOException
     */
    public String getString(HttpEntity httpEntity) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (httpEntity != null) {
            long len = httpEntity.getContentLength();
            if (len != -1 && len < 2048) {
                sb.append(EntityUtils.toString(httpEntity, charset));
            } else {
                InputStream instream = httpEntity.getContent();
                try {
                    byte[] b;
                    while (true) {
                        b = new byte[512];
                        int i = instream.read(b);
                        sb.append(new String(b, charset));
                        if (i == -1) {
                            break;
                        }
                    }
                } finally {
                    instream.close();
                }
            }
        }
        return sb.toString();
    }

}
