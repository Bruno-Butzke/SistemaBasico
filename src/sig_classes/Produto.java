/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sig_classes;

/**
 *
 * @author bruno_butzke
 */
public class Produto {
    private int cod;
    private String descricao;
    private int quant;
    private String uni;
    private int valor;
    
    public Produto(int cod, String descricao, int quant, String uni, int valor){
        this.cod = cod;
        this.descricao = descricao;
        this.quant = quant;
        this.uni = uni;
        this.valor = valor;
    }
    
    public Produto(){
        
    }
    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
