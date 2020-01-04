package cn.dapotian.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping("/show")
	@ResponseBody
	public String show() {

		return "Hello World";

	}

	/**
	 * 进入首页
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model) {

		return "index";
	}

	/**
	 * 进入添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "user/add";
	}

	/**
	 * 进入更新页面
	 * 
	 * @return
	 */
	@RequestMapping("/update")
	public String update() {
		return "user/update";
	}

	/**
	 * 登录页面
	 * 
	 * @return
	 */
	@RequestMapping("/loginPage")
	public String loginPage() {
		System.out.println("请求地址：/user/loginPage");
		return "login";
	}


	/**
	 * 登录
	 * 
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String username, String password, Model model) {
		System.out.println("请求地址：/user/login");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			return "redirect:index";
		} catch (UnknownAccountException e) {
			// TODO Auto-generated catch block
			// 登录失败：用户名不存在
			System.out.println("登录失败：用户名不存在");
			model.addAttribute("msg", "登录失败：用户名不存在");
			e.printStackTrace();
			return "login";  
		} catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			// 登录失败：用户名不存在
			System.out.println("登录失败：密码错误");
			model.addAttribute("msg", "登录失败：密码错误");
			return "login";
		}

	}

	/**
	 * 登录
	 *
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout() {
		System.out.println("请求地址：/user/logout");
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return "redirect:index";
	}
}
