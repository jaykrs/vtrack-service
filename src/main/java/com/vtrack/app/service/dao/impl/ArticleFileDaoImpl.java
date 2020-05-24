package com.vtrack.app.service.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vtrack.app.service.dao.AbstractDao;
import com.vtrack.app.service.dao.ArticleFileDao;
import com.vtrack.app.service.entity.ArticleFile;
@Repository(value = "articleFileDao")
public class ArticleFileDaoImpl extends AbstractDao<Integer, ArticleFile> implements ArticleFileDao{

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<ArticleFile> listArticleFiles() {
		List<ArticleFile> list;
		list = getSession().createCriteria(ArticleFile.class).list();
				return list;
	}

	public ArticleFile getArticleFile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addArticleFile(ArticleFile articleFile) {
		getSession().save(articleFile);
		
	}

	public void updateArticleFile(ArticleFile articleFile) {
		// TODO Auto-generated method stub
		
	}

	public boolean deleteArticleFile(int articleFileId) {
		// TODO Auto-generated method stub
		return false;
	}

}
