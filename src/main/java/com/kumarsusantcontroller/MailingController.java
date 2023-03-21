package com.kumarsusantcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kumarsusant.utils.EmailUtil;

@RestController
@RequestMapping("/mail")
public class MailingController {
	@GetMapping("/send")
	public String sendMail(@RequestBody MailModel mailModel) {
		String st = mailModel.mailTo();
		return EmailUtil.sendMail("SpringBootMailSebding Project", mailModel.message(), mailModel.mailTo())
				? "Mail Send to " + mailModel.mailTo()
				: "Mail Sent Failed";
	}
	@GetMapping("/sendg")
	public String sendMailT() {
		
		return EmailUtil.sendMail("SpringBootMailSebding Project","Hii Bro Demo MailProject","susant.k@healthsignz.com")
				? "Mail Send to " + "susant.k@healthsignz.com"
				: "Mail Sent Failed";
	}
	public static void main(String[] args) {
		System.out.println(new MailingController().sendMailT());;
	}
}
