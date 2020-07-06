package patrick.cai.oidc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CallbackController {
	/*
	 * @RequestMapping("/callback") public ModelAndView index(HttpServletRequest
	 * request) { ModelAndView mav = new ModelAndView();
	 * mav.setViewName("callback");
	 * 
	 * StringBuffer sb = new StringBuffer("Params returned:<br/>"); Map<String,
	 * String[]> params = request.getParameterMap();
	 * 
	 * for(String key : params.keySet()) { sb.append("[" + key + "] = [" +
	 * params.get(key)[0] + "]<br/>"); //map.put(key, params.get(key)[0]);
	 * //map.put("sb", sb.toString()); }
	 * 
	 * System.out.println(sb.toString());
	 * 
	 * mav.addObject("sb", sb.toString());
	 * 
	 * return mav; }
	 */

	@RequestMapping("/callback")
	public String index(Model model, HttpServletRequest request,
			@RequestParam(name = "code", required = false) String code) {

		StringBuffer sb = new StringBuffer("Params:");
		Map<String, String[]> params = request.getParameterMap();

		for (String key : params.keySet()) {
			sb.append("[" + key + "] = [" + params.get(key)[0] + "]");
			// map.put(key, params.get(key)[0]);
			// map.put("sb", sb.toString());
		}

		System.out.println(sb.toString());

		model.addAttribute("sb", sb.toString());
		model.addAttribute("code", code);

		return "callback";
	}

}
