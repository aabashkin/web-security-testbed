package mycompany.myproducts.websecuritytestbed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CodeInjectionController {
	
	private static final Logger logger = LoggerFactory.getLogger(CodeInjectionController.class);

	@RequestMapping(value = "/code-injection/spring-eval-jsp")
	public String codeInjectionSpring(@RequestParam String input) {
		return "spring-eval";
	}

}
