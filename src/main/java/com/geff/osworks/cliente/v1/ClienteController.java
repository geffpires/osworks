package com.geff.osworks.cliente.v1;

import com.geff.osworks.cliente.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public List<Cliente> listar() {
        Cliente c1 = new Cliente();
        c1.setId(1L);
        c1.setNome("Geff");
        c1.setTelefone("999");
        c1.setEmail("geff@gmail.com");

        Cliente c2 = new Cliente();
        c2.setId(2L);
        c2.setNome("Maria");
        c2.setTelefone("888");
        c2.setEmail("Maria@gmail.com");
        return Arrays.asList(c1,c2);
    }
}
