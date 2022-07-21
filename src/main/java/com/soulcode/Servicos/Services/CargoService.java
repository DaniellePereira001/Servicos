package com.soulcode.Servicos.Services;

import com.soulcode.Servicos.Models.Cargo;
import com.soulcode.Servicos.Repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    @Autowired
    CargoRepository cargoRepository;

    // primeiro serviço: mostrar todos os cargos cadastrados

    @Cacheable("cargosCache")
    public List<Cargo> mostrarTodosCargos(){
        return cargoRepository.findAll();
    }

    @Cacheable(value = "cargosCache", key = "#idCargo")
    public Cargo mostrarCargoPeloId(Integer idCargo){
        Optional<Cargo> cargo = cargoRepository.findById(idCargo);
        return cargo.orElseThrow();
    }

    @CachePut(value = "cargoCache", key = "cargo.idcargo")
    public Cargo cadastrarCargo(Cargo cargo){
        //por precaução vamos limpar o id do Cargo
        cargo.setIdCargo(null);
        return cargoRepository.save(cargo);
    }

    @CachePut(value = "cargoCache", key = "cargo.idcargo")
    public Cargo editarCargo(Cargo cargo){
        return cargoRepository.save(cargo);
    }

    @CacheEvict(value = "cargoCache", key = "idCargo", allEntries = true)
    public void excluirCargo(Integer idCargo){
        try{
            cargoRepository.deleteById(idCargo);
        }catch(org.springframework.dao.DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Esse cargo não pode ser excluído porque existem funcionário(s) associado(s) a ele");
        }catch(org.springframework.dao.EmptyResultDataAccessException e){
            throw new EntityNotFoundException("Cargo não cadastrado!");
        }
    }
}