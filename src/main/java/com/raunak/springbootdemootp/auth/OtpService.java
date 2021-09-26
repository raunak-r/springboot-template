package com.raunak.springbootdemootp.auth;

import net.bytebuddy.utility.RandomString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
@Transactional
public class OtpService {
    private static final Logger log = LogManager.getLogger(LoginController.class);
    @Autowired CustomerRepo customerRepo;
    @Autowired JavaMailSender mailSender;
//    @Autowired PasswordEncoder passwordEncoder;


    public void generateOneTimePassword(Customer customer) throws UnsupportedEncodingException, MessagingException {
        String OTP = RandomString.make(8);

        //        String encodedOTP = passwordEncoder.encode(OTP);
//        customer.setOneTimePassword(encodedOTP);

        customer.setOneTimePassword(OTP);
        customer.setOtpRequestedTime(new Date());

        customerRepo.save(customer);
        log.info("New OTP generated for customer - {}", customer.toString());

        sendOTPEmail(customer, OTP);
    }

    public void sendOTPEmail(Customer customer, String OTP) throws UnsupportedEncodingException, MessagingException {
        log.info("Sending OTP Email - {}", customer.toString());
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("noreply@company.com", "Your Company");
        helper.setTo(customer.getEmail());

        String subject = "Here's your One Time Password (OTP) - Set to expire in 2 minutes!";

        String content = "<p>Hello " + customer.getFirstName() + "</p>"
                + "<p>For security reason, you're required to use the following "
                + "One Time Password to login:</p>"
                + "<p><b>" + OTP + "</b></p>"
                + "<br>"
                + "<p>Note: this OTP is set to expire in 2 minutes.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
        log.info("Sent OTP Email");
    }

    public boolean validateOTP(String email, String otp) {
        /**
         * Method to check if otp is valid or not.
         */
        log.info("Validating OTP");

        Customer customer = customerRepo.findByEmail(email);
        log.info("Customer object retrieved - {}", customer.toString());

        long currentTimeInMillis = System.currentTimeMillis();
        long otpRequestedTimeInMillis = customer.getOtpRequestedTime().getTime();

        /**
         * otp will not expire until 2 minutes has elapsed.
         * reqTime + 120K should be less than currTime.
         * If reqTime + 120K > currTime => otp expired.
         *
         * reqTime + 120K = TimeTillOTPisValid <= currTime
         */
        Boolean otpValid = (otpRequestedTimeInMillis + customer.OTP_VALID_DURATION) > currentTimeInMillis;

        if (otpValid && customer.getOneTimePassword().equals(otp)) {
            // otp should be equal & shouldn't have expired.
            return true;
        }
        else {
            return false;
        }


    }

}
