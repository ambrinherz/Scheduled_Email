## Summary

This Springboot project sends an email at a scheduled time every day. For eg. the code runs a scheduled cron job everyday at 10 am, which in turn sends an email to the designated "To email".

In this project I wanted to set a reminder at 10 am everyday.

## Testing

I created a unit test class called "EmailServiceTest" which tests the behavior of two cases 
- i) Email sent successfully 
- ii) Invalid "To send" email address

To avoid repetitive emails, I created a mock behavior of the actual implementation. This way I was able to test the expected behavior without actually sending multiple emails.

## Design

Unique design features in this project include:

- i) Creation of a Singleton Mailer class which holds all the functionality required to send email.
- ii) Using Consumer functional interface of Java 8 in the Mailer class. Eg. sendEmail(Consumer<Email> emailBuilder)  method. This method accepts consumer object of "Email" class,does some action but returns nothing.
- iii) Using enum to create constants "SUCCESS" and "FAILURE" in ConfirmationStatusType class.
- iv) Using logger to log the status once email is sent.
- v) Created Environment Variables to be used at run/debug time by adding them in the configuration of "Application" class which runs the SpringBoot application. This is used to secure the email and port configurations used in the project.

## Usage

Once you download the complete code, you should be able to open it on IntelliJ IDEA. 

-You need to set up Environment variables for the "Application" class or you could manually set values for Strings "FROM_EMAIL,USERNAME,PASSWORD,SMTP_SERVER,PORT" in the MailerServiceImpl class.

-You should also set up the value of String "toEmail" and the correct time you need reminder by modifying this line,  @Scheduled(cron = "0 0 10 * * ?") in the PillScheduledTask class

-You could also change the subject and body of email in the PillScheduledTask class
