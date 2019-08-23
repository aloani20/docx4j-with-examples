package com.abdul.worddocument;

import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class WorddocumentApplication {

	public static void main(String[] args) throws Docx4JException , FileNotFoundException , JAXBException {
		SpringApplication.run(WorddocumentApplication.class, args);

//		WordProcessor wordProcessor = new WordProcessor();
//		WordprocessingMLPackage template = wordProcessor.getTemplate("/Users/mwad3/dev/docx4j-master/src/main/resources/Document 6.docx");
//		MainDocumentPart mainDocumentPart = template.getMainDocumentPart();
//		System.out.println(mainDocumentPart.getXML());
//		Map<String,String> repl1 = new HashMap<String, String>();
//		repl1.put("SJ_FUNCTION", "function1");
//		repl1.put("SJ_DESC", "desc1");
//		repl1.put("SJ_PERIOD", "period1");
//
//		Map<String,String> repl2 = new HashMap<String, String>();
//		repl2.put("SJ_FUNCTION", "function2");
//		repl2.put("SJ_DESC", "desc2");
//		repl2.put("SJ_PERIOD", "period2");
//
//		Map<String,String> repl3 = new HashMap<String, String>();
//		repl3.put("SJ_FUNCTION", "function3");
//		repl3.put("SJ_DESC", "desc3");
//		repl3.put("SJ_PERIOD", "period3");
//
//		wordProcessor.replaceTable(new String[]{"SJ_FUNCTION","SJ_DESC","SJ_PERIOD"}, Arrays.asList(repl1,repl2,repl3), template);
		}

}
