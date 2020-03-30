package com.jayer.vhr.controller.system;

import com.jayer.vhr.config.VerificationCode;
import com.jayer.vhr.model.ResBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @GetMapping("/login")
    public ResBean login(){
        return ResBean.error("未登录，请先登录");
    }
    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        session.setAttribute("verify_code", text);
        code.output(image,resp.getOutputStream());

    }
}
