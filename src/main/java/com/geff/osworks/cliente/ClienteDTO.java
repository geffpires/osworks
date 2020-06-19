package com.geff.osworks.cliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class ClienteDTO {

    private Long id;

    @NotNull
    private String name;

    private String email;

    private String telephone;

    public static ClienteDTO from(Cliente cliente) {
        return ClienteDTO.builder().id(cliente.getId())
                .email(cliente.getEmail())
                .telephone(cliente.getTelephone()).build();
    }
}
