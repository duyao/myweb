package com.domain;

public class Member {

	
	private	int	teamid  ;
	private	int	stuid   ;
	private	int	proid   ;
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
	 * @return the stuid
	 */
	public int getStuid() {
		return stuid;
	}
	/**
	 * @param stuid the stuid to set
	 */
	public void setStuid(int stuid) {
		this.stuid = stuid;
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
	public Member(int teamid, int stuid, int proid) {
		super();
		this.teamid = teamid;
		this.stuid = stuid;
		this.proid = proid;
	}

}
