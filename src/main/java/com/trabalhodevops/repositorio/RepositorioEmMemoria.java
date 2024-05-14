package com.trabalhodevops.repositorio;

import com.trabalhodevops.modelo.Pessoa;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class RepositorioEmMemoria {

    private List<Pessoa> pessoasArmazenadas;

    public RepositorioEmMemoria(List<Pessoa> pessoasArmazenadas) {
        this.pessoasArmazenadas = pessoasArmazenadas;
    }

    public Pessoa adicionarPessoa(Pessoa pessoa) {
        pessoasArmazenadas.add(pessoa);
        return pessoa;
    }

    public List<Pessoa> listarPessoas() {
        return pessoasArmazenadas;
    }

    public Pessoa burcarPessoaPorCpf(String cpf) {
        Optional<Pessoa> pessoaEncontrada = pessoasArmazenadas
                .stream()
                .filter(p -> p.getCpf().equalsIgnoreCase(cpf))
                .findFirst();

        if (pessoaEncontrada.isPresent()){
            return pessoaEncontrada.get();
        }

        throw new NoSuchElementException("Nenhuma pessoa encontrada para CPF informado");
    }

    public Pessoa atualizarPessoa(Pessoa pessoaAtualizada) {
        Optional<Pessoa> pessoaEncontrada = pessoasArmazenadas
                .stream()
                .filter(p -> p.getCpf().equalsIgnoreCase(pessoaAtualizada.getCpf()))
                .findFirst();

        if (!pessoaEncontrada.isPresent()){
            throw new NoSuchElementException("Nenhuma pessoa encontrada para CPF informado");
        }

        pessoasArmazenadas.remove(pessoaEncontrada.get());
        pessoasArmazenadas.add(pessoaAtualizada);

        return pessoaAtualizada;
    }

    public void removerPessoa(Pessoa pessoaParaRemover) {
        Optional<Pessoa> pessoaEncontrada = pessoasArmazenadas
                .stream()
                .filter(p -> p.getCpf().equalsIgnoreCase(pessoaParaRemover.getCpf()))
                .findFirst();

        if (!pessoaEncontrada.isPresent()){
            throw new NoSuchElementException("Nenhuma pessoa encontrada para CPF informado");
        }

        pessoasArmazenadas.remove(pessoaEncontrada.get());
    }

}
