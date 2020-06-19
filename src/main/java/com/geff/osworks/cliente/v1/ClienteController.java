package com.geff.osworks.cliente.v1;

import com.geff.osworks.cliente.Cliente;
import com.geff.osworks.cliente.ClienteDTO;
import com.geff.osworks.cliente.ClienteRepository;
import com.geff.osworks.exceptions.ClienteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Cliente getCliente(@PathVariable(name = "id") Long clienteId){
        return clienteRepository.findById(clienteId).orElseThrow(ClienteNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente saveCliente(@Valid @RequestBody ClienteDTO newCliente) {
        return clienteRepository.save(Cliente.to(newCliente));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente editCliente(@Valid @RequestBody ClienteDTO editCliente, @PathVariable(name = "id") Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(ClienteNotFoundException::new);
        cliente.setName(editCliente.getName());
        cliente.setTelephone(editCliente.getTelephone());
        cliente.setEmail(editCliente.getEmail());

        clienteRepository.save(cliente);
        return cliente;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable(name = "id") Long idClinte) {
        if (!clienteRepository.existsById(idClinte)) {
            throw new ClienteNotFoundException();
        }
        clienteRepository.deleteById(idClinte);
    }
}
