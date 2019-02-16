package eu.jpereira.trainings.designpatterns.structural.facade;



import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;
import eu.jpereira.trainings.designpatterns.structural.facade.service.BookDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerDBService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.CustomerNotificationService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.OrderingService;
import eu.jpereira.trainings.designpatterns.structural.facade.service.WharehouseService;


public class DefaultBookStoreFacade implements BookstoreFacade {
	
	private CustomerNotificationService customService;
	private WharehouseService wareHouse;
	private CustomerDBService customerService;
	private BookDBService bookService;
	private OrderingService orderingService;


	
	@Override
	public void placeOrder(String customerId, String isbn) {
		//Order order = new Order(customerId,isbn);
		// TODO Auto-generated method stub
		
		Book dummyBook = bookService.findBookByISBN(isbn);
		Customer dummyCustomer = customerService.findCustomerById(customerId);
		Order dummyOrder = orderingService.createOrder(dummyCustomer, dummyBook);
		DispatchReceipt dummyDispatchReceipt = wareHouse.dispatch(dummyOrder);
		customService.notifyClient(dummyDispatchReceipt);
	}
	
	public void setCustomerService(CustomerNotificationService customService) {
		this.customService = customService;
	}
	
	public void setWharehouseService(WharehouseService wareHouse) {
		this.wareHouse = wareHouse;
	}
	public void setCustomerDBService(CustomerDBService customerService) {
		this.customerService = customerService;
	}
	public void setBookDBService(BookDBService bookService) {
		this.bookService = bookService;
	}
	public void OrderingService(OrderingService orderingService) {
		this.orderingService = orderingService;
	}

}
