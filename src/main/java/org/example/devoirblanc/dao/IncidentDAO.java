package org.example.devoirblanc.dao;

import org.example.devoirblanc.models.Incident;

import java.util.Set;

public interface IncidentDAO {
    void inserer(Incident incident);
    void inserer(Set<Incident> incidents);
}
