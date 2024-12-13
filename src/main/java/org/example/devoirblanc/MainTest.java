package org.example.devoirblanc;

import org.example.devoirblanc.dao.impl.IncidentDaoIMPL;
import org.example.devoirblanc.models.Incident;

import java.util.HashSet;
import java.util.Set;

public class MainTest {

    public static void main(String[] args) {
        // Initialize the IncidentDaoIMPL for inserting incidents
        IncidentDaoIMPL incidentDao = new IncidentDaoIMPL();

        // Create a few test incidents
        Incident incident1 = new Incident("INC123", "2024-12-13 10:00", "Active", "4535ba51-3c39-4de2-9ce5-98eb9b43e54e");
        Incident incident2 = new Incident("INC124", "2024-12-14 11:00", "Closed", "67890");
        Incident incident3 = new Incident("INC125", "2024-12-15 12:00", "Pending", "12345");

        // Add single incidents using inserer() method
        incidentDao.inserer(incident1);
        incidentDao.inserer(incident2);

        // Create a set of incidents and add them all at once using inserer() method for batch insert
        Set<Incident> incidentSet = new HashSet<>();
        incidentSet.add(incident3);
        incidentDao.inserer(incidentSet);

        System.out.println("Test completed. Check Incidents.json for the result.");
    }
}
