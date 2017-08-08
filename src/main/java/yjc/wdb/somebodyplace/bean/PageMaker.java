package yjc.wdb.somebodyplace.bean;

public class PageMaker {

	// 사용자 요청이 없을 경우 디폴트 값
	private int page = 1;
	private int recordsPerPage = 10;

	private String searchType; // 검색하기 위한 변수 선언
	private String keyword; // 키워드 변수 선언

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	private int startPage; // 시작
	private int endPage; // 끝
	private int totalCount; // 현재 게시글 총 수
	private boolean prev;
	private boolean next;
	
	public final static int DISPLAY_PAGE_NUM = 10; // 고정
	
	private Criteria cri;
	
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calculate();
	}
	
	private void calculate() {
		/*
		 * startPage, endPage, prev, next 계산해줌
		 */
		endPage = (int) Math.ceil(page / (double) DISPLAY_PAGE_NUM) * DISPLAY_PAGE_NUM;
		
		/*
		 * endPage까지 나타낼 수 있는 글 수와
		 * 실제로 들어있는 총 글수를 비교한다.(totalCount)
		 * endPage까지 나타낼 수 있는 글 수는 : endPage * recordsPerPage
		 * (endPage*recordsPerPage) > totalCount
		 * 만약에 위가 참이라면
		 * endPage = (totalCount/recordsPerPage)+1;
		 */
		startPage = (endPage - DISPLAY_PAGE_NUM) + 1;
		
		if((endPage*recordsPerPage)>totalCount)
			endPage = (totalCount/recordsPerPage)+1;
		
		/*		int tempEndPage = (int) (Math.ceil(totalCount) / (double) recordsPerPage);
		if (endPage > tempEndPage) {
			endPage = tempEndPage + 1;

		}*/
		prev = startPage > 1 ? true : false;
		next = endPage * recordsPerPage < totalCount ? true : false;
	}

	// 입력값을 받아야 하기 때문에
	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getStartRecord() {
		/*
		 * page가 1이면 0*10리턴 page가 2이면 1*10리턴 page가 3이면 2*10리턴 page가 4이면 3*10리턴
		 * page가 5이면 4*10리턴 ......
		 * 
		 */
		return (page - 1) * recordsPerPage;
	}

	// 사용자 요청 값
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
		} else {
			this.page = page;
		}
	}

	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	public void setRecordsPerPage(int recordsPerPage) {
		if (recordsPerPage <= 0 || recordsPerPage > 100) {
			this.recordsPerPage = 10;
		} else {
			this.recordsPerPage = recordsPerPage;
		}
	}

	public String toString() {
		return "criteria [page=" + page + ", " + "recordsPerPage=" + recordsPerPage + "]";
	}
}
