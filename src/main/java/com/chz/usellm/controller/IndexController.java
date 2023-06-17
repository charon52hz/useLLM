package com.chz.usellm.controller;

import com.chz.usellm.OpenAIAPI;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//http://localhost:8088/
@Controller
public class IndexController {
    @GetMapping("/input")
    public String get(@RequestParam String stuName,
                      @RequestParam int qf,
                      @RequestParam int cm,
                      @RequestParam int kwhd,
                      @RequestParam int ws,
                      @RequestParam int ktcy,
                      @RequestParam int khzy,
                      @RequestParam int zwgl,
                      Model model){
//        int qf = request.getQf();
//        int cm = request.getCm();
//        int kwhd = request.getKwhd();
//        int ws = request.getWs();
//        int ktcy = request.getKtcy();
//        int khzy = request.getKhzy();
//        int zwgl = request.getZwgl();


        String content = OpenAIAPI.chat(
                "1、以下我将描述某个学生的各项属性和属性分值，表示为[属性: 分值]；"+
        "2、请根据属性写出该学生的学期评语，对每个属性要给出评语和建议，没有分数的不用评价；"+
        "3、你生成的评语和建议中不能出现[属性]的文字；"+
        "4、评语一定要委婉；"+
        "5、建议一定要鼓励；"+
        "6、评价规则，[分值]的范围是最低0，最高100，越大越好。高于80分评语要与优秀相关，低于30分评语与不合格相关，30-50分评语与合格、需改进相关，50-80之间评语与良好相关；"+
        "7、学生"+stuName+"各项属如下:" +
                                "[勤奋:"+qf+"]," +
                                "[聪明:"+cm+"],"+
                                "[课外活动:"+kwhd+"],"+
                                "[卫生:"+ws+"],"+
                                "[课堂参与:"+ktcy+"],"+
                                "[课后作业:"+khzy+"],"+
                                "[自我管理:"+zwgl+"]。"+
         "8、你的回答应按照json格式返回，分别每个属性给出一个评语，并在最后给出[综合评价]和[建议]。"+
         "9、请注意你的评价需要与评价规则一致。"
        );
        System.out.println(stuName+":勤奋："+qf+" 聪明："+cm+" 课外活动："+kwhd+" 卫生"+ws+" 课堂参与"+ktcy+" 课后作业"+khzy+" 自我管理"+zwgl);
        System.out.println(content);
        model.addAttribute("content",content);
        model.addAttribute("stuName",stuName);
        model.addAttribute("qf",qf);
        model.addAttribute("cm",cm);
        model.addAttribute("ktcy",ktcy);
        model.addAttribute("kwhd",kwhd);
        model.addAttribute("ws",ws);
        model.addAttribute("khzy",khzy);
        model.addAttribute("zwgl",zwgl);
        return "index";

    }

}
