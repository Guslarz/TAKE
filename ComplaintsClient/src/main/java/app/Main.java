/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import entities.Complaint;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Guslarz
 */
public class Main {

    private static final String BASE_URL = "http://localhost:8080/Complaints/resources/complaints";

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        Client client = ClientBuilder.newClient()
                .register(new JacksonJaxbJsonProvider(mapper, JacksonJaxbJsonProvider.DEFAULT_ANNOTATIONS));

        String count = client.target(BASE_URL + "/count")
                .request(MediaType.TEXT_PLAIN)
                .get(String.class);
        System.out.println("Count: " + count);

        List<Complaint> allComplaints = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Complaint>>() {
                });
        System.out.println("All complaints: " + allComplaints);
        
        Complaint openComplaint = client.target(BASE_URL)
                .path("402")
                .request(MediaType.APPLICATION_JSON)
                .get(Complaint.class);
        
        System.out.println("Open complaint: " + openComplaint);
        
        openComplaint.setStatus("closed");
        client.target(BASE_URL)
                .path(openComplaint.getId().toString())
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(openComplaint, MediaType.APPLICATION_JSON));
        
        List<Complaint> modifiedAllComplaints = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<Complaint>>() {
                });
        System.out.println("All complaints: " + modifiedAllComplaints);

        client.close();
    }
}
