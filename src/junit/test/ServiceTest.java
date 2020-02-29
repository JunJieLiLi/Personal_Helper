package junit.test;

import java.text.ParseException;
import java.util.Random;

import org.junit.Test;

import com.itcast.domain.Transaction;
import com.itcast.domain.User;
import com.itcast.exception.UserExistException;
import com.itcast.service.impl.BusinessServiceImpl;
import com.itcast.web.formbean.AddTransactionForm;

public class ServiceTest {

	@Test
	public void testRegister() throws ParseException
	{
		/*
		User user= new User();
		user.setUsername("ff");
		user.setPassword("ff");
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		//int test=(int) Math.random();
		Random random = new Random();
		int value = random.nextInt(100);
		
		try {
			service.register(user);
			System.out.println("zhu ce cheng gong");
		} catch (UserExistException e) {
			// TODO Auto-generated catch block
			System.out.println("yong hu yi jing cun zai");
		}
		*/
		
		/*
		Transaction trans= new Transaction();
		trans.setDate("10-12-99");
		trans.setDescription("grocery");
		trans.setIncome("40");
		trans.setOutgoing("dontknow");
		BusinessServiceImpl service= new BusinessServiceImpl();
		*/
		/*
		AddTransactionForm tranform= new AddTransactionForm();
		tranform.setFKClientID(2);
		tranform.setDate("11");
		tranform.setDescription("12");
		tranform.setIncome("13");
		tranform.setOutgoing("14");
		
		
		BusinessServiceImpl service= new BusinessServiceImpl();
		
		try {
			service.addTransaction(tranform);
		} catch (UserExistException e) {
			e.printStackTrace();
		}
		*/
		BusinessServiceImpl service= new BusinessServiceImpl();
		//service.getDescdingOrderTransactions(78);
		
		//service.getSelectedMonthTransactionS(78, "2019-12-1", "2019-12-31");
		//service.deleteTransaction(78);
		//service.getUserTransaction("renhuang", "renhuang");
	}

}
