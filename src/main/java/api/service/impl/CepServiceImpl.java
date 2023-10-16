package api.service.impl;

import api.client.CepCliente;
import api.controller.dto.response.CepResponseDto;
import api.converter.ConvertJson;
import api.functions.FilterCepsFunction;
import api.service.CepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

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
        LocalDateTime now = LocalDateTime.now();
        String jsonResponse = cepCliente.getDataCep(uf, cidade, name);
        try {
            List<CepResponseDto> cepResponseDto = (List<CepResponseDto>) ConvertJson.convert(jsonResponse, CepResponseDto.class);

            // Parallel array sorting
            Comparator<CepResponseDto> groupComparator = Comparator.comparing(CepResponseDto::getBairro);
            Arrays.parallelSort(cepResponseDto.toArray(new CepResponseDto[cepResponseDto.size()]), groupComparator);

            //BASE 64
            cepResponseDto.stream().forEach(cep -> {
                try {
                    cep.setEncodedFormat(Base64.getEncoder().encodeToString((cep.getUf() + " - " + cep.getLocalidade() + " - " + cep.getLogradouro()).getBytes("utf-8")));
                    cep.setDecodedFormat(new String(Base64.getDecoder().decode(cep.getEncodedFormat()), StandardCharsets.UTF_8));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });

            //LocalDateTime
            //Default method in interface and static
            //SearchTime.class
            cepResponseDto.stream().forEach(cep -> cep.actualMoment(LocalDateTime.now()));

            //Optional
            cepResponseDto.stream().forEach(cep -> Optional.of(cep.getComplemento()).ifPresent(complemento -> complemento.trim()));

            //Set
            HashSet<CepResponseDto> cepResponseUnique = new HashSet<>(cepResponseDto);

            //Functional Interfaces
            //Use Options
            FilterCepsFunction functionCeps = new FilterCepsFunction() {
                @Override
                public boolean containsValue(String cep, Integer value) {
                    return cep.contains(value.toString());
                }
            };
            removeCeps(cepResponseDto, functionCeps);

            FilterCepsFunction functionCepsLambda = (str, num) -> str.contains(num.toString());
            removeCeps(cepResponseDto, functionCepsLambda);

            removeCeps(cepResponseDto, (str, num) -> str.contains(num.toString()));

        } catch (Exception e) {
        }
        return null;
    }

    public void removeCeps(List<CepResponseDto> ceps, FilterCepsFunction function) {
        for (int i = ceps.size() - 1; i >= 0; i--) {
            if (function.containsValue(ceps.get(i).getCep(), 100))
                ceps.remove(i);
        }
    }
}
