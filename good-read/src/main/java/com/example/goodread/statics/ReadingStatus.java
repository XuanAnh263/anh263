package com.example.goodread.statics;

import lombok.Getter;

@Getter
public enum ReadingStatus {
    WANT_TO_READ("To-read"), READING("Reading"), READ("Read");

    String name;

    ReadingStatus(String name) {
        this.name = name;
    }
}
