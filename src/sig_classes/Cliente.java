package sig_classes;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author bruno_butzke
 */
public class Cliente {
    
    private int cod;
    private String nome;
    private String fone;
    private String email;
    private String ende;
    
    public Cliente(int cod, String nome, String fone, String email, String ende){
        
    this.cod = cod;
    this.nome = nome;
    this.fone = fone;
    this.email = email;
    this.ende = ende;
    }

    public Cliente() {
        
    }
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }
   

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnde() {
        return ende;
    }

    public void setEnde(String ende) {
        this.ende = ende;
    } 
    
}
