package com.abdul.worddocument;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
@RestController
//@Path("messages")

@RequestMapping("api")
public class MessagesResource {

    @Autowired
    DocxGenerator docxGenerator;

    @GetMapping("arabic")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM)
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public ResponseEntity<byte[]> createNewDocxMessage() {

        byte[] result = null;

        try {
            result = docxGenerator.generateDocxFileFromTemplate();
        } catch (Exception e) {
            e.printStackTrace();
//            return Response.serverError().build();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        responseHeaders.setContentLength(result.length);
        responseHeaders.set("Content-disposition", "attachment; filename=message.docx");
        return new ResponseEntity<byte[]>(result, responseHeaders, HttpStatus.OK);
    }


    @GetMapping("messages")
//    @Produces(MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<byte[]> createNewDocxMessage(HttpServletResponse response) {

        byte[] result = null;
        UserInformation ui = new UserInformation();
        ui.setFirstName("Mohamed");
        ui.setLastName("Abdul");
        ui.setMessage("message to you");
        ui.setSalutation("Mr.");

        try {
            result = docxGenerator.createDocxFile();
        } catch (Exception e) {
            e.printStackTrace();
//            return Response.serverError().build();
        }

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("charset", "utf-8");
        responseHeaders.setContentType(MediaType.valueOf(MediaType.APPLICATION_OCTET_STREAM_VALUE));
        responseHeaders.setContentLength(result.length);
        responseHeaders.set("Content-disposition", "attachment; filename=message.docx");
        return new ResponseEntity<byte[]>(result, responseHeaders, HttpStatus.OK);

    }
}
