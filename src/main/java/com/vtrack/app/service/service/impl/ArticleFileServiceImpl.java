package com.vtrack.app.service.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vtrack.app.service.dao.ArticleFileDao;
import com.vtrack.app.service.entity.ArticleFile;
import com.vtrack.app.service.service.ArticleFileService;

/**
 * @author jayant
 *
 */
@Repository(value = "articleFileService")
@Transactional
public class ArticleFileServiceImpl implements ArticleFileService{

	@Autowired
	private ArticleFileDao articleFileDao;
	
	public List<ArticleFile> getAllArticleFiles() {
		return articleFileDao.listArticleFiles();
	}

	public ArticleFile findArticleFileById(int articleFileId) {
		return articleFileDao.getArticleFile(articleFileId);
	}

	public void addArticleFile(ArticleFile articleFile) {
		articleFileDao.addArticleFile(articleFile);
	}

	public void saveArticleFile(ArticleFile articleFile) {
		articleFileDao.updateArticleFile(articleFile);
		
	}

	public boolean deleteArticleFile(int articleFileId) {
		// TODO Auto-generated method stub
		return false;
	}

}