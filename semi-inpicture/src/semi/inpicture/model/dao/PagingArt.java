package semi.inpicture.model.dao;

public class PagingArt {
	private int start = 1;
	private int totalArt;
	
	public PagingArt(int start, int totalArt) {
		super();
		this.start = start;
		this.totalArt = totalArt;
	}
	public PagingArt(int totalArt) {
		super();
		this.totalArt = totalArt;
	}
	public int getStart() {
		return this.start;
	}
	public int getEnd() {
		return this.start+2;
	}
	public boolean isPrevious() {
		boolean flag=false;
		if(this.start-1!=0)
			flag=true;
		return flag;
	}
	public boolean isNext() {
		boolean flag=false;
		if(this.start+1<totalArt)
		 flag = true;
		return flag;
	}
}
