package com.trabalhodevops.servico;

import com.trabalhodevops.modelo.Pessoa;
import com.trabalhodevops.repositorio.RepositorioEmMemoria;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PessoaServicoTest {

    private List<Pessoa> pessoasArmazenadas = new ArrayList<>();
    private RepositorioEmMemoria repositorioEmMemoria = new RepositorioEmMemoria(pessoasArmazenadas);
    private PessoaServico pessoaServico = new PessoaServico(repositorioEmMemoria);

    @Test
    public void adicionarPessoa() {
        Pessoa pessoa = new Pessoa(
                "Everlyn",
                "Borges de Oliveira",
                "01/02/1991",
                "feminino",
                "000.000.000.01"
        );

        pessoaServico.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = pessoaServico.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());
    }

    @Test
    public void deveLancarExcecaoAoTentarAdicionarUmaPessoaComCpfDuplicado(){
        Pessoa pessoaUm = new Pessoa(
                "Everlyn",
                "Borges de Oliveira",
                "01/02/1991",
                "feminino",
                "000.000.000.01"
        );

        Pessoa pessoaDois = new Pessoa(
                "Outra",
                "Pessoa",
                "01/02/1991",
                "feminino",
                "000.000.000.01"
        );

        pessoaServico.adicionarPessoa(pessoaUm);

        Exception exception = assertThrows(InvalidParameterException.class, () -> pessoaServico.adicionarPessoa(pessoaDois));
        assertEquals("Já existe uma pessoa adicionada com o CPF informado", exception.getMessage());
    }

    @Test
    public void atualizarPessoa() {
        Pessoa pessoa = new Pessoa(
                "Everlyn",
                "Borges de Oliveira",
                "01/02/1991",
                "feminino",
                "000.000.000.01"
        );

        Pessoa pessoaAdicionada = pessoaServico.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = pessoaServico.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());

        pessoaAdicionada.mudarSobrenome("Sobrenome atualizado");
        pessoaServico.atualizarPessoa(pessoaAdicionada);

        Pessoa pessoaEncontrada = pessoaServico.burcarPessoaPorCpf("000.000.000.01");

        assertEquals("Sobrenome atualizado", pessoaEncontrada.getSobreNome());
    }

    @Test
    public void removerPessoa() {
        Pessoa pessoa = new Pessoa(
                "Everlyn",
                "Borges de Oliveira",
                "01/02/1991",
                "feminino",
                "000.000.000.01"
        );

        Pessoa pessoaAdicionada = pessoaServico.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = pessoaServico.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());

        pessoaServico.removerPessoa(pessoaAdicionada);

        List<Pessoa> pessoasArmazenadas = pessoaServico.listarPessoas();

        assertNotNull(pessoasArmazenadas);
        assertEquals(0, pessoasArmazenadas.size());
    }

}
