package api.controller;

import api.controller.contract.Cep;
import api.controller.dto.response.CepResponseDto;
import api.service.CepService;
import api.service.impl.CepServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CepController implements Cep {
    @Autowired
    private CepService cepService;

    @Override
    public ResponseEntity<CepResponseDto> getCep(@PathVariable("cep") String cep) {
        return ResponseEntity.of(Optional.of(cepService.getCepData(cep)));
    }
    @Override
    public ResponseEntity<CepResponseDto> getStreets(String uf,String city) {
        return ResponseEntity.of(Optional.of(cepService.getAllCepsByName(uf,city,"")));
    }
}
