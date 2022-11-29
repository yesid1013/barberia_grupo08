package com.grupo08.barberia.Utility;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service
public class ConvertEntity {
    // clase y metodo para convertir cualquier enttidad DTO y no hacerla de forma manual
    ModelMapper modelMapper= new ModelMapper();
    public Object convert(Object obj,Object objDto){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
                objDto = modelMapper.map(obj, objDto.getClass());
        return objDto;

    }
}
