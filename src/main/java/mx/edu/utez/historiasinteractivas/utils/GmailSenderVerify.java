package mx.edu.utez.historiasinteractivas.utils;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
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

public class GmailSenderVerify {
    private static final String TOKENS_DIRECTORY_PATH = System.getProperty("user.home") + "/tokens";
    private static final String FROM = "HistoriasInteractivasutez@gmail.com";
    private final Gmail service;

    public GmailSenderVerify() throws Exception {
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        service = new Gmail.Builder(httpTransport, jsonFactory, getCredentials(httpTransport, jsonFactory))
                .setApplicationName("AplicacionesWeb")
                .build();
    }

    private static Credential getCredentials(final NetHttpTransport httpTransport, GsonFactory jsonFactory)
            throws IOException {
        // Carga el archivo de credenciales
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
                new InputStreamReader(GmailSenderVerify.class.getResourceAsStream("/credentials.json")));

        // Construye el flujo de autorización
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, jsonFactory, clientSecrets, Set.of(GMAIL_SEND))
                .setDataStoreFactory(new FileDataStoreFactory(Paths.get(TOKENS_DIRECTORY_PATH).toFile()))
                .setAccessType("offline")
                .build();

        // Receptor local para manejar la respuesta de Google
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    public void sendVerificationEmail(String recipientEmail, String verificationCode) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(FROM));
        email.addRecipient(TO, new InternetAddress(recipientEmail));
        email.setSubject("Código de verificación de tu cuenta");
        email.setContent("<!DOCTYPE html>\n" +
                "<html lang=\"es\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Código de Verificación</title>\n" +
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
                "            Código de Verificación para tu Cuenta\n" +
                "        </div>\n" +
                "        <div class=\"message\">\n" +
                "            Para completar tu proceso de registro y activar tu cuenta, por favor utiliza el siguiente código de verificación:\n" +
                "        </div>\n" +
                "        <div class=\"verification-code\">\n" +
                "            <div style=\"text-align: center;\">\n" +
                "                <div class=\"square\">{{verificationCode.charAt(0)}}</div>\n" +
                "                <div class=\"square\">{{verificationCode.charAt(1)}}</div>\n" +
                "                <div class=\"square\">{{verificationCode.charAt(2)}}</div>\n" +
                "                <div class=\"square\">{{verificationCode.charAt(3)}}</div>\n" +
                "                <div class=\"square\">{{verificationCode.charAt(4)}}</div>\n" +
                "                <div class=\"square\">{{verificationCode.charAt(5)}}</div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            Si no has solicitado este código, por favor ignora este mensaje.\n" +
                "        </div>\n" +
                "        <div class=\"footer\">\n" +
                "            Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en contactar a nuestro equipo de soporte.<br>\n" +
                "            Gracias por elegirnos.\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n" + verificationCode, "text/html; charset=utf-8");

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
