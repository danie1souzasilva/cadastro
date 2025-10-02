package com.funcionario.cadastro.mapper;

import com.funcionario.cadastro.dto.FuncDTO;
import com.funcionario.cadastro.entidade.Funcionario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapearDTO {

    Funcionario dtoParaEntidade(FuncDTO funcDTO);
    FuncDTO entidadeParaDTO(Funcionario funcionario);
}
