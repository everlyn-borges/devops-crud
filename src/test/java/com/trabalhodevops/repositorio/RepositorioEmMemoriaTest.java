package com.trabalhodevops.repositorio;

import com.trabalhodevops.modelo.Pessoa;
import com.trabalhodevops.repositorio.RepositorioEmMemoria;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RepositorioEmMemoriaTest {

    private List<Pessoa> pessoasArmazenadas = new ArrayList<>();

    private RepositorioEmMemoria repositorioEmMemoria = new RepositorioEmMemoria(pessoasArmazenadas);

    @Test
    public void adicionarPessoa() {
        Pessoa pessoa = new Pessoa(
            "Everlyn",
            "Borges de Oliveira",
            "01/02/1991",
            "feminino",
            "000.000.000.01"
        );

        repositorioEmMemoria.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = repositorioEmMemoria.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());
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

        Pessoa pessoaAdicionada = repositorioEmMemoria.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = repositorioEmMemoria.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());

        pessoaAdicionada.mudarSobrenome("Sobrenome atualizado");
        repositorioEmMemoria.atualizarPessoa(pessoaAdicionada);

        Pessoa pessoaEncontrada = repositorioEmMemoria.burcarPessoaPorCpf("000.000.000.01");

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

        Pessoa pessoaAdicionada = repositorioEmMemoria.adicionarPessoa(pessoa);

        List<Pessoa> pessoasAdicionadas = repositorioEmMemoria.listarPessoas();

        assertNotNull(pessoasAdicionadas);
        assertEquals(1, pessoasAdicionadas.size());

        repositorioEmMemoria.removerPessoa(pessoaAdicionada);

        List<Pessoa> pessoasArmazenadas = repositorioEmMemoria.listarPessoas();

        assertNotNull(pessoasArmazenadas);
        assertEquals(0, pessoasArmazenadas.size());

    }

}
