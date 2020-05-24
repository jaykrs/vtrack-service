package com.vtrack.app.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Articlefile")
public class ArticleFile {

	
	@Id
	@Column(name = "ArticleFileId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long articleFileId;
	
	@Column
	private String fileName;
	@Column
	private String fileDescription;
	@Column
	private String fileType;
	@Column
	private Integer fileSize;
	@Column
	private String domain;
	@Column
	private String filePath;
	@Column
	private String fileUrl;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Integer getFileSize() {
		return fileSize;
	}
	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize/1024;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public long getArticleFileId() {
		return articleFileId;
	}
	public void setArticleFileId(long articleFileId) {
		this.articleFileId = articleFileId;
	}
	
}