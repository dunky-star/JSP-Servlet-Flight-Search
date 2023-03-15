package com.dunky.flyaway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  // Using annotation-based metadata is same as "native" strategy in xml-based metadata. 
@Table(name = "admin_users")
public class Users {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
	
	@Column(name = "uname")
	private String  uname;
	
	@Column(name = "upwd")
	private String upwd;
	
	@Column(name = "uemail")
	private String uemail;
	
	@Column(name = "umobile")
	private String umobile;
	
	// Constructors
	public Users() {

    }

	public Users(int id, String uname, String upwd, String uemail, String umobile) {
		super();
		this.id = id;
		this.uname = uname;
		this.upwd = upwd;
		this.uemail = uemail;
		this.umobile = umobile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpwd() {
		return upwd;
	}

	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUmobile() {
		return umobile;
	}

	public void setUmobile(String umobile) {
		this.umobile = umobile;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", uname=" + uname + ", upwd=" + upwd + ", uemail=" + uemail + ", umobile=" + umobile
				+ "]";
	}
	
    	
}
