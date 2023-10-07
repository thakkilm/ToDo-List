package com.practice.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SayHelloController {

	
	@RequestMapping("say-hello")
	@ResponseBody
	public String sayHello() {
		return "Hello World";
	}
	
	
	@RequestMapping("say-hello-html")
	@ResponseBody
	public String sayHelloHtml() {
		StringBuffer htmlBuffer = new StringBuffer();
        
        // Append the HTML content to the StringBuffer
        htmlBuffer.append("<html>\n");
        htmlBuffer.append("<head>\n");
        htmlBuffer.append("<title>My Basic HTML Page</title>\n");
        htmlBuffer.append("</head>\n");
        htmlBuffer.append("<body>\n");
        htmlBuffer.append("<h1>Welcome to My Basic HTML Page</h1>\n");
        htmlBuffer.append("<p>This is a simple HTML page generated using Java StringBuffer.</p>\n");
        htmlBuffer.append("</body>\n");
        htmlBuffer.append("</html>\n");
		return htmlBuffer.toString();
	}
	
	@RequestMapping("say-hello-jsp")
	public String sayHelloJsp() {
		return "sayHello";
	}

}
