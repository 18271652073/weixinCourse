package com.nbdeli.test.controller;

import com.nbdeli.test.common.utils.SignUtil;
import com.nbdeli.test.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Dukaixiang
 * @date 2019/4/24.
 * @Version 1.0
 */
@RestController
public class LoginController {

    @Autowired
    private CoreService coreService;

    @RequestMapping(value = "/toke", method = RequestMethod.GET)
    public String token(@RequestParam(required = false, defaultValue = "") String signature,
                        @RequestParam(required = false, defaultValue = "") String timestamp,
                        @RequestParam(required = false, defaultValue = "") String nonce,
                        @RequestParam(required = false, defaultValue = "") String echostr) {
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "/toke", method = RequestMethod.POST)
    public void token(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 调用核心业务类接收消息、处理消息
        String respMessage = coreService.processRequest(request);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
