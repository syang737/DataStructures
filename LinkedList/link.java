class Link
{
	private Object payload;
	private Link next;
	public Link(Object pl, Link n)
	{
		this.payload = pl;
		this.next = n;
	}
	public Object getPayload()
	{
		return this.payload;
	}
	public Link getNext()
	{
		return this.next;
	}
	public void setPayload(Object pl)
	{
		this.payload = pl;
	}
	public void setNext(Link n)
	{
		this.next = n;
	}
	public boolean hasNext()
	{
		return (this.next != null);
	}
	public boolean hasPayload()
	{
		return (this.payload != null);
	}
}