package BaiDuAPI;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ImageRecognition {
     private static final String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
    public String imageRecongintion(String loaclLaction) throws Exception {
        // 创建GetAccess_Token对象，用于获取access_token
        GetAccess_Token getAccessToken = new GetAccess_Token();
        // 获取access_token
        String access_token = getAccessToken.getAccessToken();
        // 读取图片文件，转换为字节数组
        byte[] imageData = FileUtil.readFileByBytes(loaclLaction);
        // 将字节数组转换为base64编码的字符串
        String imgStr = Base64Util.encode(imageData);
        // 对字符串进行URL编码
        String imgParam = URLEncoder.encode(imgStr, StandardCharsets.UTF_8);
        // 构建请求参数
        String param = "image=" + imgParam;
        // 发送POST请求，获取响应
        String json = HttpUtil.post(url, access_token, param);
        // 将响应转换为JSONObject对象
        JSONObject object = JSONObject.parseObject(json);
        // 获取识别结果
        String word_result = object.getString("words_result");
        // 将识别结果转换为JSONArray对象
        JSONArray jsonArray = JSONArray.parseArray(word_result);
        // 获取识别结果中的文本
        String value = jsonArray.getJSONObject(0).getString("words");
        //返回识别结果
        return value;

    }
}
