/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import dtos.EstudanteDTO;
import dtos.InstituicaoDTO;
import dtos.ProfessorDTO;
import ejbs.EstudanteBean;
import ejbs.ProfessorBean;
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
import javax.faces.event.ActionEvent;
import javax.faces.component.UIParameter;
import javax.ws.rs.core.GenericType;

@ManagedBean
@SessionScoped
public class AdministratorManager implements Serializable{
    // ---------------------------- ERROR MESSAGES ----------------------------
    private static final String CONST_ERR_OTHER = "Erro inesperado! Tente novamente mais tarde!";
    
    private static final String CONST_LISTAR_URL = "listar?faces-redirect=true";
    
    private static final Logger logger = Logger.getLogger("web.AdministratorManager");
    
    @EJB
    private EstudanteBean estudanteBean;
    
    @EJB
    private ProfessorBean professorBean;
    
    
    private EstudanteDTO novoEstudante;
    private EstudanteDTO estudanteAtual;
    private ProfessorDTO novoProfessor;
    private ProfessorDTO professorAtual;
    private Client client;
    private UIComponent component;
    
    public AdministratorManager() {
        novoEstudante = new EstudanteDTO();
        client = ClientBuilder.newClient();
    }
    
    public void eliminarEstudante(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("studentUsername");
            String username = param.getValue().toString();
            
            client.target(URILookup.getBaseAPI())
                    .path("/students/remove")
                    .path(username)
                    .request(MediaType.APPLICATION_XML)
                    .delete(String.class);
            
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
        }
    }
    
    public void eliminarProfessor(ActionEvent event){
        try {
            UIParameter param = (UIParameter) event.getComponent().findComponent("professorUsername");
            String username = param.getValue().toString();
            
            client.target(URILookup.getBaseAPI())
                    .path("/professores/remove")
                    .path(username)
                    .request(MediaType.APPLICATION_XML)
                    .delete(String.class);
            
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
        }
    }

    public String criarEstudante() {
        try {
            client.target(URILookup.getBaseAPI())
                    .path("/students/create")
                    .request(MediaType.APPLICATION_XML)
                    .post(Entity.xml(novoEstudante));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
        return CONST_LISTAR_URL;
    }
    
    public String criarProfessor() {
        try {
            client.target(URILookup.getBaseAPI())
                    .path("/professores/create")
                    .request(MediaType.APPLICATION_XML)
                    .post(Entity.xml(novoEstudante));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
        return CONST_LISTAR_URL;
    }
    
    public String editarEstudante() {
        try {
            client.target(URILookup.getBaseAPI())
                    .path("/students/update")
                    .request(MediaType.APPLICATION_XML)
                    .put(Entity.xml(estudanteAtual));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }

        return CONST_LISTAR_URL;
    }
    
    public String editarProfessor() {
        try {
            client.target(URILookup.getBaseAPI())
                    .path("/professores/update")
                    .request(MediaType.APPLICATION_XML)
                    .put(Entity.xml(estudanteAtual));
        } catch (Exception e) {
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }

        return CONST_LISTAR_URL;
    }
    
    public Collection<EstudanteDTO> getAllEstudantes() {
        try{
            GenericType<Collection<EstudanteDTO>> lista = new GenericType<Collection<EstudanteDTO>>() {};
            Collection<EstudanteDTO> estudantes = client.target(URILookup.getBaseAPI())
                .path("/students/all")
                .request(MediaType.APPLICATION_XML)
                .get(lista);
            return estudantes;
        } catch(Exception e){
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
    }
    
    public Collection<ProfessorDTO> getAllProfessores() {
        try{
            GenericType<Collection<ProfessorDTO>> lista = new GenericType<Collection<ProfessorDTO>>() {};
            Collection<ProfessorDTO> professores = client.target(URILookup.getBaseAPI())
                .path("/professores/all")
                .request(MediaType.APPLICATION_XML)
                .get(lista);
            return professores;
        } catch(Exception e){
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
    }
    
        public Collection<InstituicaoDTO> getAllInstituicoes() {
        try{
            GenericType<Collection<InstituicaoDTO>> lista = new GenericType<Collection<InstituicaoDTO>>() {};
            Collection<InstituicaoDTO> instituicoes = client.target(URILookup.getBaseAPI())
                .path("/instituicoes/all")
                .request(MediaType.APPLICATION_XML)
                .get(lista);
            return instituicoes;
        } catch(Exception e){
            FacesExceptionHandler.handleException(e, CONST_ERR_OTHER, logger);
            return null;
        }
    }
    
    
    public EstudanteDTO getEstudanteAtual() {
        return estudanteAtual;
    }

    public void setEstudanteAtual(EstudanteDTO estudanteAtual) {
        this.estudanteAtual = estudanteAtual;
    }
    
    public EstudanteDTO getNovoEstudante() {
        return novoEstudante;
    }

    public void setNovoEstudante(EstudanteDTO novoEstudante) {
        this.novoEstudante = novoEstudante;
    }

    public ProfessorDTO getNovoProfessor() {
        return novoProfessor;
    }

    public void setNovoProfessor(ProfessorDTO novoProfessor) {
        this.novoProfessor = novoProfessor;
    }

    public ProfessorDTO getProfessorAtual() {
        return professorAtual;
    }

    public void setProfessorAtual(ProfessorDTO professorAtual) {
        this.professorAtual = professorAtual;
    }

    
    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
}
