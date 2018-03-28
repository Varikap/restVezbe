package pojo;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
@Produces(MediaType.APPLICATION_JSON)
@XmlRootElement
public class Order {
	private int id;
	private String name;
	private boolean deleted = false;
	private ArrayList<Item> items = new ArrayList<>();
	
	public Order() {}
	
	public void addItem(Item i) {
		items.add(i);
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Order(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	@Path("/")
	@GET
	public Order getOrder() {
		return this;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Order updateOrder (Order o) {
		name = o.getName();
		return this;
	}
	
	@DELETE
	@Path("/delete")
	public void delete() {
		deleted = true;
	}
}
