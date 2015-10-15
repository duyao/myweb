package com.domain;

public class Task {
	private int taskid           ;
	private String taskname      ;
	private int proid            ;
	private int seq              ;
	/**
	 * @return the taskid
	 */
	public int getTaskid() {
		return taskid;
	}
	/**
	 * @param taskid the taskid to set
	 */
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	/**
	 * @return the taskname
	 */
	public String getTaskname() {
		return taskname;
	}
	/**
	 * @param taskname the taskname to set
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
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
	 * @return the seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * @param seq the seq to set
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public Task(int taskid, String taskname, int proid, int seq) {
		super();
		this.taskid = taskid;
		this.taskname = taskname;
		this.proid = proid;
		this.seq = seq;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	


}
