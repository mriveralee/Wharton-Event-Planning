package edu.upenn.cis350;

/* Event Data Model */
public class Event {
	private long id;
	private String name;
	private String time;
	private String loc;
	private long global_id;
	
	public long getId()
	{ return id; }
	
	public void setId(long id)
	{ this.id = id; }
	
	public String getName()
	{ return name; }
	
	public void setName(String name)
	{ this.name = name; }
	
	public String getTime()
	{ return time; }
	
	public void setTime(String time)
	{ this.time = time; }
	
	public String getLoc()
	{ return loc; }
	
	public void setLoc(String loc)
	{ this.loc = loc; }
	
	public long getGId()
	{ return global_id; }
	
	public void setGId(long gid)
	{ this.global_id = gid; }
	
	@Override
	public String toString() {
		return id + ": " + name + ", " + loc + ", " + time + " (global event: " + global_id + ")";
	}
}