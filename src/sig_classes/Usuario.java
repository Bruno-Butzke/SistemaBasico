/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_classes;

/**
 *
 * @author bruno_butzke
 */
public class Usuario {

    private int iduser;
    private String nmUser;
    private String pwUser;
    private String tpUser;
    private char pvUser;
    
     public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getNmUser() {
        return nmUser;
    }

    public void setNmUser(String nmUser) {
        this.nmUser = nmUser;
    }

    public String getPwUser() {
        return pwUser;
    }

    public void setPwUser(String pwUser) {
        this.pwUser = pwUser;
    }

    public String getTpUser() {
        return tpUser;
    }

    public void setTpUser(String tpUser) {
        this.tpUser = tpUser;
    }

    public char getPvUser() {
        return pvUser;
    }

    public void setPvUser(char pvUser) {
        this.pvUser = pvUser;
    }
}
