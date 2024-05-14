package com.trabalhodevops.modelo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pessoa {

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    private String nome;
    private String sobreNome;
    private Date dataNascimento;
    private String sexo;

    private String cpf;

    public Pessoa(String nome, String sobreNome, String dataNascimento, String sexo, String cpf) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.dataNascimento = converteStringEmData(dataNascimento);
        this.sexo = sexo;
        this.cpf = cpf;
    }

    private Date converteStringEmData(String dataNascimento) {
        try {
            return formatter.parse(dataNascimento);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public void mudarSobrenome(String sobrenomeAtualizado) {
        this.sobreNome = sobrenomeAtualizado;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public String getCpf() {
        return cpf;
    }

}
