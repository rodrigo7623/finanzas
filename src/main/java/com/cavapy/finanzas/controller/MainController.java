package com.cavapy.finanzas.controller;

import com.cavapy.finanzas.entity.CuentaBanco;
import com.cavapy.finanzas.entity.Deposito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1")
public class MainController {

    @GetMapping(value = "/consulta/movimientos", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultaCuentaBanco(@RequestBody CuentaBanco cuentaBanco) {

        return new ResponseEntity<>(new CuentaBanco(), HttpStatus.OK);

    }

    @PostMapping(value = "/registro/tipo-cambio/diario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registroTipoCambio() {

        return new ResponseEntity<>("Tipo de cambio actualizado correctamente.", HttpStatus.OK);

    }


    @PostMapping(value = "/registro/depositos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registroDepositos(@RequestBody List<Deposito> depositos) {

        return new ResponseEntity<>("Depósito(s) realizado(s) correctamente.", HttpStatus.OK);

    }

}
