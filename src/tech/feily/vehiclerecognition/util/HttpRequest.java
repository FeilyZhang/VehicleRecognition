package tech.feily.vehiclerecognition.util;

import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStreamReader;  
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;  
import java.net.HttpURLConnection;  
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL; 
import java.util.Date;
import java.util.List;
import java.util.Map;

public class HttpRequest {
	
    static boolean proxySet = false;
    static String proxyHost = "127.0.0.1";
    static int proxyPort = 8087;

    public static String urlEncode(String source,String encode) throws IOException {  
        String result = source;  
        try {  
            result = java.net.URLEncoder.encode(source,encode);  
        } catch (UnsupportedEncodingException e) {  
			new OperateFile().write(Util.ERROR_PATH, new Date() + "\t" + "Fail to urlEncode" + "\r\n",true);
            e.printStackTrace();  
            return "0";  
        }  
        return result;  
    }

    public static HttpResultBean sendGet(String url, String param) throws IOException {
        String result = "", line;
        Map<String, List<String>> map = null;
        BufferedReader br = null;
        HttpResultBean httpResult = new HttpResultBean();
        try {
            String urlNameString = url + "?" + param;
            HttpURLConnection connection = (HttpURLConnection) new URL(urlNameString).openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            map = connection.getHeaderFields();
            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            while ((line = br.readLine()) != null) result += line;
        } catch (Exception e) {
			new OperateFile().write(Util.ERROR_PATH, new Date() + "\t" + "Fail to send request of get" + "\r\n", true);
            e.printStackTrace();
        } finally {
            if (br != null) br.close();
        }
        if (map !=null && "".equals(result)) httpResult.setFlag(true);
        else  httpResult.setFlag(true);
        httpResult.setResult(result);
        httpResult.setMap(map);
        return httpResult;
    }


    @SuppressWarnings("static-access")
	public static String sendPost(String url, String param,boolean isproxy) throws IOException {
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        String result = "";
        try {
        	URL finalurl = new URL(url);
            HttpURLConnection conn = null;
            if(isproxy){
                Proxy proxy = new Proxy(Proxy.Type.DIRECT.HTTP, new InetSocketAddress(proxyHost, proxyPort));
                conn = (HttpURLConnection) finalurl.openConnection(proxy);
            }else conn = (HttpURLConnection) finalurl.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            osw = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            osw.write(param);
            osw.flush();
            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = br.readLine()) != null) result += line;
        } catch (Exception e) {
			new OperateFile().write(Util.ERROR_PATH, new Date() + "\t" + "Fail to send request of post" + "\r\n",true);
            e.printStackTrace();
        } finally {
            if(osw!=null) osw.close();
            if(br!=null) br.close();
        }
        return result;
    }    

}