package org.we5.waterplant.javaclass;

public enum MonthVal {

	JANUARY(1, "1-31"), FEBRAUARY(2, "1-28"), MARCH(3, "1-31"), APRIL(4, "1-30"), MAY(
			5, "1-31"), JUNE(6, "1-30"), JULY(7, "1-31"), AUGUST(8, "1-31"), SEPTEMBER(
			9, "1-30"), OCTOBER(10, "1-31"), NOVEMBER(11, "1-30"), DECEMBER(12,
			"1-31");

	private String range;

	private int seq;

	private MonthVal(int seq, String range) {
		this.seq = seq;
		this.range = range;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	
	
}
