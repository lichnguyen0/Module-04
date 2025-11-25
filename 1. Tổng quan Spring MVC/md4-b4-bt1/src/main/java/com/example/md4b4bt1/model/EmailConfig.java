package com.example.md4b4bt1.model;

public class EmailConfig {
    private String language;
    private String pageSize;
    private boolean spamFilter;
    private String signature;

    // Constructors
    public EmailConfig() {}

    public EmailConfig(String language, String pageSize, boolean spamFilter, String signature) {
        this.language = language;
        this.pageSize = pageSize;
        this.spamFilter = spamFilter;
        this.signature = signature;
    }

    // Getters and Setters
    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getPageSize() { return pageSize; }
    public void setPageSize(String pageSize) { this.pageSize = pageSize; }

    public boolean isSpamFilter() { return spamFilter; }
    public void setSpamFilter(boolean spamFilter) { this.spamFilter = spamFilter; }

    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
}
