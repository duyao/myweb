package com.domain;

public class Team {
	private	int	teamid;
	private	int	proid;
	private	int	leaderid;
	private	int	total;
	
	
	public Team(int teamid, int proid, int leaderid, int total) {
		super();
		this.teamid = teamid;
		this.proid = proid;
		this.leaderid = leaderid;
		this.total = total;
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
	 * @return the leaderid
	 */
	public int getLeaderid() {
		return leaderid;
	}
	/**
	 * @param leaderid the leaderid to set
	 */
	public void setLeaderid(int leaderid) {
		this.leaderid = leaderid;
	}
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
