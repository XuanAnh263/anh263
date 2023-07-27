package com.example.socialwave.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ConversationStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    ARCHIVED("Archived"),
    PENDING("Pending"),
    CLOSED("Closed"),
    DELETED("Deleted");
    String status;
}
