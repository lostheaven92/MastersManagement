/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.persistence.Entity;

/**
 *
 * @author Alberto
 */
@Entity
public class MembroCCP extends Utilizador{
    
    public MembroCCP() {
    }

    public MembroCCP(String username, String name, String password, String email) {
        super(username, name, password, email);
    }
    
    
}
