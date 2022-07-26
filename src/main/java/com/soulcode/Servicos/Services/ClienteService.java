package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Cliente;
import com.soulcode.Servicos.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    //aqui fazemos a injeção de depência
    @Autowired
    ClienteRepository clienteRepository;

    // findAll (método da Spring Data) - busca todos os registros
    @Cacheable("clientesCache")
    public List<Cliente> mostrarTodosClientes(){
        return clienteRepository.findAll();
    }

    // findById - busca um cliente específico pelo seu id
    @CachePut(value = "clientesCache", key = "idCliente")
    public Cliente mostrarUmCliente(Integer idCliente) {
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);
        return cliente.orElseThrow();
    }

    @CachePut(value = "clientesCache", key = "#cliente.idClienete")
    public Cliente inserirCliente(Cliente cliente) {
        //por precaução vamos limpar o campo de id do cliente
        cliente.setIdCliente(null);
        return clienteRepository.save(cliente);

    }

    // editar um cliente já cadastrado
    @CachePut(value = "clientesCache", key = "#cliente.idClienete")
    public Cliente editarCliente (Cliente cliente) {
        mostrarUmCliente(cliente.getIdCliente());
        return clienteRepository.save(cliente);
    }

    // deleteById  - excluir um cliente pelo seu id
    @CacheEvict(value = "clienteCache", key = "#idCliente", allEntries = true)
    public void excluirCliente (Integer idCliente) {
        mostrarUmCliente(idCliente);
        clienteRepository.deleteById(idCliente);
    }
}