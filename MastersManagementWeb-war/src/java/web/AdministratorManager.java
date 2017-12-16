/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.EstudanteDTO;
import ejbs.EstudanteBean;
import exceptions.EntityAlreadyExistsException;
import exceptions.MyConstraintViolationException;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import util.URILookup;

@ManagedBean
@SessionScoped
public class AdministratorManager implements Serializable{
    // ---------------------------- ERROR MESSAGES ----------------------------
    private static final String CONST_ERR_OTHER = "Erro inesperado! Tente novamente mais tarde!";
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    @EJB
    private EstudanteBean estudanteBean;
    
    private EstudanteDTO novoEstudante;
    private Client client;
    private UIComponent component;
    
    public AdministratorManager() {
        novoEstudante = new EstudanteDTO();
        client = ClientBuilder.newClient();
    }
    
    public Collection<EstudanteDTO> getAllEstudantes(){
        return estudanteBean.getAllREST();
    }
    
    public Collection<EstudanteDTO> getAllEstudantesREST() {
        try {
            return estudanteBean.getAllREST();
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
    }

    public String criarEstudanteREST() {
        try {
            client.target(URILookup.getBaseAPI())
                    .path("/students/create")
                    .request(MediaType.APPLICATION_XML)
                    .post(Entity.xml(novoEstudante));
        
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, "Unexpected error! Try again latter!", logger);
            return null;
        }
        return "listar_estudantes?faces-redirect=true";
    }
    
    public String criarEstudante() {
        try {
            estudanteBean.criar(novoEstudante.getUsername(),novoEstudante.getPassword(),novoEstudante.getNome(),novoEstudante.getEmail());
            novoEstudante.reset();
        } catch (EntityAlreadyExistsException | MyConstraintViolationException e) {
            FacesExceptionHandler.handleException(e, e.getMessage(), component, logger);
            return null;
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, component, logger);
            return null;
        }
        
        return "listar_estudantes?faces-redirect=true";
    }
    
    public EstudanteDTO getNovoEstudante() {
        return novoEstudante;
    }

    public void setNovoEstudante(EstudanteDTO novoEstudante) {
        this.novoEstudante = novoEstudante;
    }

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
