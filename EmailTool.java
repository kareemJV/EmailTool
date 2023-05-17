import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;


public class EmailTool {

    static Scanner scanner = new Scanner(System.in);
    static Session session;
    static String absender;
    static String passwort;


    public static void Senden() {   //Der Sinn, warum hier zwei Methoden sind ist, damit man nicht immer wieder die eigene Email eingeben muss, falls man an mehreren Adressen
                                    //eine Email gleichzeitig senden möchte über eine for-Schleife zum Beispiel
        System.out.println("Geben Sie Ihre Email ein:");
        absender = scanner.next();
        System.out.println("Passwort eingeben: ");
        passwort = scanner.next();
        final String username = absender;
        final String password = passwort; //Am besten ein App Passwort generieren,falls die zweistufige authentifizierung aktiviert ist.

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

         session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
    }


    public static void schicken() { //Hier wird die Nachricht sowie die Email des Empfängers festgelegt
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(absender));
            System.out.println("Geben Sie die Email des Empfängers ein");
            String empfänger = scanner.next();
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(empfänger));

            System.out.println("Es wird die Standard Nachricht gesendent!");
            System.out.println("Sehr geehrte Damen und Herren,\nanbei finden Sie meine Bewerbungsunterlagen für die Oben genannten Stelle.");



            message.setSubject("Bewerbung als Werktstudent");
            message.setText("Sehr geehrte Damen und Herren,\nanbei finden Sie meine Bewerbungsunterlagen für die Oben genannten Stelle.");


            //Hier werden Dokumente angehängt

            MimeBodyPart lebenslauf = new MimeBodyPart();
            MimeBodyPart zeugnis = new MimeBodyPart();
            MimeBodyPart zeugnis1 = new MimeBodyPart();
            MimeBodyPart zertifikat = new MimeBodyPart();

            lebenslauf.attachFile("Pfad eingeben");// Dokument Pfad
            zeugnis.attachFile("Pfad eingeben");
            zeugnis1.attachFile("Pfad eingeben");
            zertifikat.attachFile("Pfad eingeben");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(lebenslauf);
            multipart.addBodyPart(zeugnis);
            multipart.addBodyPart(zeugnis1);
            multipart.addBodyPart(zertifikat);
            message.setContent(multipart);

            Transport.send(message);
            System.out.println("Die E-Mail wurde erfolgreich versendet.");

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


