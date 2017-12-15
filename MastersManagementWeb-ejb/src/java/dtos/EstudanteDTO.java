package dtos;

import java.io.Serializable;

public class EstudanteDTO extends UtilizadorDTO implements Serializable{
   
    public EstudanteDTO(){
    }

    public EstudanteDTO(
            String username,
            String password,
            String nome,
            String email) {
        super(username, password, nome, email);
    }
    
    @Override
    public void reset() {
        super.reset();
    }
}
