/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Guslarz
 */
@Named(value = "formBean")
@RequestScoped
public class FormBean {
    
    private Integer lhs;
    private Integer rhs; 
    private Integer result;

    /**
     * Creates a new instance of FormBean
     */
    public FormBean() {
    }
    
    public void calculate() {
        result = lhs + rhs;
        String message = String.format("%d + %d = %d", lhs, rhs, result);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

    /**
     * @return the lhs
     */
    public Integer getLhs() {
        return lhs;
    }

    /**
     * @param lhs the lhs to set
     */
    public void setLhs(Integer lhs) {
        this.lhs = lhs;
    }

    /**
     * @return the rhs
     */
    public Integer getRhs() {
        return rhs;
    }

    /**
     * @param rhs the rhs to set
     */
    public void setRhs(Integer rhs) {
        this.rhs = rhs;
    }

    /**
     * @return the result
     */
    public Integer getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(Integer result) {
        this.result = result;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return new Date();
    }
}
