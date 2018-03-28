package rest.samples.crud;

import java.util.Vector;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import rest.pojo.User;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON) // na nivou klase se definise, da svaka metoda vraca podatke u JSON-u
public class UserService {
	private static Vector<User> users = new Vector<>();
	private static int brojac = 1;

	@GET
	public Vector<User> getAll() {
		return users;
	}

	@GET
	@Path("/{id}")
	// @Path("/{id: \\d+}")	// dozvoljeni samo brojevi
	public User getById(@PathParam("id") int id) {
		for (User u : users) {
			if (u.getId() == id) {
				return u;
			}
		}
		return null;
	}

	@GET
	@Path("/username/{username: [a-zA-Z][0-9a-zA-Z]*}")
	public User getByUsername(@PathParam("username") String username) {
		for (User u : users) {
			if (u.getUsername().equals(username)) {
				return u;
			}
		}
		return null;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		user.setId(brojac++);
		users.add(user);
		return user;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public User updateUser(User user) {
		for (User u : users) {
			if (u.getId() == user.getId()) {
				u.setFirstname(user.getUsername());
				u.setLastname(user.getLastname());
				u.setEmail(user.getEmail());
				u.setUsername(user.getUsername());
				u.setPassword(user.getPassword());
				return u;
			}
		}
		return null;
	}

	@DELETE
	@Path("/{id}")
	public void deleteUser(@PathParam("id") int id) {
		User user = null;
		for (User u : users) {
			if (u.getId() == id) {
				user = u;
			}
		}
		if (user != null) {
			users.remove(user);
		}
	}

}
