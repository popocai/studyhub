package demo.rest;

import java.util.Calendar;

import demo.entity.Customer;

public class CustomerServiceImpl implements CustomerService {
	public Customer findCustomerById(String id) {
        System.out.println("findCustomerById " + id);
		Customer customer = new Customer();
		customer.setId(id);
		customer.setName(id);
		customer.setBirthday(Calendar.getInstance().getTime());
		return customer;
	}

	public Customer findCustomerByName(String name) {
        System.out.println("findCustomerByName " + name);
		Customer customer = new Customer();
		customer.setId(name);
		customer.setName(name);
		customer.setBirthday(Calendar.getInstance().getTime());
		return customer;
	}
}
