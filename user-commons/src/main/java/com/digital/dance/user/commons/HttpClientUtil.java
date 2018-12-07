package com.digital.dance.user.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
/**
 * 
 * @author liwy
 *
 * time:2016年8月22日下午7:28:53
 */
public class HttpClientUtil {
	public static final String CHARSET_UTF_8 = "UTF-8";
	public static final String CHARSET_GBK = "GBK";
	public static final String JSON_STRING = "jsonString";
	
	public static final int HTTP_TIME_OUT = 50000;
	
	  static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();

	    static int TIMEOUT = 50 * 1000;
	    //并发100个请求
	    static int MAX_HTTP_CONNECTION = 100;

	    static {
	        //HttpClient 连接池属性设置，HOST并发数默认为2, 客户端总并发数为100, TimeOut时间为5s.
	        HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();
	        httpConnectionManagerParams.setMaxTotalConnections(MAX_HTTP_CONNECTION);
	        httpConnectionManagerParams.setDefaultMaxConnectionsPerHost(MAX_HTTP_CONNECTION / 2);
	        httpConnectionManagerParams.setSoTimeout(TIMEOUT);
	        httpConnectionManagerParams.setConnectionTimeout(TIMEOUT);
	        connectionManager.setParams(httpConnectionManagerParams);
	    }
	/**
	 * 远程调用
	 * @param url 请求的url
	 * @param map 请求的数据key-value对
	 * @return
	 * @throws IOException
	 */
	public static Object remoteCall(String url, Map<String, String> map) throws IOException {
		return remoteCall(null, url , map);
	}
	
	/**
	 * 远程调用
	 * @param methodType http的get或post类型
	 * @param url 请求的url
	 * @param map 请求的数据key-value对
	 * @return String 返回响应字符串
	 * @throws IOException
	 */
	public static String remoteCall(String methodType, String url, Map<String, String> map) throws IOException {
		
		// HttpMethod
		if ("get".equalsIgnoreCase(methodType)){
			return executeGetMethod(url,map);
		} else {
			return null;//executePostMethod(url,map);
		}
	}
	
	/**
	 * 执行http post方法，返回响应结果
	 * @param url 请求的url
	 * @param map 请求的数据key-value对
	 * @return
	 * @throws IOException
	 */
	public static String executePostMethod(String url, Map<String, String> map) throws IOException {
		String rtnStr = null;
		
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setContentCharset(CHARSET_UTF_8);
		
		if(map != null && !map.isEmpty()){
			if(map.get(JSON_STRING) != null){
				String jsonString = map.get(JSON_STRING);
				StringRequestEntity reqEntity = new StringRequestEntity(jsonString, "application/json", CHARSET_UTF_8);
				postMethod.setRequestEntity(reqEntity);
			}else{
				int size = map.size();
				NameValuePair[] data = new NameValuePair[size];
				Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
				Entry<String, String> entry = null;
				
				for(int i = 0 ; i < size ; i++){
					entry = iterator.next();
					data[i] = new NameValuePair(entry.getKey(), entry.getValue());
				}
				
				// 将表单的值放入postMethod中
				postMethod.setRequestBody(data);
			}
		}
		
		// 执行postMethod
		HttpClient httpClient = new HttpClient(); 
		
		// 链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(HTTP_TIME_OUT);  
		// 读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(HTTP_TIME_OUT);
		
		int statusCode = httpClient.executeMethod(postMethod);

		if (statusCode == HttpStatus.SC_OK) {
			StringBuffer contentBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					postMethod.getResponseBodyAsStream(), postMethod.getResponseCharSet()));

			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contentBuffer.append(inputLine);
			}
			reader.close();
			rtnStr = contentBuffer.toString();
		}
		
		return rtnStr;
	}
	
	public static String executeGetMethodGBK(String url, Map<String, String> map) throws IOException {
		String rtnStr = "";
		
		GetMethod getMethod = new GetMethod(url); 
		getMethod.getParams().setContentCharset(CHARSET_GBK);
		if(map != null && !map.isEmpty()){
			int size = map.size();
			NameValuePair[] data = new NameValuePair[size];
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			Entry<String, String> entry = null;
			
			for(int i = 0 ; i < size ; i++){
				entry = iterator.next();
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
			}
			
			// 将表单的值放入postMethod中
			getMethod.setQueryString(data);
		}
		HttpClient httpClient = new HttpClient();
		// 链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(HTTP_TIME_OUT);  
		// 读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(HTTP_TIME_OUT);
		int statusCode = httpClient.executeMethod(getMethod);
		
		if (statusCode == HttpStatus.SC_OK) {
			StringBuffer contentBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet()));

			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contentBuffer.append(inputLine);
			}
			reader.close();
			rtnStr = contentBuffer.toString();
		}
		
		return rtnStr;
	}
	
	public static String executeGetMethod(String url, Map<String, String> map) throws IOException {
		String rtnStr = "";
		
		GetMethod getMethod = new GetMethod(url); 
		getMethod.getParams().setContentCharset(CHARSET_UTF_8);
		if(map != null && !map.isEmpty()){
			int size = map.size();
			NameValuePair[] data = new NameValuePair[size];
			Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
			Entry<String, String> entry = null;
			
			for(int i = 0 ; i < size ; i++){
				entry = iterator.next();
				data[i] = new NameValuePair(entry.getKey(), entry.getValue());
			}
			
			// 将表单的值放入postMethod中
			getMethod.setQueryString(data);
		}
		HttpClient httpClient = new HttpClient();
		// 链接超时
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(HTTP_TIME_OUT);  
		// 读取超时
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(HTTP_TIME_OUT);
		int statusCode = httpClient.executeMethod(getMethod);
		
		if (statusCode == HttpStatus.SC_OK) {
			StringBuffer contentBuffer = new StringBuffer();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet()));

			String inputLine = null;
			while ((inputLine = reader.readLine()) != null) {
				contentBuffer.append(inputLine);
			}
			reader.close();
			rtnStr = contentBuffer.toString();
		}
		
		return rtnStr;
	}
	
	 public static String sendPost(String url, Map<String, String> headers, Map<String, String> params, String paramString,  String encoding) throws Exception{
	        String ret = null;
	        if (encoding==null)
	            encoding = CHARSET_UTF_8;
	        HttpClient httpClient = new HttpClient(connectionManager);
	        PostMethod method = new PostMethod(url);
	        // 1.设置header
	        //设置默认header
	        //String contentType = "application/x-www-form-urlencoded";
	        // method.setRequestHeader("Content-Type", contentType);
	        if (headers != null && headers.size() > 0) {
	            // 设置自定义header
	            for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
	                method.setRequestHeader(headerEntry.getKey(), headerEntry.getValue());
	            }
	        }
	        //2.设置参数/body
	        if (params != null && params.size() > 0 && paramString == null) {
	            List<NameValuePair> nvList = new ArrayList<NameValuePair>();
	            for (Map.Entry<String, String> paramEntry : params.entrySet()) {
	                nvList.add(new NameValuePair(paramEntry.getKey(), paramEntry.getValue()));
	            }
	            method.setRequestBody(nvList.toArray(new NameValuePair[]{}));
	        } else if (paramString != null) {
				//method.setRequestEntity(new StringRequestEntity(paramString,contentType,encoding));
	        }
	        try {
	            //3.发请求
	            httpClient.executeMethod(method);
	            int state = method.getStatusCode();  
	            if(state != HttpStatus.SC_OK){
	            	throw new Exception("post error,status code :"+state);
	            }else{
	            	//4. 处理请求数据
	                ret = new String(method.getResponseBody(), encoding);
	            }
	            
	        } catch (Exception e) {
	            throw e;
	        } finally {
	            //释放连接
	            method.releaseConnection();
	        }
	        return ret;
	    }
	 
	public static void main(String[] args) {
	    
	}
}
