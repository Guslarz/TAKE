/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

/**
 *
 * @author Guslarz
 */
public class Complaint {
    
    private Long id;
    private LocalDate complaintDate;
    private String complaintText;
    private String author;
    private String status;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the complaintDate
     */
    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    /**
     * @param complaintDate the complaintDate to set
     */
    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    /**
     * @return the complaintText
     */
    public String getComplaintText() {
        return complaintText;
    }

    /**
     * @param complaintText the complaintText to set
     */
    public void setComplaintText(String complaintText) {
        this.complaintText = complaintText;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Complaint{" + "id=" + id + ", complaintDate=" + complaintDate + ", complaintText=" + complaintText + ", author=" + author + ", status=" + status + '}';
    }
    
}
