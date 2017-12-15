package dtos;

import java.io.Serializable;

public class ProfessorDTO extends UtilizadorDTO implements Serializable {

    public ProfessorDTO() {
    }    
    
    public ProfessorDTO(String username, String password, String name, String email, String office) {
        super(username, password, name, email);
    }
    
    @Override
    public void reset() {
        super.reset();
    }    
}
