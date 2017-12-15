/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.EstudanteDTO;
import ejbs.EstudanteBean;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

@ManagedBean
@SessionScoped
public class AdministratorManager implements Serializable{
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    @EJB
    private EstudanteBean estudanteBean;
    
    private Client client;

    public AdministratorManager() {
        client = ClientBuilder.newClient();
    }
    
    public Collection<EstudanteDTO> getAllEstudantes(){
        return estudanteBean.getAllREST();
    }
    
    public Collection<EstudanteDTO> getAllEstudantesREST() {
        try {
            return estudanteBean.getAllREST();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
    }
}
