package model;

public class X {
	private int ID;
	private boolean Y;
	
	public X() {
		ID = -1;
	}
	
	public X(int id, boolean in) {
		ID = id;
		Y = in;
	}
	
	public void setID(int i) {
		ID = i;
	}
	
	public int getID() {
		return this.ID;
	}
	
	public void setX(boolean in) {
		Y = in;
	}
	
	public boolean getX() {
		return this.Y;
	}
	
	public String toString() {
		return "X ["+this.ID+"] : "+this.Y;
	}
}
