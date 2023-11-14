package com.hypham.learn.clients.dto;

import lombok.Builder;

@Builder
public record CustomerRegisterNotiRequest(String userName, Integer userId, String userEmail) {}
