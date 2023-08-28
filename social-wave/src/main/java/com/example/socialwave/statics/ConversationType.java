package com.example.socialwave.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConversationType {
    PERSONAL("Personal"),
    GROUP("Group");
    String type;
}
