package com.formacionbdi.microservicios.app.usuarios.services;

import com.formacionbdi.microservicios.app.usuarios.dto.ClienteDto;
import com.formacionbdi.microservicios.app.usuarios.models.entity.Cliente;
import com.formacionbdi.microservicios.app.usuarios.models.entity.Persona;
import com.formacionbdi.microservicios.app.usuarios.models.repository.ClienteRepository;
import com.formacionbdi.microservicios.app.usuarios.models.repository.PersonaRepository;
import com.prueba.her.commons.service.CommonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ClienteServiceImpl extends CommonServiceImpl<Cliente, ClienteRepository> implements ClienteService {

    @Autowired
    PersonaRepository repositoryPer ;
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Persona persona = new Persona();
        persona.setEdad(clienteDto.getEdad());
        persona.setDireccion(clienteDto.getDireccion());
        persona.setGenero(clienteDto.getGenero());
        persona.setNombre(clienteDto.getNombre());
        persona.setIdentificacion(clienteDto.getIdentificacion());
        persona.setTelefono(clienteDto.getTelefono());
        Persona perDB = this.repositoryPer.save(persona);
        Cliente cliente = new Cliente();
        cliente.setContrasenia(clienteDto.getContrasenia());
        cliente.setEstado(clienteDto.getEstado());
        cliente.setIdPersona(perDB.getId());

        return this.repository.save(cliente);
    }
}
