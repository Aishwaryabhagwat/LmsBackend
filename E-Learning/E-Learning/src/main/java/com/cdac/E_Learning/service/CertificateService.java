package com.cdac.E_Learning.service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Date;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdac.E_Learning.pojo.Course;
import com.cdac.E_Learning.pojo.User;
import com.cdac.E_Learning.repo.ICertificateRepo;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;



@Service
public class CertificateService {

    @Autowired
    private UserVideoProgressServiceImpl videoProgressService;

    @Autowired
    private QuizService quizService;

    @Autowired
    private ICertificateRepo certificateRepo;

    public ByteArrayInputStream generateCertificate(User user, Course course) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 26, BaseColor.DARK_GRAY);
            Paragraph title = new Paragraph("Certificate of Completion", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // Certificate text
            Font textFont = FontFactory.getFont(FontFactory.HELVETICA, 16, BaseColor.BLACK);
            Paragraph text = new Paragraph(
                    "This is to certify that " + user.getName() +
                    " has successfully completed the course \"" + course.getName() + "\"."
            );
            text.setAlignment(Element.ALIGN_CENTER);
            document.add(text);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            // Completion Date
            Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 12, BaseColor.GRAY);
            Paragraph date = new Paragraph("Date: " + new Date(), dateFont);
            date.setAlignment(Element.ALIGN_RIGHT);
            document.add(date);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
