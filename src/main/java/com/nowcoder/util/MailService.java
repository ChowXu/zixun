package com.nowcoder.util;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Map;
import java.util.Properties;

/**
 * Project: news04
 * Author: Chow xi
 * Email: zhouxu_1994@163.com
 * Time: 17/2/4 下午8:36
 */
@Service
public class MailService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private JavaMailSenderImpl mailSender;

    @Autowired
    private VelocityEngine velocityEngine;


    /**
     * @param to
     * @param subject  标题
     * @param template 模板
     * @param model    内容
     * @return
     */
    public boolean sendWithHTMLTemplate(String to, String subject, String template, Map<String, Object> model) {

        try {
            String nick = MimeUtility.encodeText("中级项目");
            InternetAddress from = new InternetAddress(nick + "<zhx19941017@qq.com>");
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            String result = VelocityEngineUtils
                    .mergeTemplateIntoString(velocityEngine, template, "UTF-8", model);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(result, true);
            mailSender.send(mimeMessage);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        mailSender = new JavaMailSenderImpl();
        mailSender.setUsername("zhx19941017@qq.com");
        mailSender.setPassword("oklbxoywnltebhdj");
        mailSender.setHost("smtp.qq.com");
        mailSender.setPort(465);
        mailSender.setProtocol("smtps");
        mailSender.setDefaultEncoding("utf8");
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.ssl.enable", true);
        mailSender.setJavaMailProperties(javaMailProperties);
    }
}
