package com.myproject.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {

	public static String doGet(String url, Map<String, String> params) {
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = doGet(url, params, CHARSET);
			if (httpResponse == null) {
				return null;
			}
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, CHARSET);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeHttpResponse(httpResponse);
		}
		return null;
	}

	public static String doPost(String url, Map<String, String> params) {
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = doPost(url, params, CHARSET);
			if (httpResponse == null) {
				return null;
			}
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				return EntityUtils.toString(entity, CHARSET);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeHttpResponse(httpResponse);
		}
		return null;
	}

	public static void downLoadByGet(String url, Map<String, String> params, File file) {
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = doGet(url, params, CHARSET);
			if (httpResponse == null) {
				return;
			}
			HttpEntity entity = httpResponse.getEntity();
			FileUtils.copyInputStreamToFile(entity.getContent(), file);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeHttpResponse(httpResponse);
		}
	}

	public static void downLoadByPost(String url, Map<String, String> params, File file) {
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = doPost(url, params, CHARSET);
			if (httpResponse == null) {
				return;
			}
			HttpEntity entity = httpResponse.getEntity();
			FileUtils.copyInputStreamToFile(entity.getContent(), file);
		} catch (UnsupportedOperationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeHttpResponse(httpResponse);
		}
	}

	// 超时时间
	private static final int CONNECTIONREQUEST_TIMEOUT = 60000;
	private static final int CONNECT_TIMEOUT = 60000;
	private static final int SOCKET_TIMEOUT = 60000;

	// 连接池最大连接数
	private static final int CONN_MANAGER_MAX_TOTAL = 10;

	// 字符集
	public static final String CHARSET = "UTF-8";

	// 连接池管理器
	private static PoolingHttpClientConnectionManager connManager = null;

	// 连接池初始化
	static {
		LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(createIgnoreVerifySSL());
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();
		connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
		connManager.setMaxTotal(CONN_MANAGER_MAX_TOTAL);
		connManager.setDefaultMaxPerRoute(CONN_MANAGER_MAX_TOTAL);
	}

	// ssl绕过
	private static SSLContext createIgnoreVerifySSL() {
		SSLContext sslContext = null;
		// 1、
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				@Override
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		// 2、
		// try {
		// sslContext = SSLContext.getDefault();
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// }

		// 3、
		// try {
		// sslContext = SSLContext.getInstance("SSLv3");
		// // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
		// X509TrustManager trustManager = new X509TrustManager() {
		// @Override
		// public void checkClientTrusted(java.security.cert.X509Certificate[]
		// paramArrayOfX509Certificate,
		// String paramString) throws CertificateException {
		// }
		// @Override
		// public void checkServerTrusted(java.security.cert.X509Certificate[]
		// paramArrayOfX509Certificate,
		// String paramString) throws CertificateException {
		// }
		// @Override
		// public java.security.cert.X509Certificate[] getAcceptedIssuers() {
		// return null;
		// }
		// };
		// sslContext.init(null, new TrustManager[] { trustManager }, null);
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// } catch (KeyManagementException e) {
		// e.printStackTrace();
		// }

		return sslContext;
	}

	// 请求参数初始化
	private static void config(HttpRequestBase httpRequestBase) {
		initHttpRequest(httpRequestBase);
		// 配置请求的超时设置
		RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(CONNECTIONREQUEST_TIMEOUT)
				.setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
		httpRequestBase.setConfig(requestConfig);
	}

	private static void initHttpRequest(HttpRequestBase httpRequestBase) {
		// httpRequestBase.setHeader("Accept",
		// "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		// httpRequestBase.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		// httpRequestBase.setHeader("Accept-Language",
		// "zh-CN,zh;q=0.8,en-US;q=0.6,en;q=0.4");
		// httpRequestBase.setHeader("Cache-Control", "max-age=0");
		// httpRequestBase.setHeader("Connection", "keep-alive");
		// httpRequestBase.setHeader("Content-Type", "text/xml;charset=utf-8");
		// httpRequestBase.setHeader("Cookie", "");
		// httpRequestBase.setHeader("Host", "180.97.33.90");
		// httpRequestBase.setHeader("Upgrade-Insecure-Requests", "1");
		// httpRequestBase.setHeader("User-Agent",
		// "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Ubuntu Chromium/51.0.2704.79 Chrome/51.0.2704.79 Safari/537.36");
	}

	// 获取HttpClient
	private static synchronized CloseableHttpClient getHttpClient() {
		CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connManager).build();
		return httpClient;
	}

	// 关闭HttpResponse
	private static void closeHttpResponse(CloseableHttpResponse response) {
		try {
			if (response != null) {
				EntityUtils.consume(response.getEntity());
				response.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static CloseableHttpResponse doGet(String url, Map<String, String> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpResponse response = null;
		List<NameValuePair> pairs = setHttpParams(params);
		try {
			if (pairs != null) {
				// 将请求参数和url进行拼接
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			config(httpGet);
			response = getHttpClient().execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static CloseableHttpResponse doPost(String url, Map<String, String> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		CloseableHttpResponse response = null;
		List<NameValuePair> pairs = setHttpParams(params);
		try {
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			response = getHttpClient().execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
		if (paramMap == null || paramMap.isEmpty()) {
			return null;
		}
		List<NameValuePair> formparams = new ArrayList<>();
		for (Map.Entry<String, String> entry : paramMap.entrySet()) {
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return formparams;
	}

}