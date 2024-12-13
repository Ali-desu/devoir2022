package org.example.devoirblanc.models;

import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Member {
    private String identifiant = UUID.randomUUID().toString();
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private Set<Incident> incidents;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member that = (Member) o;
        return this.getIdentifiant().equals(that.getIdentifiant());
    }
    @Override
    public int hashCode() {
        return identifiant.hashCode();
    }
}
