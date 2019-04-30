package cn.stylefeng.guns.core.util;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClien工具类
 * 
 * @author wwm
 * @date: 2016-06-14 20:50:11
 * @version v1.0
 */
@SuppressWarnings("deprecation")
public class HttpClientUtil {
	private static final int REQUEST_TIMEOUT = 60000;
	private static final int SOCKET_TIMEOUT = 60000;
	private static final int CONNECT_TIMEOUT = 60000;
	/**
	 * 发送Get请求，包含参数
	 * 
	 * @param url
	 * @param param
	 *            可以为空
	 * @return
	 */
	public static String doGet(String url, Map<String, String> param,int requestTimeout,int socketTimeout,int connectTimeout) {
		// 创建Httpclient对象
		CloseableHttpClient httpclient = HttpClientBuilder.create().setConnectionManager(getConnectManager())
				.setDefaultRequestConfig(getRequestConfig(requestTimeout,socketTimeout,connectTimeout)).build();

		String resultString = "";
		CloseableHttpResponse response = null;
		try {
			// 创建uri
			URIBuilder builder = new URIBuilder(url);
			if (param != null) {
				for (String key : param.keySet()) {
					builder.addParameter(key, param.get(key));
				}
			}
			URI uri = builder.build();

			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);

			// 执行请求
			response = httpclient.execute(httpGet);
			// 判断返回状态是否为200
			if (response.getStatusLine().getStatusCode() == 200) {
				resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	/**
	 * 发送GET请求，无参版
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url,int requestTimeout,int socketTimeout,int connectTimeout) {
		return doGet(url, null, requestTimeout, socketTimeout, connectTimeout);
	}
	
	public static String doGet(String url) {
		return doGet(url, null, REQUEST_TIMEOUT, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
	}

	/**
	 * 发送POST请求，参数以MAP形式传递
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static String doPost(String url, Map<String, String> param,int requestTimeout,int socketTimeout,int connectTimeout) throws ParseException, IOException {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(getConnectManager())
				.setDefaultRequestConfig(getRequestConfig(requestTimeout,socketTimeout,connectTimeout)).build();
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			// 创建参数列表
			if (param != null) {
				List<NameValuePair> paramList = new ArrayList<>();
				for (String key : param.keySet()) {
					paramList.add(new BasicNameValuePair(key, param.get(key)));
				}
				// 模拟表单
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "utf-8");
				httpPost.setEntity(entity);
			}
			// 执行http请求
			response = httpClient.execute(httpPost);
			resultString = EntityUtils.toString(response.getEntity(), "utf-8");
		} finally {
			try {
				if (null != response) {
					response.close();
				}
			} catch (IOException e) {
			}
		}
		return resultString;
	}
	
	/**
	 * 发送POST请求，参数以MAP形式传递
	 * 
	 * @param url
	 * @param param
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static String doPost(String url, Map<String, String> param) throws ParseException, IOException {
		return doPost(url, param, REQUEST_TIMEOUT, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
	}

	/**
	 * 发送POST请求，不带参数
	 * 
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static String doPost(String url,int requestTimeout,int socketTimeout,int connectTimeout) throws ParseException, IOException {
		return doPost(url, null,requestTimeout,socketTimeout,connectTimeout);
	}

	/**
	 * 发送POST请求，参数以JSON形式传递
	 * 
	 * @param url
	 * @param json
	 * @return
	 */
	public static String doPostJson(String url, String json,int requestTimeout,int socketTimeout,int connectTimeout) throws RuntimeException {
		// 创建Httpclient对象
		CloseableHttpClient httpClient = null;
		CloseableHttpResponse response = null;
		String resultString = "";
		try {
			// 创建Http Post请求
			HttpPost httpPost = new HttpPost(url);
			HttpEntity entity = ("".equals(json)) ? new StringEntity(json, ContentType.APPLICATION_FORM_URLENCODED)
					: new StringEntity(json, ContentType.APPLICATION_JSON);
			httpPost.setEntity(entity);

			URI uri = new URL(url).toURI();
			if ("https".equals(uri.getScheme())) {
				entity = HttpClientUtil.createSSLClient(httpPost,requestTimeout,socketTimeout,connectTimeout);
			} else {
				httpClient = HttpClientBuilder.create().setConnectionManager(getConnectManager()).setDefaultRequestConfig(getRequestConfig(requestTimeout,socketTimeout,connectTimeout)).build();
				response = httpClient.execute(httpPost);// 执行http请求
				entity = response.getEntity();
			}
			if (entity != null) {
				resultString = EntityUtils.toString(entity, "utf-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("调用错误，原因：" + e.getMessage());
		} finally {
			try {
				if (response != null) {
					response.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return resultString;
	}
	
	public static String doPostJson(String url, String json) throws RuntimeException {
		return doPostJson(url, json, REQUEST_TIMEOUT, SOCKET_TIMEOUT, CONNECT_TIMEOUT);
	}

	private static PoolingHttpClientConnectionManager getConnectManager() {
		PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
		connectionManager.setMaxTotal(500);
		connectionManager.setDefaultMaxPerRoute(500);
		return connectionManager;
	}

	private static RequestConfig getRequestConfig(int requestTimeout,int socketTimeout,int connectTimeout) {
		return RequestConfig.custom().setConnectionRequestTimeout(requestTimeout).setSocketTimeout(socketTimeout)
				.setConnectTimeout(connectTimeout).build();
	}

	public static HttpEntity createSSLClient(HttpPost httpPost,int requestTimeout,int socketTimeout,int connectTimeout) throws GeneralSecurityException {
		HttpClient httpClient = HttpClientBuilder.create().setConnectionManager(getConnectManager())
				.setDefaultRequestConfig(getRequestConfig(requestTimeout,socketTimeout,connectTimeout)).build();
		HttpEntity entity = null;
		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] xcs, String string) {
			}

			public void checkServerTrusted(X509Certificate[] xcs, String string) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx);
		ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", ssf, 443));
		try {
			HttpResponse response = httpClient.execute(httpPost);
			entity = response.getEntity(); // 获取响应实体
		} catch (IOException e) {
			throw new GeneralSecurityException("找不到服务器！");
		}
		return entity;
	}
}
