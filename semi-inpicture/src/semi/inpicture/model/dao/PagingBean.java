package semi.inpicture.model.dao;

/**
 * 페이징 처리를 위한 비즈니스 계층의 클래스 PagingBean method 구현순서
 * getStartRowNumber()<br>
 * getEndRowNumber()<br> 
 * getTotalPage()<br> 
 * getTotalPageGroup()<br>
 * getNowPageGroup()<br>
 * getStartPageOfPageGroup()<br>
 * getEndPageOfPageGroup()<br> 
 * isPreviousPageGroup()<br>
 * isNextPageGroup()
 * 
 * @author kosta
 *
 */
public class PagingBean {
	/**
	 * 현재 페이지
	 */
	private int nowPage = 1;
	/**
	 * 페이지당 게시물수
	 */
	private int postCountPerPage = 5;
	/**
	 * 페이지 그룹당 페이지수
	 */
	private int pageCountPerPageGroup = 4;
	/**
	 * database에 저장된 총게시물수
	 */
	private int totalPostCount;

	public PagingBean() {
	}

	public PagingBean(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}

	public PagingBean(int totalPostCount, int nowPage) {
		this.totalPostCount = totalPostCount;
		this.nowPage = nowPage;
	}

	public int getNowPage() {
		return nowPage;
	}

	/**
	 * 현재 페이지번호에 해당하는 시작 게시물의 row number를 반환 <br>
	 * 
	 * 
	 * @return
	 */
	public int getStartRowNumber() {
		int startRowNumber = 0;
		if(nowPage == 1 ) {
			startRowNumber = 1;
		}else {
			//nowPage-1의 endRowNumber + 1
			//nowPage가 1인경우엔 0*postCountPerPage+1 이므로 따로 조건을 안줘도된다.
			startRowNumber = ((nowPage-1)*postCountPerPage)+1;
		}
		return startRowNumber;
	}

	/**
	 * 현재 페이지에서 보여줄 게시물 행(row)의 마지막 번호<br>
	 * 
	 * @return
	 */
	public int getEndRowNumber() {	
		int endRowNumber = 0;
		if(nowPage==1) {
			endRowNumber = postCountPerPage;
		}else {
			if(totalPostCount%postCountPerPage!=0) {
				endRowNumber = nowPage*postCountPerPage;
			}else {
				endRowNumber = totalPostCount;
			}
		}
		return endRowNumber;
	}

	/**
	 * 총 페이지 수를 return한다.<br>
	 * @return
	 */
	private int getTotalPage() {		
		int totalPage = 0;
		totalPage=((totalPostCount-1)/postCountPerPage)+1;
		return totalPage;
	}

	/**
	 * 총 페이지 그룹의 수를 return한다.<br>
	 */
	private int getTotalPageGroup() {	
		int totalPageGroup = 0;
		//총 10페이지 이므로 3그룹이 나와야 한다.
		if(getTotalPage()%pageCountPerPageGroup==0) {
			totalPageGroup = getTotalPage()/pageCountPerPageGroup;
		}else {
			totalPageGroup = getTotalPage()/pageCountPerPageGroup+1;
		}
		return totalPageGroup;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹 번호(몇 번째 페이지 그룹인지) 을 return 하는 메소드 <br>
	 * 
	 * @return
	 */
	private int getNowPageGroup() {		
		int NowPageGroup = 0;
		//현재 페이지를 페이지 그룹당 페이지수로 나눈 것이 0이 아니면m
		if(nowPage%pageCountPerPageGroup != 0 ) {
			NowPageGroup = (nowPage/pageCountPerPageGroup)+1;
		}else {
			NowPageGroup = (nowPage/pageCountPerPageGroup);
		}
		return NowPageGroup;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 시작 페이지 번호를 return 한다.<br>
	 * 
	 * @return
	 */
	public int getStartPageOfPageGroup() {	
		int startPageOfPageGroup =0;
			if(getNowPageGroup()==1) {
				startPageOfPageGroup = 1;
			}else {
				startPageOfPageGroup = ((getNowPageGroup()-1)*pageCountPerPageGroup)+1;
			}
		return startPageOfPageGroup;
	}

	/**
	 * 현재 페이지가 속한 페이지 그룹의 마지막 페이지 번호를 return 한다.<br>
	 * 
	 * @return
	 */
	public int getEndPageOfPageGroup() {
		int endPageOfPageGroup = getNowPageGroup() * pageCountPerPageGroup;
		if(endPageOfPageGroup > getTotalPage()) {
			endPageOfPageGroup = getTotalPage();
		}
		return endPageOfPageGroup;
	
	}

	/**
	 * 이전 페이지 그룹이 있는지 체크하는 메서드 <br>	
	 * @return
	 */
	public boolean isPreviousPageGroup() {
		boolean flag = false;
		if((getNowPageGroup()-1) != 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 다음 페이지 그룹이 있는지 체크하는 메서드 <br>	 
	 * @return
	 */
	public boolean isNextPageGroup() {
		boolean flag = false;
		if(getNowPageGroup() != getTotalPageGroup()) {
			flag = true;
		}
		return flag;
	}

	public int getPostCountPerPage() {
		return postCountPerPage;
	}

	public void setPostCountPerPage(int postCountPerPage) {
		this.postCountPerPage = postCountPerPage;
	}

	public int getPageCountPerPageGroup() {
		return pageCountPerPageGroup;
	}

	public void setPageCountPerPageGroup(int pageCountPerPageGroup) {
		this.pageCountPerPageGroup = pageCountPerPageGroup;
	}

	public int getTotalPostCount() {
		return totalPostCount;
	}

	public void setTotalPostCount(int totalPostCount) {
		this.totalPostCount = totalPostCount;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	

/*	public static void main(String args[]) {
		PagingBean p = new PagingBean(47, 10);
		// 현페이지의 시작 row number 를 조회 46
		System.out.println("getBeginRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 47
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 전체 페이지 수 : 10
		System.out.println("getTotalPage:" + p.getTotalPage());
		// 전체 페이지 그룹 수 : 3
		System.out.println("getTotalPageGroup:" + p.getTotalPageGroup());
		System.out.println("//////////////////////////////////////////////////");
		p = new PagingBean(31, 6);// 게시물수 31 현재 페이지 6
		// 현페이지의 시작 row number 를 조회 26
		System.out.println("getStartRowNumber:" + p.getStartRowNumber());
		// 현페이지의 마지막 row number 를 조회 30
		System.out.println("getEndRowNumber:" + p.getEndRowNumber());
		// 게시물수 31 -> 총페이지수 7 -> 총페이지그룹->2
		// 현재 페이지 그룹 : 2
		System.out.println("getNowPageGroup:" + p.getNowPageGroup());
		// 페이지 그룹의 시작 페이지 : 5
		System.out.println("getStartPageOfPageGroup:" + p.getStartPageOfPageGroup());
		// 페이지 그룹의 마지막 페이지 : 7
		System.out.println("getEndPageOfPageGroup:" + p.getEndPageOfPageGroup());
		// 이전 페이지 그룹이 있는 지 : true
		System.out.println("isPreviousPageGroup:" + p.isPreviousPageGroup());
		// 다음 페이지 그룹이 있는 지 : false
		System.out.println("isNextPageGroup:" + p.isNextPageGroup());

	}*/

}
