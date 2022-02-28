package com.gilsonbraggion.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class MainController {

	@GetMapping
	public String main() {
		return "index";
	}

	@GetMapping("/user")
	public String user(@AuthenticationPrincipal OAuth2User principal, Model model) {
		String url = principal.getAttribute("url");
		String provedor = "";
		
		if(url != null && url.contains("git")) {
			provedor = "GitHub";
		} else {
			provedor = "Google";
		}
		
		model.addAttribute("provedor", provedor);
		
		if (provedor.equalsIgnoreCase("GitHub")) {
			model.addAttribute("login", principal.getAttribute("login"));
			model.addAttribute("id", principal.getAttribute("id"));
			model.addAttribute("avatar_url", principal.getAttribute("avatar_url"));
			model.addAttribute("url", principal.getAttribute("url"));
			model.addAttribute("type", principal.getAttribute("type"));
			model.addAttribute("name", principal.getAttribute("name"));
			model.addAttribute("company", principal.getAttribute("company"));
			model.addAttribute("email", principal.getAttribute("email"));
			model.addAttribute("followers", principal.getAttribute("followers"));
		} else if (provedor.equalsIgnoreCase("Google")){
			model.addAttribute("name", principal.getAttribute("name"));
			model.addAttribute("given_name", principal.getAttribute("given_name"));
			model.addAttribute("family_name", principal.getAttribute("family_name"));
			model.addAttribute("email", principal.getAttribute("email"));
		}

		return "user/user";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied(Model model) {
		
		model.addAttribute("accessDenied", true);
		
		return "index";
	}

}
