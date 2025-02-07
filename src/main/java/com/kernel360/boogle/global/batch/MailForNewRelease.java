//package com.kernel360.boogle.global.batch;
//
//import com.kernel360.boogle.book.db.BookEntity;
//import com.kernel360.boogle.book.db.BookRepository;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import jakarta.mail.*;
//import jakarta.mail.internet.InternetAddress;
//import jakarta.mail.internet.MimeMessage;
//
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Properties;
//
//@Component
//public class MailForNewRelease {
//
//    @Value("${mail.batch.email}")
//    private String userEmail;
//
//    @Value("${mail.batch.password}")
//    private String userPassword;
//
//    private static final String SMTP_HOST = "smtp.gmail.com";
//    private static final int TLS_PORT = 587;
//
//    private final BookRepository bookRepository;
//
//    public MailForNewRelease(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
//
//    public void send() throws MessagingException {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", SMTP_HOST);
//        props.put("mail.smtp.port", TLS_PORT);
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.starttls.required", "true");
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
//        props.put("jdk.tls.client.protocols", "TLSv1.2");
//
//        // 메일 세션 생성
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(userEmail, userPassword);
//            }
//        });
//
//        // 메일 메시지 생성
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(userEmail));
//
//        // 수신자 메일 목록
//        String[] recipientAddresses = {
//                "receiver@gmail.com"
//        };
//
//        InternetAddress[] recipientInternetAddresses = new InternetAddress[recipientAddresses.length];
//        for (int i = 0; i < recipientAddresses.length; i++) {
//            recipientInternetAddresses[i] = new InternetAddress(recipientAddresses[i]);
//        }
//
//        message.setRecipients(Message.RecipientType.TO, recipientInternetAddresses);
//
//        LocalDate today = LocalDate.now();
//        int thisYear = today.getYear();
//        int thisMonth = today.getMonthValue();
//
//        // 제목
//        message.setSubject(String.format("[%d년 %d월 신간도서 알림] 안녕하세요. Boogle팀 Ross입니다.", thisYear, thisMonth));
//
//        // 신간 도서 조회
//        List<BookEntity> monthlyBooks = bookRepository.findAllByPublishDateBetween(today.minusMonths(1), today);
//
//        // 이메일 내용 구성
//        StringBuilder stringBuilder = new StringBuilder();
//        for (BookEntity book : monthlyBooks) {
//            stringBuilder.append("제목: ").append(book.getTitle()).append("\n")
//                    .append("지은이: ").append(book.getAuthor()).append("\n")
//                    .append("발행일: ").append(book.getPublishDate()).append("\n")
//                    .append("ISBN: ").append(book.getIsbn()).append("\n\n");
//        }
//
//        message.setText(stringBuilder.toString());
//
//        // 메일 보내기
//        Transport.send(message);
//
//        System.out.println("메일 발송 완료!");
//    }
//}