package epam.jmp.muha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@ExceptionHandler(Exception.class)
	public ModelAndView handleIOException(Exception exception) {
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("message", exception.getMessage());
		return modelAndView;

	}
}