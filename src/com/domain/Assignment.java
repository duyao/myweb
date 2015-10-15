package com.domain;

public class Assignment {
	private	int        taskid   ;
	private	int        stuid    ;
	private	String     subtime  ;
	private	String     path     ;
	private String filename;
	private String mydes;

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
	 * @return the subtime
	 */
	public String getSubtime() {
		return subtime;
	}
	/**
	 * @param subtime the subtime to set
	 */
	public void setSubtime(String subtime) {
		this.subtime = subtime;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the mydes
	 */
	public String getMydes() {
		return mydes;
	}
	/**
	 * @param mydes the mydes to set
	 */
	public void setMydes(String mydes) {
		this.mydes = mydes;
	}
	public Assignment(int taskid, int stuid, String subtime, String path,
			String filename, String mydes) {
		super();
		this.taskid = taskid;
		this.stuid = stuid;
		this.subtime = subtime;
		this.path = path;
		this.filename = filename;
		this.mydes = mydes;
	}

	
}
