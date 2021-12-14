package com.ray.videos.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
