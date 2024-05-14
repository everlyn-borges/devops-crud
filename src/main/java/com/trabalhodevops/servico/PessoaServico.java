package com.trabalhodevops.servico;

import com.trabalhodevops.modelo.Pessoa;
import com.trabalhodevops.repositorio.RepositorioEmMemoria;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

public class PessoaServico {

    private RepositorioEmMemoria repositorioEmMemoria;

    public PessoaServico(RepositorioEmMemoria repositorioEmMemoria) {
        this.repositorioEmMemoria = repositorioEmMemoria;
    }

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        Optional<Pessoa> pessoaEncontrada = repositorioEmMemoria
                .listarPessoas()
                .stream()
                .filter(p -> p.getCpf().equalsIgnoreCase(pessoa.getCpf()))
                .findFirst();

        if (pessoaEncontrada.isPresent()){
            throw new InvalidParameterException("Já existe uma pessoa adicionada com o CPF informado");
        }

        return repositorioEmMemoria.adicionarPessoa(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return repositorioEmMemoria.listarPessoas();
    }

    public Pessoa burcarPessoaPorCpf(String cpf) {
        return repositorioEmMemoria.burcarPessoaPorCpf(cpf);
    }

    public Pessoa atualizarPessoa(Pessoa pessoaParaAtualizar) {
        return repositorioEmMemoria.atualizarPessoa(pessoaParaAtualizar);
    }

    public void removerPessoa(Pessoa pessoaParaRemover) {
        repositorioEmMemoria.removerPessoa(pessoaParaRemover);
    }
}
