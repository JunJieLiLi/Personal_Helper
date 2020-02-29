package junit.test;

import java.sql.SQLException;

import org.junit.Test;

import com.itcast.Daoa.TransactionDao;
import com.itcast.dao.impl.TransactionDaoImpl;
import com.itcast.domain.Transaction;
import com.itcast.domain.User;

public class TransactionDapTest {

	@Test 
	public void testAdd() throws SQLException, ClassNotFoundException
	{

		TransactionDao trandao= new TransactionDaoImpl();
		//trandao.deleteTransaction(57);
		
		Transaction tran= new Transaction();
		//tran=trandao.getSingleEditTransactionDB(50);

		//trandao.upDateEditTransactionDB(49, 78, "111", "222", "333", "444");
		/*
		// check if the transaction is already exist in the database
		if(trandao.findTransaction(tran.getDate()))
		{
			Transaction trans= new Transaction(); 
			trans.setDate("123");
			trans.setDescription("123");
			trans.setIncome("123");
			trans.setOutgoing("123");
			
			trandao.editTransaction(trans);
		}
	
		*/
	
	}

}
