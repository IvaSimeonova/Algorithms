package Algorithms;

public class Circle implements Comparable<Circle> {
	int x;
	public Circle(int x) {
		this.x = x;
	}
	public int compareTo(Circle args0) {
		// TODO Auto-generated method stub
		return this.x - args0.x;
	}
	public String toString() {
		return " Radius: " + x;
	}

}