package com.zhiyuan.androidwidget.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;

/**
 * Created by stefan on 2017/5/17.
 */
@Entity
public class User {
	@Id
	private Long	id;
	@Property(nameInDb = "USERNAME")
	private String	username;
	@Property(nameInDb = "PASSWORD")
	private String	password;
	@Transient
	private String	nickname;
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return this.username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getId() {
		return this.id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Generated(hash = 1681958521)
	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}
	@Generated(hash = 586692638)
	public User() {
	}
	
}
