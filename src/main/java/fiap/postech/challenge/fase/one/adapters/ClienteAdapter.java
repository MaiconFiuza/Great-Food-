package fiap.postech.challenge.fase.one.adapters;

import fiap.postech.challenge.fase.one.dtos.request.ClienteCreateRequestDTO;
import fiap.postech.challenge.fase.one.entities.Cliente;

import java.util.Date;

public class ClienteAdapter {
    public Cliente transform(ClienteCreateRequestDTO clienteCreateRequestDTO, Date ultimaAlteracao) {

        Cliente cliente = new Cliente(clienteCreateRequestDTO.nome(),
        clienteCreateRequestDTO.email(),
        clienteCreateRequestDTO.login(),
        clienteCreateRequestDTO.senha(),
        clienteCreateRequestDTO.endereco(),
        ultimaAlteracao
        );

        return cliente;
    }

    public Cliente updateCliente(
            Long id,
            String nome,
            String email,
            String login,
            String senha,
            String endereco,
            Date ultimaAlteracao
    ) {

        return new Cliente(
                id, nome, email,login, senha, endereco, ultimaAlteracao
        );
    }
}
