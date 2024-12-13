package org.example.devoirblanc.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.devoirblanc.models.Incident;
import org.example.devoirblanc.models.Member;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IncidentDaoIMPL {

    private static final String INCIDENT_FILE_PATH = "C:\\Users\\Lenovo\\IdeaProjects\\devoirBlanc\\src\\main\\java\\org\\example\\devoirblanc\\db\\Incidents.json";
    private static final String MEMBER_FILE_PATH = "C:\\Users\\Lenovo\\IdeaProjects\\devoirBlanc\\src\\main\\java\\org\\example\\devoirblanc\\db\\Member.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Insert a single incident
    public void inserer(Incident incident) {
        // Load existing members
        List<Member> members = loadMembers();

        // Check if the memberId exists in the members list
        boolean memberExists = members.stream()
                .anyMatch(member -> member.getIdentifiant().equals(incident.getMemberId()));

        if (!memberExists) {
            System.out.println("Member with ID " + incident.getMemberId() + " does not exist. Cannot add incident.");
            return;
        }

        // Load existing incidents
        Set<Incident> incidents = loadIncidents();

        // Add the new incident
        incidents.add(incident);

        // Save updated incidents list back to the file
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(INCIDENT_FILE_PATH), incidents);
            System.out.println("Incident inserted successfully: " + incident);
        } catch (IOException e) {
            System.err.println("Failed to save incident: " + e.getMessage());
        }
    }

    // Insert a set of incidents (batch insert)
    public void inserer(Set<Incident> incidents) {
        // Load existing members
        List<Member> members = loadMembers();

        // Filter out incidents whose memberId does not exist in the members list
        Set<Incident> validIncidents = new HashSet<>();
        for (Incident incident : incidents) {
            boolean memberExists = members.stream()
                    .anyMatch(member -> member.getIdentifiant().equals(incident.getMemberId()));

            if (memberExists) {
                validIncidents.add(incident);
            } else {
                System.out.println("Member with ID " + incident.getMemberId() + " does not exist. Skipping incident: " + incident);
            }
        }

        // Load existing incidents
        Set<Incident> existingIncidents = loadIncidents();

        // Add valid incidents to the existing incidents set
        existingIncidents.addAll(validIncidents);

        // Save updated incidents list back to the file
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(INCIDENT_FILE_PATH), existingIncidents);
            System.out.println("Batch insert of incidents completed successfully.");
        } catch (IOException e) {
            System.err.println("Failed to save incidents: " + e.getMessage());
        }
    }

    // Helper method to load members from the Member.json file
    private List<Member> loadMembers() {
        try {
            File file = new File(MEMBER_FILE_PATH);
            if (!file.exists()) {
                return List.of();  // Return empty list if the file doesn't exist
            }
            return objectMapper.readValue(file, new TypeReference<List<Member>>() {});
        } catch (IOException e) {
            System.err.println("Failed to load members: " + e.getMessage());
            return List.of();  // Return empty list on error
        }
    }

    // Helper method to load incidents from the Incidents.json file
    private Set<Incident> loadIncidents() {
        try {
            File file = new File(INCIDENT_FILE_PATH);
            if (!file.exists()) {
                return new HashSet<>();  // Return empty set if the file doesn't exist
            }
            return objectMapper.readValue(file, new TypeReference<Set<Incident>>() {});
        } catch (IOException e) {
            System.err.println("Failed to load incidents: " + e.getMessage());
            return new HashSet<>();  // Return empty set on error
        }
    }
}
