package com.chz.usellm;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.unfbx.chatgpt.OpenAiClient;
import com.unfbx.chatgpt.entity.completions.CompletionResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
//        //配置api keys
//        OpenAiClient openAiClient = new OpenAiClient("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");
//        CompletionResponse completions = openAiClient.completions("how are you");
//        Arrays.stream(completions.getChoices()).forEach(System.out::println);
        sendMsg();
    }

    public static void sendMsg() {
        // 消息列表
        List<ChatMessage> list = new ArrayList<>();

        // 给chatGPT定义一个身份，是一个助手
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setRole("system");
        chatMessage.setContent("You are a helpful assistant.");
        list.add(chatMessage);

        // 定义一个用户身份，content是用户写的内容
        ChatMessage userMessage = new ChatMessage();
        userMessage.setRole("user");
        userMessage.setContent("hello");
        list.add(userMessage);

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .messages(list)
                .model("gpt-3.5-turbo")
                .build();
        OpenAiService service = new OpenAiService("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");
//my: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
        // chatCompletion 对象就是chatGPT响应的数据了
        ChatCompletionResult chatCompletion = service.createChatCompletion(request);
        System.out.println(chatCompletion);
    }
}
