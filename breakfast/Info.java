package breakfast;

public class Info {//Value

	private double price;
	private int calories;
	private int deliciousness;//scale of 0-10
	
	
	public Info(double price, int calories, int deliciousness) {
		super();
		this.price = price;
		this.calories = calories;
		this.deliciousness = deliciousness;
	}


	@Override
	public String toString() {
		return "Info [price=" + price + ", calories=" + calories + ", deliciousness=" + deliciousness + "]";
	}
	
	
	
}
