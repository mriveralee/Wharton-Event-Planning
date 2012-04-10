package edu.upenn.cis350;

/* Person Data Model */
public class Person {
	private long id;
	private String name;
	private String pic;
	private String position;
	
	public long getId()
	{ return id; }
	
	public void setId(long id)
	{ this.id = id; }
	
	public String getName()
	{ return name; }
	
	public void setName(String name)
	{ this.name = name; }
	
	public String getPic()
	{ return pic; }
	
	public void setPic(String pic)
	{ this.pic = pic; }
	
	public String getPosition()
	{ return position; }
	
	public void setPosition(String position)
	{ this.position = position; }
	
	@Override
	public String toString() {
		return id + ": " + name + ", " + position;
	}
}
