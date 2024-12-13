package org.example.devoirblanc.dao;

import org.example.devoirblanc.models.Member;

public interface MemberDAO {
    void inserer(Member member);
    void chargerListIncidents();
}
