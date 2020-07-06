package patrick.cai.oidc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	private String prefix = "classpath:/templates/";
	private String suffix = ".html";

	@RequestMapping("/index")
	public String index() {
		return "oidc";
	}

}
