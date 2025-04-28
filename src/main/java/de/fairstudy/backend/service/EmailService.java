package de.fairstudy.backend.service;

import com.mailgun.api.v3.MailgunMessagesApi;
import com.mailgun.api.v3.MailgunStatisticsApi;
import com.mailgun.client.MailgunClient;
import com.mailgun.enums.StatsEventType;
import com.mailgun.model.StatisticsOptions;
import com.mailgun.model.message.Message;
import com.mailgun.model.message.MessageResponse;
import com.mailgun.model.stats.Stats;
import com.mailgun.model.stats.StatsResult;
import com.mailgun.util.EmailUtil;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Service class for sending emails using Mailgun API.
 * <p>
 * This class provides methods to send emails with attachments, as plain text, or as HTML.
 * It also includes a method to retrieve email statistics.
 * <p>
 * Documentation: https://github.com/mailgun/mailgun-java?tab=readme-ov-file#send-email-attachments-example
 */
@Service
public class EmailService {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(EmailService.class);
    @Value("${mailgun.api.key}")
    private String apiKey;
    @Value("${mailgun.domain}")
    private String domain;
    @Value("${mailgun.from.email}")
    private String emailFrom;
    @Value("${mailgun.server.url}")
    private String serverUrl;

    public MessageResponse sendEmailWithAttachments(String emailTo, String subject, String text, List<File> attachments) {
        return sendEmail(emailTo, subject, text, false, 0, attachments);
    }

    public MessageResponse sendEmailAsText(String emailTo, String subject, String text) {
        return sendEmail(emailTo, subject, text, false, 0, null);
    }

    public MessageResponse sendEmailAsHTML(String emailTo, String subject, String text) {
        return sendEmail(emailTo, subject, text, true, 0, null);
    }

    public MessageResponse sendEmailAsHTMLwithDelay(String emailTo, String subject, String text, int delay) {
        return sendEmail(emailTo, subject, text, true, delay, null);
    }

    /**
     * Sends an email using Mailgun API.
     *
     * @param emailTo the recipient's email address
     * @param subject the subject of the email
     * @param text    the content of the email
     * @param html    true if the content is HTML, false if it's plain text
     * @return MessageResponse containing the response from Mailgun
     */
    public MessageResponse sendEmail(String emailTo, String subject, String text, boolean html, int delay, List<File> attachments) {
        try {
            MailgunMessagesApi mailgunMessagesApi = MailgunClient.config(serverUrl, apiKey).createAsyncApi(MailgunMessagesApi.class);

            Message.MessageBuilder builder = Message.builder()
                    .from(emailFrom)
                    .to(emailTo)
                    .sender(EmailUtil.nameWithEmail("Service", emailFrom))
                    .subject(subject);
            if (html) {
                builder.html(text);
            } else {
                builder.text(text);
            }
            if (delay > 0) {
                builder.deliveryTime(ZonedDateTime.now().plusMinutes(delay));
            }
            if (attachments != null && !attachments.isEmpty()) {
                builder.attachment(attachments);
            }
            Message message = builder.build();

            // Send mail asynchronously
            CompletableFuture<MessageResponse> response = mailgunMessagesApi.sendMessageAsync(domain, message);
            logger.info("Email sent successfully to: " + emailTo);
            logger.info("Response: " + response.get().getMessage() + " with ID: " + response.get().getId());

            return response.get();
        } catch (Exception e) {
            logger.error("Failed to send email: " + e.getMessage(), e);
            throw new RuntimeException("Failed to send the mail", e);
        }
    }

    public List<Stats> getStats() {
        try {
            MailgunStatisticsApi mailgunStatisticsApi = MailgunClient.config(serverUrl, apiKey)
                    .createApi(MailgunStatisticsApi.class);
            StatisticsOptions statsOptions = StatisticsOptions.builder()
                    .start(ZonedDateTime.now().minusDays(3))
                    .end(ZonedDateTime.now())
                    .event(Arrays.asList(
                            StatsEventType.ACCEPTED,
                            StatsEventType.DELIVERED,
                            StatsEventType.FAILED,
                            StatsEventType.OPENED,
                            StatsEventType.CLICKED,
                            StatsEventType.UNSUBSCRIBED,
                            StatsEventType.STORED))
                    .build();
            StatsResult result = mailgunStatisticsApi.getDomainStats(domain, statsOptions);
            return result.getStats();
        } catch (Exception e) {
            logger.error("Failed to get stats: " + e.getMessage(), e);
        }
        return null;
    }
}
