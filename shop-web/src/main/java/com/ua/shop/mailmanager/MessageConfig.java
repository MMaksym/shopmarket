package com.ua.shop.mailmanager;

public class MessageConfig {
    
    private String from;
    private String [] recipients;
    private String subject;
    private EmailType emailType;

    public MessageConfig(String from, String[] recipients, String subject, EmailType emailType) {
        this.from = from;
        this.recipients = recipients;
        this.subject = subject;
        this.emailType = emailType;
    }

    public MessageConfig() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getRecipients() {
        return recipients;
    }

    public void setRecipients(String[] recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }
}
