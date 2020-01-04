package cn.dapotian;import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.catalina.LifecycleListener;

import cn.dapotian.domain.User;

public class InitUser {
	
	
	public List<User> init() {
		List<User> list= new ArrayList<>();
		User user1 = new User("张三","123");
		User user2 = new User("李四","456");
		User user3 = new User("钱七","789");

		list.add(user1);
		list.add(user2);
		list.add(user3);
		
		return list;
		
	}

}
