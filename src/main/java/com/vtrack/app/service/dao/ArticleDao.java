package com.vtrack.app.service.dao;

import java.util.List;

import com.vtrack.app.service.entity.Article;

public interface ArticleDao {

	public List<Article> listArticles();
	public Article getArticle(int id);
	public void addArticle(Article article);
	public void updateArticle(Article article);
	public boolean deleteArticle(int id);
	
	
}