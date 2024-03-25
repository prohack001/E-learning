package com.example.monprojet.models;

public class Chat {
    String uId, chatId, message;
    Long timestamp;

    public Chat(String uId, String chatId, String message, Long timestamp) {
        this.uId = uId;
        this.chatId = chatId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Chat(String uId, String message, Long timestamp) {
        this.uId = uId;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Chat(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public Chat(){

    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
