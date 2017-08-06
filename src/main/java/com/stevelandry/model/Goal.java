package com.stevelandry.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Goal {

    private String scorerName;
    private int minutes;
}
