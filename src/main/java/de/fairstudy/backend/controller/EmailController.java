package de.fairstudy.backend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mailgun.model.stats.Stats;
import de.fairstudy.backend.service.EmailService;
import de.fairstudy.backend.service.AllUserService;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/email")
public class EmailController {


    private final EmailService emailService;
    private final AllUserService tutorService;

    public EmailController(EmailService emailService, AllUserService tutorService) {
        this.emailService = emailService;
        this.tutorService = tutorService;
    }

    /*
    @GetMapping("/sendAll")
    public String spamAll() {
        var tutors = tutorService.getAllTutors();
        int i = 0;
        String text = "Hallo, ich bin ein Test! Wenn die Nachricht ankommt, ist alles gut! Dann funktioniert es! Dann kann ich meinen diabolischen Plan der Weltherrschaft weiter fröhnen. " + "Schöne Grüße Waldemar von Fairstudy. PS: Man kann auf diese Email nicht antworten.";

        for (var tutor : tutors) {
            var response = emailService.sendEmailAsText(tutor.getEmailAddress(), "Testnachricht von Fairstudy", text);
            try {
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Email sent to: " + tutor.getEmailAddress() + " - " + response.getMessage());
            i++;
        }

        return i + " Emails sent successfully!";
    }
     */

    @PostMapping("/text")
    public String sendTextEmail(@RequestBody JsonNode jsonBody) {

        String to = jsonBody.get("to").asText();
        String subject = jsonBody.get("subject").asText();
        String text = jsonBody.get("text").asText();

        var response = emailService.sendEmailAsText(to, subject, text);
        return "Email sent successfully! " + response.getMessage();
    }

    @PostMapping("/html")
    public String sendHTMLemail(@RequestBody JsonNode jsonBody) {

        String to = jsonBody.get("to").asText();
        String subject = jsonBody.get("subject").asText();
        String text = jsonBody.get("text").asText();

        var response = emailService.sendEmailAsHTML(to, subject, text);
        return "Email sent successfully! " + response.getMessage();
    }

    @PostMapping("/delay")
    public String sendHTMLdelayed(@RequestBody JsonNode jsonBody) {

        String to = jsonBody.get("to").asText();
        String subject = jsonBody.get("subject").asText();
        String text = jsonBody.get("text").asText();
        int delay = jsonBody.get("delay").asInt(1);

        var response = emailService.sendEmailAsHTMLwithDelay(to, subject, text, delay);
        return "Email sent successfully!";
    }

    // NOT TESTED YET
    @PostMapping("/attachments")
    public String sendEmailWithAttachments(@RequestBody JsonNode jsonBody) {

        String to = jsonBody.get("to").asText();
        String subject = jsonBody.get("subject").asText();
        String text = jsonBody.get("text").asText();
        // Assuming attachments are passed as a list of file paths
        List<File> attachments = new ArrayList<>();
        for (JsonNode attachment : jsonBody.get("attachments")) {
            attachments.add(new File(attachment.asText()));
        }

        var response = emailService.sendEmailWithAttachments(to, subject, text, attachments);
        return "Email sent successfully! " + response.getMessage();
    }

    @GetMapping("/stats")
    public String stats() {
        List<Stats> list = emailService.getStats();

        if (list == null || list.isEmpty()) {
            return "No stats available.";
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String jsonArray = "";
        try {
            jsonArray = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return jsonArray;
    }
}