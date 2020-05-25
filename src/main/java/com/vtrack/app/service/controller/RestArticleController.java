package com.vtrack.app.service.controller;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vtrack.app.service.constant.ServiceConstants;
import com.vtrack.app.service.entity.Article;
import com.vtrack.app.service.model.HtmlArticle;
import com.vtrack.app.service.service.ArticleService;


@RestController
@RequestMapping("api/article")
public class RestArticleController {

	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return "jai hind";
    }
	
	
	@RequestMapping(value = "/article/{articleid}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity<HtmlArticle> getArticleInJSON(@PathVariable String articleid) {
		HtmlArticle hArticle = null;
		int id = Integer.parseInt(articleid);
		Article article = null;
		article = articleService.findArticleById(id);
		if(null != article){
		String details = article.getBody();
		try {
			hArticle = parseXml(details,hArticle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(hArticle.getPublishStatus().equalsIgnoreCase(ServiceConstants.Published))
		{return new ResponseEntity<HtmlArticle>(hArticle, HttpStatus.OK);}
		}
		return new ResponseEntity<HtmlArticle>(new HtmlArticle(), HttpStatus.NO_CONTENT);
    }
	
	
	public HtmlArticle parseXml(String xmlResponse, HtmlArticle htmlArticle) throws Exception
	{

	 StringReader stringReader = new StringReader(xmlResponse);
	            JAXBContext jaxbContext = JAXBContext.newInstance(HtmlArticle.class);
	            XMLInputFactory xif = XMLInputFactory.newInstance();
	            XMLStreamReader xsr = xif.createXMLStreamReader(stringReader);
	            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
	            return (HtmlArticle) unmarshaller.unmarshal(xsr);
	}
}
