package com.chz.usellm;

import cn.hutool.core.convert.ConvertException;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@UtilityClass
public class OpenAIAPI {

    //api，适用代理api，原api需要科学上网，发送请求超时得不到响应
    //String chatEndpoint = "https://api.openai.com/v1/chat/completions";
    String chatEndpoint = "https://api.openai-proxy.com/v1/chat/completions";
    //key
    String apiKey = "Bearer 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";

    /**
     * 发送消息
     * @param txt 输入的内容
     */
    public String chat(String txt) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("model", "gpt-3.5-turbo");
        List<Map<String, String>> dataList = new ArrayList<>();
        dataList.add(new HashMap<String, String>(){{
            put("role", "user");
            put("content", txt);
        }});
        paramMap.put("messages", dataList);
        JSONObject message = null;
        try {
            String body = HttpRequest.post(chatEndpoint)
                    .header("Authorization", apiKey)
                    .header("Content-Type", "application/json")
                    .body(JsonUtils.toJson(paramMap))
                    .execute()
                    .body();
            JSONObject jsonObject = JSONUtil.parseObj(body);
            JSONArray choices = jsonObject.getJSONArray("choices");
            JSONObject result = choices.get(0, JSONObject.class, Boolean.TRUE);
            message = result.getJSONObject("message");
        } catch (HttpException e) {
            return "出现了异常";
        } catch (ConvertException e) {
            return "出现了异常";
        }
        return message.getStr("content");
    }

    public static void main(String[] args) {
        System.out.println(chat("以下我描述学生的各项属性，请根据属性写出小学生的学期评语，不超过300字。 针对每个属性,以第三人称,为张三给出综合的评价和建议,一共不超过300字。评语需要温和，鼓励，不过激，要委婉。不出现分数的描述，不出现属性的名称。每个属性分值0-100,0分最低，100最高。张三，勤奋0分,聪明0分,课外活动0分，卫生0分,课堂参与0分,课后作业0分，自我管理0分。"));
    }
}
