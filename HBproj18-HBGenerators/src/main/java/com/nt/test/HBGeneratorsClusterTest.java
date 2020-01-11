package com.nt.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nt.entity.Product;
import com.nt.utility.HibernateUtil;

public class HBGeneratorsClusterTest {

	public static void main(String[] args) {
		Session ses=null;
		Product prod=null,prod1=null;
		Transaction tx=null;
		boolean flag=false;
		int idVal=0,idVal1=0;
		  // get Session object
		ses=HibernateUtil.getSession();
		//save object
		prod=new Product();
		prod.setPname("chair");
		prod.setPrice(8999);
		prod.setQty(4);
		prod.setStatus(true);
		
	
		try {
			 tx=ses.beginTransaction();
			    idVal=(int)ses.save(prod);
			   System.out.println("Generated id value::"+idVal);
			   Thread.sleep(50000);
			    flag=true; 
		}//try
		catch(HibernateException he) {
			he.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		    //perform Tx Mgmt
			if(flag==true) {
				tx.commit();
				System.out.println("Object saved");
			}
			else {
				tx.rollback();
				System.out.println("Object not saved");
			}
			
			HibernateUtil.closeSessionFactory();
		
		}
		
	}

}
