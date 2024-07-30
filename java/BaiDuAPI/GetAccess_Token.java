package BaiDuAPI;

import com.alibaba.fastjson2.JSONObject;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class GetAccess_Token {
    private static final String grant_type = "client_credentials";
    private static final String client_id = "ZlFZMztKDCDGO1tmgnD3MvvH";
    private static final String client_secret = "cZiRDllQmTul5BeaxBMnbt0k8GDIEPAZ";
    private static final String url = "https://aip.baidubce.com/oauth/2.0/token";
    private static final String realUrl = url + "?grant_type=" + grant_type + "&client_id=" + client_id + "&client_secret=" + client_secret;
    public String getAccessToken() throws IOException, ParseException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建HttpPost请求
        HttpPost postRequest = new HttpPost(realUrl);
        // 执行请求
        ClassicHttpResponse response = httpClient.execute(postRequest);
        // 获取响应实体
        HttpEntity entity = response.getEntity();
        // 将响应实体转换为字符串
        String json = EntityUtils.toString(entity);
        // 将字符串转换为JSONObject对象
        JSONObject object = JSONObject.parseObject(json);
        // 获取access_token
        String access_token = object.getString("access_token");
        // 返回access_token
        return access_token;
    }
}
