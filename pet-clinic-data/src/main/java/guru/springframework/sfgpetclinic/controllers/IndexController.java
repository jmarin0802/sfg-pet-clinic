package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

	@RequestMapping({"","/","index","index.html"})
	public String index() {
		return "index";
	}
}
