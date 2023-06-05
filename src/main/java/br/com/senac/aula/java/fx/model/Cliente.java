package br.com.senac.aula.java.fx.model;

public class Cliente {

    private int id;
    private String nome;
    private String documento;
    private int rg;

    public Cliente() {

    }

    public Cliente(int id, String nome, String documento, int rg) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.rg = rg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getRg() {
        return rg;
    }

    public void setRg(int rg) {
        this.rg = rg;
    }
}
