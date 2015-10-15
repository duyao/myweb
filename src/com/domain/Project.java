package com.domain;

import java.sql.Date;

public class Project {
	
	private	int	proid;
	private	int	leadid;
	private int teamid;
	private	String	proname;
	private	String	des;
	private	String	finish;
	private	String	 protype;
	private	Date	startdate;
	private	Date	enddate;
	
	/**
	 * @return the proid
	 */
	public int getProid() {
		return proid;
	}
	/**
	 * @param proid the proid to set
	 */
	public void setProid(int proid) {
		this.proid = proid;
	}
	/**
	 * @return the leadid
	 */
	public int getLeadid() {
		return leadid;
	}
	/**
	 * @param leadid the leadid to set
	 */
	public void setLeadid(int leadid) {
		this.leadid = leadid;
	}
	/**
	 * @return the proname
	 */
	public String getProname() {
		return proname;
	}
	/**
	 * @param proname the proname to set
	 */
	public void setProname(String proname) {
		this.proname = proname;
	}
	/**
	 * @return the des
	 */
	public String getDes() {
		return des;
	}
	/**
	 * @param des the des to set
	 */
	public void setDes(String des) {
		this.des = des;
	}
	/**
	 * @return the teamid
	 */
	public int getTeamid() {
		return teamid;
	}
	/**
	 * @param teamid the teamid to set
	 */
	public void setTeamid(int teamid) {
		this.teamid = teamid;
	}
	/**
	 * @return the finish
	 */
	public String getFinish() {
		return finish;
	}
	/**
	 * @param finish the finish to set
	 */
	public void setFinish(String finish) {
		this.finish = finish;
	}
	
	public Project(int proid, int leadid, int teamid, String proname,
			String des) {
		super();
		this.proid = proid;
		this.leadid = leadid;
		this.teamid = teamid;
		this.proname = proname;
		this.des = des;

	}
	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the protype
	 */
	public String getProtype() {
		return protype;
	}
	/**
	 * @param protype the protype to set
	 */
	public void setProtype(String protype) {
		this.protype = protype;
	}
	/**
	 * @return the startdate
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * @param date the startdate to set
	 */
	public void setStartdate(Date date) {
		this.startdate = date;
	}
	/**
	 * @return the enddate
	 */
	public Date getEnddate() {
		return enddate;
	}
	/**
	 * @param date the enddate to set
	 */
	public void setEnddate(Date date) {
		this.enddate = date;
	}

}
