package org.act.arch.client.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.act.arch.client.security.HTTPClient;
import org.act.arch.client.security.TokenManager;
import org.apache.http.HttpResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	private HTTPClient httpClient;

	public HomeController() {
		httpClient = new HTTPClient();
	}

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("learningObjects", getLearningObjects());
		return mv;
	}

	private String getLearningObjects() {
		TokenManager tm = new TokenManager();
		return getLearningObjects(tm.getToken("admin", "admin")
				.getAccessToken());
	}

	public String getLearningObjects(String token) {
		String submitUrl = "http://localhost:8280/rest/1.0.0/learningObjectives";
		try {
			HttpResponse httpResponse = httpClient.doGet(submitUrl, "Bearer "
					+ token);
			return httpClient.getResponsePayload(httpResponse);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
