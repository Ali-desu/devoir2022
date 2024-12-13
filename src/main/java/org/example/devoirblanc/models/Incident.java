package org.example.devoirblanc.models;

import lombok.*;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Incident {
    private String reference;
    private String time;
    private String status;
    private String memberId;
}
