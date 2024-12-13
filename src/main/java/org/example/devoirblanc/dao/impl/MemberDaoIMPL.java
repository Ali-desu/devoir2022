package org.example.devoirblanc.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.devoirblanc.dao.MemberDAO;
import org.example.devoirblanc.models.Member;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemberDaoIMPL implements MemberDAO {
    private static final String FILE_PATH = "C:\\Users\\Lenovo\\IdeaProjects\\devoirBlanc\\src\\main\\java\\org\\example\\devoirblanc\\db\\Member.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void inserer(Member member) {
        // Generate a unique ID for the member
        member.setIdentifiant(UUID.randomUUID().toString());

        // Load existing members
        List<Member> members = loadMembers();

        // Add the new member to the list
        members.add(member);

        // Save the updated list back to the file
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), members);
            System.out.println("Member inserted successfully: " + member);
        } catch (IOException e) {
            System.err.println("Failed to save member: " + e.getMessage());
        }
    }

    @Override
    public void chargerListIncidents() {
        // Load and display the list of members
        List<Member> members = loadMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            System.out.println("List of members:");
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }

    // Helper method to load existing members
    private List<Member> loadMembers() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                // If the file doesn't exist, return an empty list
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Member>>() {});
        } catch (IOException e) {
            System.err.println("Failed to load members: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        MemberDaoIMPL memberDao = new MemberDaoIMPL();

        // Test inserer
        Member newMember = new Member();
        newMember.setNom("John");
        newMember.setPrenom("Doe");
        newMember.setEmail("john.doe@example.com");
        newMember.setPhone("1234567890");
        memberDao.inserer(newMember);

        // Test chargerListIncidents
        memberDao.chargerListIncidents();
    }

}
