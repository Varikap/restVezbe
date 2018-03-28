package service;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlTransient;

import pojo.Item;
import pojo.Order;

@Path("/zadatak")
@Produces(MediaType.APPLICATION_JSON)
public class ZadatakService {
	private static ArrayList<Order> orders = new ArrayList<>();
//	AKO HOCES DA PRAVIS SVOJ KONSTRUKTOR ONDA DODAJ I PRAZAN KONSTRUKTOR NA SVE KLASE!!!!
	static {
		Order o1 = new Order(1, "prva");
		Order o2 = new Order(2, "druga");
		Item i1 = new Item(1, "pirinac", 666.666);
		Item i2 = new Item(2, "brokoli", 999.999);
		Item i3 = new Item(3, "spanac", 23.43);
		Item i4 = new Item(2, "ajs", 43.23);
		o1.addItem(i1);
		o1.addItem(i2);
		o2.addItem(i3);
		o2.addItem(i4);
		orders.add(o1);
		orders.add(o2);
	}
	
	@XmlTransient
	@GET
	@Path("/orders")
	public ArrayList<Order> getOrders() {
		return orders;
	}
	
	@Path("/orders/{id}")
	public Order getOrderById (@PathParam("id") int id) {
		for (Order o : orders)
			if (o.getId() == id)
				return o;
		return null;
	}
	
	@POST
	@Path("/orders/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Order createOrder (Order order) {
		order.setId(hashCode());
		orders.add(order);
		return order;
	}
}
