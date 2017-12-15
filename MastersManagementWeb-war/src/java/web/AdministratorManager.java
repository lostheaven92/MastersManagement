/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import ejbs.EstudanteBean;
import entities.Estudante;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AdministratorManager implements Serializable{
    
    @EJB
    EstudanteBean estudanteBean;

    public AdministratorManager() {
    }
    
    public Collection<Estudante> getAllEstudantes(){
        return estudanteBean.getAllREST();
    }
}
