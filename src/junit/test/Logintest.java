package junit.test;

import org.junit.Test;

import com.itcast.Daoa.UserDao;
import com.itcast.dao.impl.UserDaoImpl;
import com.itcast.domain.User;

public class Logintest {

	@Test
	public void test()
	{

		UserDao udao= new UserDaoImpl();
		udao.find("1", "1");
		
		
		
	}

}
