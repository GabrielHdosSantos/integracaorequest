package com.teste.integracao.controller;

import com.teste.integracao.dto.EnderecoDTO;
import com.teste.integracao.dto.GabrielDTO;
import com.teste.integracao.dto.PessoaDTO2;
import com.teste.integracao.modelo.Endereco;
import com.teste.integracao.modelo.Gabriel;
import com.teste.integracao.dto.Pessoa;
import com.teste.integracao.repo.EnderecoRepo;
import com.teste.integracao.repo.GabrielRepo;
import com.teste.integracao.repo.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pessoa")
public class Controller {

    @Autowired
    private Repo repository;

    @Autowired
    private EnderecoRepo repo;

    @Autowired
    private GabrielRepo gabrielRepo;

    @PostMapping()
    public ResponseEntity<Pessoa> findById(@RequestBody PessoaDTO2 pessoaDTO) {
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("http").host("localhost:9091").path("/" + pessoaDTO.id).build();

        ResponseEntity<Pessoa> newPessoa = template.getForEntity(uri.toString(), Pessoa.class);

        return newPessoa;
    }

    @PostMapping("/viacep")
    public ResponseEntity<Endereco> enderecoviacep(@RequestBody EnderecoDTO endereco) {
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https").host("viacep.com.br").path("/ws/"+endereco.cep+"/json/").build();

        ResponseEntity<Endereco> newEndereco = template.getForEntity(uri.toString(), Endereco.class);

        repo.save(newEndereco.getBody());

        return newEndereco;
    }

    @PostMapping("/add")
    public ResponseEntity<Gabriel> findById(@RequestBody GabrielDTO gabrielDTO) {
        RestTemplate template = new RestTemplate();

        Gabriel gabriel = new Gabriel(gabrielDTO.nome, gabrielDTO.sobrenome);

        ResponseEntity<Gabriel> gabrielsave = new RestTemplate()
                .exchange("http://localhost:9091/add",
                        HttpMethod.POST, new HttpEntity<>(gabriel), Gabriel.class);

        return gabrielsave;
    }




}
