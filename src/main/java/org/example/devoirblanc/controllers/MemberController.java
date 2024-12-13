package org.example.devoirblanc.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.devoirblanc.dao.impl.MemberDaoIMPL;
import org.example.devoirblanc.models.Member;

public class MemberController {
    @FXML
    private TextField nomField, prenomField, emailField, phoneField;

    @FXML
    private Button insertBtn;

    private final MemberDaoIMPL memberDao = new MemberDaoIMPL();

    @FXML
    public void initialize() {
        // Check if the fields are properly loaded
        if (nomField == null || prenomField == null || emailField == null || phoneField == null || insertBtn == null) {
            System.out.println("One or more FXML components are null! Check your fx:id bindings in the FXML file.");
        } else {
            System.out.println("All FXML components loaded successfully!");
        }
    }

    @FXML
    public void HandleInsertMember() {
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || emailField.getText().isEmpty() || phoneField.getText().isEmpty()) {
            System.out.println("All fields must be filled in!");
        } else {
            // Create a new Member object and populate it with data from the fields
            Member member = new Member();
            member.setNom(nomField.getText());
            member.setPrenom(prenomField.getText());
            member.setEmail(emailField.getText());
            member.setPhone(phoneField.getText());

            // Use the DAO to insert the member into the JSON file
            memberDao.inserer(member);

            // Clear the fields after insertion
            nomField.clear();
            prenomField.clear();
            emailField.clear();
            phoneField.clear();


        }
    }
}
