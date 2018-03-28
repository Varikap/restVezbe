package primer01;

public class User {
	private String name;
	private double num;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getNum() {
		return num;
	}
	public void setNum(double num) {
		this.num = num;
	}
	public User(String name, double num) {
		super();
		this.name = name;
		this.num = num;
	}
	
	
}
