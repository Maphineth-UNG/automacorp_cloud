package com.emse.spring.automacorp.dto;

public record RoomCommand(Integer floor, String name, Double currentTemperature, Double targetTemperature) {
}
