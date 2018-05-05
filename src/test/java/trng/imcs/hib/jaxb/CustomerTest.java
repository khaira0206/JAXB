package trng.imcs.hib.jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Test;

import trng.jaxb.customer.Address;
import trng.jaxb.customer.Customer;
import trng.jaxb.customer.ObjectFactory;
import trng.jaxb.customer.Payment;


public class CustomerTest {

	@Test
	public void serialization() throws DatatypeConfigurationException, JAXBException {
		
		ObjectFactory fac = new ObjectFactory();
		Address address = fac.createAddress();

		address.setCity("Irving");
		address.setState("Texas");
		address.setStreet("Colonial Grand");
		address.setZipcode("76263");

		GregorianCalendar c1 = new GregorianCalendar(2017,02,02);
		GregorianCalendar c2 = new GregorianCalendar(2017,04,04);
		GregorianCalendar c3 = new GregorianCalendar(1990,04,04);
	
		XMLGregorianCalendar dateTo = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		XMLGregorianCalendar dateFrom = DatatypeFactory.newInstance().newXMLGregorianCalendar(c2);
		XMLGregorianCalendar dob = DatatypeFactory.newInstance().newXMLGregorianCalendar(c3);
		
		Payment payment = new Payment();
		payment.setCardName("Harman");
		payment.setCardNumber(11112222);
		payment.setCardtype("CREDITCARD");
		payment.setDateFrom(dateFrom);
		payment.setDateTo(dateTo);

		List<Payment> list = new ArrayList<>();
		list.add(payment);

		Customer customer = new Customer();
		customer.setCustomerId(1);
		customer.setName("Harman");
		customer.setDob(dob);
		customer.setSalary(1500);
		customer.setAddress(address);
		customer.setPayment(list);

		
		JAXBContext context = JAXBContext.newInstance(Customer.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(customer, new File("Customer.xml"));
		
		
	}
	
}
