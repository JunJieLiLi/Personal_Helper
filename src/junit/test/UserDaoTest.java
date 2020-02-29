package junit.test;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.itcast.DBForm.UserTransactionForm;
import com.itcast.Daoa.UserDao;
import com.itcast.domain.User;
import com.itcast.dao.impl.UserDaoImpl;
public class UserDaoTest 
{
	@Test 
	public void testAdd() throws SQLException, ClassNotFoundException
	{
		/*
		User user= new User();
		user.setUserID(66);
		user.setUsername("hh");
		user.setPassword("ee");
		
		UserDao dao= new UserDaoImpl();
		dao.find("hh");
		
		*/
		UserDao dao= new UserDaoImpl();
		
		
		//ArrayList<UserTransactionForm> userTranList= new ArrayList<UserTransactionForm>();
		//userTranList=dao.getUserTransactionList(2);
		//dao.findUserID(7667);
		dao.getUserTransactionList(2717);
		//User user2= new User();
		
		//user2=dao.find("vv","vv");
		
		//System.out.println("User name: "+user2.getUsername());
		//System.out.println("User pass: "+user2.getPassword());
	
	}
}
