package mx.edu.utez.historiasinteractivas.utils;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import mx.edu.utez.historiasinteractivas.model.User;
import org.apache.commons.codec.binary.Base64;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Set;
import static com.google.api.services.gmail.GmailScopes.GMAIL_SEND;
import static jakarta.mail.Message.RecipientType.TO;

public class GmailSender {
    private static final String TOKENS_DIRECTORY_PATH = "src/main/resources/tokens";
    private static final String FROM = "lagunes.derick@gmail.com";
    private final Gmail service;
    public GmailSender() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("AplicacionesWeb")
                .build();
    }
    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                new InputStreamReader(GmailSender.class.getResourceAsStream("/credentials.json")));
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get(TOKENS_DIRECTORY_PATH ).toFile()))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }
    public void sendMail(String para, String asunto, String mensaje) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(FROM));
        email.addRecipient(TO, new InternetAddress(para));
        email.setSubject(asunto);
        email.setContent(mensaje,"text/html; charset=utf-8");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);
        try {
            service.users().messages().send("me", msg).execute();
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("No se pudo enviar el mensaje: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }
    public void sendEmailRecoverPassword(String userEmail, String link) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(FROM));
        email.addRecipient(TO, new InternetAddress(userEmail));
        email.setSubject("Cambiar contraseña");
        email.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Restablecer Contraseña</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "            background-color: #f4f4f4;\n" +
                "        }\n" +
                "        .container {\n" +
                "            max-width: 600px;\n" +
                "            margin: 50px auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            border-radius: 10px;\n" +
                "            box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            display: flex;\n" +
                "            align-items: center;\n" +
                "            margin-bottom: 20px;\n" +
                "        }\n" +
                "        .logo img {\n" +
                "            max-width: 100px;\n" +
                "            margin-right: 10px;\n" +
                "        }\n" +
                "        .header-text {\n" +
                "            font-size: 24px;\n" +
                "            color: #555555;\n" +
                "        }\n" +
                "        .divider {\n" +
                "            border-top: 2px solid #9332EB;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .message {\n" +
                "            font-size: 16px;\n" +
                "            color: #555555;\n" +
                "            margin-bottom: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .verification-code {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #9332EB;\n" +
                "            margin: 20px 0;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .square {\n" +
                "            display: inline-block;\n" +
                "            background-color: #9332EB;\n" +
                "            color: white;\n" +
                "            padding: 10px 20px;\n" +
                "            margin: 5px;\n" +
                "            border-radius: 5px;\n" +
                "            font-size: 24px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .footer {\n" +
                "            font-size: 14px;\n" +
                "            color: #555555;\n" +
                "            margin-top: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <div class=\"logo\">\n" +
                "                <img src=\"logoHistoriasInteractivas.png\" alt=\"Logo\">\n" +
                "            </div>\n" +
                "            <div class=\"header-text\">\n" +
                "                Story\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"divider\"></div>\n" +
                "        <div class=\"message\">\n" +
                "            Para restablecer su contraseña, haga clic en el botón que aparece a continuación. El enlace se autodestruirá después de tres días.\n" +
                "        </div>\n" +
                "        <div class=\"divider\"></div>\n" +
                "        <a href=\"#\" class=\"button\">Restablecer Contraseña</a>\n" +
                "        <div class=\"footer\">\n" +
                "            Si no desea cambiar su contraseña o no solicitó un restablecimiento, puede ignorar y eliminar este correo electrónico.\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n" + link, "text/html; charset=utf-8");

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);

        try {
            service.users().messages().send("me", msg).execute();
        } catch (GoogleJsonResponseException e) {
            GoogleJsonError error = e.getDetails();
            if (error.getCode() == 403) {
                System.err.println("No se pudo enviar el mensaje: " + e.getDetails());
            } else {
                throw e;
            }
        }
    }
}
