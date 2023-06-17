package com.chz.usellm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/chatgpt")
public class ChatGPTServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String CHATGPT_API_ENDPOINT = "https://api.openai.com/v1/chat";
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            // 获取输入的分数值
            int score = Integer.parseInt(request.getParameter("score"));
            // 调用 ChatGPT API
            String result = callChatGPTAPI(score);
            // 将结果作为文本返回给 web 页面
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write(result);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    private String callChatGPTAPI(int score) throws Exception {
        // 构建请求 URL
        String urlStr = CHATGPT_API_ENDPOINT + "?model=davinci&prompt=My score is " + score + ".";
        URL url = new URL(urlStr);
        // 发送 HTTP GET 请求
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "Bearer {YOUR_API_KEY}");
        // 读取响应结果
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();
        // 解析响应结果并返回
        return response.toString();
    }
}