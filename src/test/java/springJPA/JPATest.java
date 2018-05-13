package springJPA;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.suman.springjpa.configuration.AppConfig;
import com.suman.springjpa.entity.User;
import com.suman.springjpa.service.UserService;

public class JPATest {

	public static void main(String[] args) {
		
		ApplicationContext ctx=new AnnotationConfigApplicationContext(AppConfig.class);
		
		UserService userService=(UserService) ctx.getBean("userService");
		
		
	/*	
		User add=new User();
		//add.setUserId(3);
		add.setUsername("sriram");
		add.setPassword("user");
		userService.save(add);*/
		
		List<User> users=userService.findAllUsers();
		for (User user : users) {
		System.out.println(user.getUsername());	
		}
		
		

	}

}
