package com.vtrack.app.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jayant
 *
 */
@Entity
@Table(name = "Article")
public class Article {

	@Id
	@Column(name = "ArticleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "Title")
	private String title;

	@Column(name = "Teaser")
	private String teaser;

	@Column(name = "BodyContent")
	private String body;
	
	@Column(name = "MO_ID", nullable = false)
	private String managementObject;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTeaser() {
		return teaser;
	}

	public void setTeaser(String teaser) {
		this.teaser = teaser;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the managementObject
	 */
	public String getManagementObject() {
		return managementObject;
	}

	/**
	 * @param managementObject the managementObject to set
	 */
	public void setManagementObject(String managementObject) {
		this.managementObject = managementObject;
	}

}