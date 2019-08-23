package com.abdul.worddocument;

import org.docx4j.jaxb.Context;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.model.table.TblFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class DocxGenerator {

    private static final String TEMPLATE_NAME = "Document 7 (1).docx";

    public byte[] generateDocxFileFromTemplate( ) throws Exception {

        InputStream templateInputStream = this.getClass().getClassLoader().getResourceAsStream(TEMPLATE_NAME);

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(templateInputStream);

        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();

        VariablePrepare.prepare(wordMLPackage);

        HashMap<String, String> variables = new HashMap<>();
        variables.put("firstName", "عبد الرحمن");
        variables.put("lastName", "سلمان");
        variables.put("salutation", "السيد");
        variables.put("message", "مرحبا بك");

        documentPart.variableReplace(variables);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        outputStream = documentPart.
//        Docx4J.save(wordMLPackage, outputStream);
        wordMLPackage.save(outputStream);

        return outputStream.toByteArray();
    }

    public byte[] createDocxFile() throws Exception {

        WordprocessingMLPackage wordPackage = WordprocessingMLPackage.createPackage();
        MainDocumentPart mainDocumentPart = wordPackage.getMainDocumentPart();
        mainDocumentPart.addStyledParagraphOfText("Title", "Hello World!");
        mainDocumentPart.addParagraphOfText("Welcome To Baeldung!");

        ObjectFactory factory = Context.getWmlObjectFactory();
        P p = factory.createP();
        R r = factory.createR();
        Text t = factory.createText();
        t.setValue("السلام  عليكم ورحمة الله وبركاته ");
        r.getContent().add(t);
        p.getContent().add(r);
        RPr rpr = factory.createRPr();
        BooleanDefaultTrue b = new BooleanDefaultTrue();
//        rpr.setB(b);
//        rpr.setI(b);
//        rpr.setCaps(b);
        rpr.setRtl(b);

        Color red = factory.createColor();
        red.setVal("green");
        rpr.setColor(red);
        r.setRPr(rpr);
        mainDocumentPart.getContent().add(p);

        int writableWidthTwips = wordPackage.getDocumentModel().getSections().get(0).getPageDimensions().getWritableWidthTwips();
        int columnNumber = 3;
        Tbl tbl = TblFactory.createTable(3, 3, writableWidthTwips / columnNumber);
        List<Object> rows = tbl.getContent();
        for (Object row : rows) {
            Tr tr = (Tr) row;
            List<Object> cells = tr.getContent();
            for (Object cell : cells) {
                Tc td = (Tc) cell;
                td.getContent().add(p);
            }
        }

        mainDocumentPart.getContent().add(tbl);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        wordPackage.save(outputStream);
        return outputStream.toByteArray();
    }
}
