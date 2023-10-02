package api.service.impl;

import api.client.CepCliente;
import api.controller.dto.response.CepResponseDto;
import api.converter.ConvertJson;
import api.functions.FilterCepsFunction;
import api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CepServiceImpl implements CepService {
    @Autowired
    CepCliente cepCliente;

    @Override
    public CepResponseDto getCepData(String cep) {
        //cepCliente.getDataCep(cep);
        return null;
    }

    @Override
    public CepResponseDto getAllCepsByName(String uf, String cidade, String name) {
        String jsonResponse = cepCliente.getDataCep(uf, cidade, name);
        try {
            List<CepResponseDto> cepResponseDto = (List<CepResponseDto>) ConvertJson.convert(jsonResponse, CepResponseDto.class);
            removeCeps(cepResponseDto,(o,i)->o.contains(i.toString()));
        } catch (Exception e) {
        }
        return null;
    }

    public void removeCeps(List<CepResponseDto> ceps, FilterCepsFunction function) {
        for(int i=ceps.size()-1;i>=0;i--){
            if(function.containsValue(ceps.get(i).getCep(),100))
                ceps.remove(i);
        }
    }
}
