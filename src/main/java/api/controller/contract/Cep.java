package api.controller.contract;

import api.controller.dto.response.CepResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface Cep {
    @GetMapping("/ceps/{cep}")
    ResponseEntity<CepResponseDto> getCep(@PathVariable("cep") String cep);
    @GetMapping("/ruas/{uf}/{city}")
    ResponseEntity<CepResponseDto> getStreets(@PathVariable("uf") String uf, @PathVariable("city") String city);
}
