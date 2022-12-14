package com.grupo08.barberia.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo08.barberia.DTO.ClienteDTO;
import com.grupo08.barberia.DTO.CrearClienteDTO;
import com.grupo08.barberia.DTO.CredencialesDTO;
import com.grupo08.barberia.Entity.Cliente;
import com.grupo08.barberia.Entity.ERol;
import com.grupo08.barberia.Entity.Message;
import com.grupo08.barberia.Entity.Role;
import com.grupo08.barberia.Exception.Exception.InvalidDataException;
import com.grupo08.barberia.Security.Hash;
import com.grupo08.barberia.Service.ClienteService;
import com.grupo08.barberia.Service.RoleService;
import com.grupo08.barberia.Utility.ConvertEntity;



@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RoleService roleService;

    ConvertEntity convertEntity = new ConvertEntity();

    

    @GetMapping("/listar")
    public ResponseEntity<List<ClienteDTO>> findAll(@RequestHeader String user, @RequestHeader String key){
        try {
            if (!clienteService.validarUsuarioAdmin(user, key)) {
                   return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    
                } else {
                    List<Cliente> clientes = clienteService.findAll();
                    List<ClienteDTO> clientesDTO = new ArrayList<>();
                    for (Cliente cliente : clientes) {
                        ClienteDTO clienteDTO = new ClienteDTO(); //objeto DTO
                        clientesDTO.add((ClienteDTO) convertEntity.convert(cliente, clienteDTO));
                }
                return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
                }
            
            
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    @PostMapping("/crear")
    public ResponseEntity <Message> save(@Valid @RequestBody CrearClienteDTO cliente,BindingResult result, @RequestHeader String user, @RequestHeader String key){
        if(result.hasErrors()){
            throw new InvalidDataException("Error de datos",result);
        }
        if(clienteService.validarUsuarioAdmin(user, key)==false){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Set<String> strRoles = cliente.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles==null) {//Si no manda roles se le agrega el rol user por defecto
            Role rol = roleService.findByNombre(ERol.ROLE_USER).get();
            roles.add(rol); 
        } else {
            strRoles.forEach(role->{
                switch(role){
                case "admin":
                Role rolAdmin = roleService.findByNombre(ERol.ROLE_ADMIN).get();
                roles.add(rolAdmin);
                break;
                case "user":
                Role rolUser = roleService.findByNombre(ERol.ROLE_USER).get();
                roles.add(rolUser);
                break;
                case "barber":
                Role rolBarber = roleService.findByNombre(ERol.ROLE_BARBER).get();
                roles.add(rolBarber); 
                break; 
                }
            });
            
        }
        Cliente clienteSave = (Cliente) convertEntity.convert(cliente, new Cliente());
        clienteSave.setRoles(roles);
        
        if (!clienteService.validarCredenciales(user, key)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
        try {
            clienteSave.setPassword(Hash.sha1(cliente.getPassword())); //encriptar contraseña
            clienteService.save(clienteSave);
            return new ResponseEntity<Message>(new Message(201,"Registro creado"), HttpStatus.CREATED);
            
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400,"Los datos ingresados no son correctos "+e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Message> delteById(@PathVariable String id){
        Message message= clienteService.deleteById(id);
        return new ResponseEntity<>(message,HttpStatus.resolve(message.getStatus()));
    }

    @GetMapping("list/{id}")
    public ClienteDTO findById(@PathVariable String id){
        ClienteDTO clienteDTO = new ClienteDTO();
        return (ClienteDTO) convertEntity.convert(clienteService.findById(id),clienteDTO);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Message> update(@RequestBody CrearClienteDTO cliente){
        Set<String> strRoles = cliente.getRoles();
        Set<Role> roles = new HashSet<>();
        if (strRoles==null) {//Si no manda roles se le agrega el rol user por defecto
            Role rol = roleService.findByNombre(ERol.ROLE_USER).get();
            roles.add(rol); 
        } else {
            strRoles.forEach(role->{
                switch(role){
                case "admin":
                Role rolAdmin = roleService.findByNombre(ERol.ROLE_ADMIN).get();
                roles.add(rolAdmin);
                break;
                case "user":
                Role rolUser = roleService.findByNombre(ERol.ROLE_USER).get();
                roles.add(rolUser);
                break;
                case "barber":
                Role rolBarber = roleService.findByNombre(ERol.ROLE_BARBER).get();
                roles.add(rolBarber); 
                break; 
                }
            });
            
        }
        cliente.setPassword(Hash.sha1(cliente.getPassword()));
        Cliente clientesave = (Cliente) convertEntity.convert(cliente, new Cliente());
        clientesave.setRoles(roles);
        
        try {
            Message message=clienteService.update(clientesave);
            
            return new ResponseEntity<Message>(message,HttpStatus.resolve(message.getStatus()));
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message(400,"Ha ocurrido un error "+e.getMessage()),HttpStatus.BAD_REQUEST);
        }
        
    }

    @GetMapping("/encriptar/{dato}")
    public String encriptar (@PathVariable String dato){
        return Hash.sha1(dato);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestHeader String user,@RequestHeader String pwd){
        Cliente cliente = clienteService.login(user, Hash.sha1(pwd)); //Enviamos la contraseña encriptada ya que en la bd no esta plana
        if(cliente!=null){
            ClienteDTO clienteDTO=((ClienteDTO) convertEntity.convert(cliente, new ClienteDTO()));
            return new ResponseEntity<>(new CredencialesDTO(cliente.getUsername(),Hash.sha1(Hash.sha1(pwd)+Hash.sha1(cliente.getUsername())),clienteDTO.getRoles()),HttpStatus.OK);//Mandamos la llave con contraseña y usuario juntas encriptadas 
        }else{
            return new ResponseEntity<>(new Message(401,"Error de credenciales"),HttpStatus.UNAUTHORIZED);
        }
    }

    

}
