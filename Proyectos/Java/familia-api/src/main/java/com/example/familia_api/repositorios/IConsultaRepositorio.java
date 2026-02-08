package com.example.familia_api.repositorios;

import com.example.familia_api.modelos.Consulta;
import com.example.familia_api.modelos.dto.AccesoEstudianteDTO;
import com.example.familia_api.modelos.dto.AccesoHoraDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConsultaRepositorio extends JpaRepository<Consulta, Integer> {

    // AQUI ESTAMOS HACIENDO JOIN PARA COMBINAR DATOS DESDE DOS TABLAS DIFERENTES, ESTO LO HACEMOS
    // PARA LOS QUE ESTAN HACIENDO EL

    @Query("SELECT c FROM Consulta c JOIN FETCH c.vinculo v JOIN FETCH v.estudiante e JOIN FETCH v.familiar f")
    List<Consulta> findAllCompleta();

    @Query("SELECT new com.example.familia_api.modelos.dto.AccesoEstudianteDTO(v.estudiante.id, COUNT(c.id)) FROM Consulta c JOIN c.vinculo v GROUP BY v.estudiante.id")
    List<AccesoEstudianteDTO> countConsultasByEstudiante();

    @Query("SELECT new com.example.familia_api.modelos.dto.AccesoHoraDTO(hour(c.fechaConsulta), COUNT(c.id)) FROM Consulta c GROUP BY hour(c.fechaConsulta)")
    List<AccesoHoraDTO> countConsultasByHora();
}
