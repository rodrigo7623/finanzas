package com.cavapy.finanzas.controller;

import com.cavapy.finanzas.entity.CuentaBanco;
import com.cavapy.finanzas.entity.Deposito;
import com.cavapy.finanzas.util.FinanzasResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1")
@Tag(name = "MainController", description = "API para la gestión de finanzas")
public class MainController {

    @Operation(summary = "Consulta movimientos de la cuenta bancaria",
            description = "Retorna los movimientos de la cuenta bancaria especificada",
            requestBody = @RequestBody(
                    description = "Cuenta bancaria a consultar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = CuentaBanco.class))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta exitosa",
                            content = @Content(schema = @Schema(implementation = CuentaBanco.class))),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            })
    @GetMapping(value = "/consulta/movimientos", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> consultaCuentaBanco(@RequestBody CuentaBanco cuentaBanco) {
        return new ResponseEntity<>(new CuentaBanco(), HttpStatus.OK);
    }

    @Operation(summary = "Registrar tipo de cambio diario",
            description = "Registra el tipo de cambio diario en el sistema",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tipo de cambio actualizado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            })
    @PostMapping(value = "/registro/tipo-cambio/diario", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registroTipoCambio() {
        return new ResponseEntity<>(new FinanzasResponse("Tipo de cambio actualizado correctamente."), HttpStatus.OK);
    }

    @Operation(summary = "Registrar depósitos",
            description = "Registra uno o más depósitos en la cuenta centralizadora de liquidación",
            requestBody = @RequestBody(
                    description = "Lista de depósitos a registrar",
                    required = true,
                    content = @Content(schema = @Schema(implementation = Deposito.class),
                            examples = @ExampleObject(
                                    name = "Ejemplo de depósito",
                                    summary = "Ejemplo de lista de depósitos",
                                    value = "[{\"monto\": 1000.50, \"moneda\": \"USD\", \"fecha\": \"2024-05-20\", \"descripcion\": \"Depósito\"}]"
                            ))
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Depósito(s) realizado(s) correctamente"),
                    @ApiResponse(responseCode = "400", description = "Solicitud incorrecta")
            })
    @PostMapping(value = "/registro/depositos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registroDepositos(@RequestBody List<Deposito> depositos) {
        return new ResponseEntity<>(new FinanzasResponse("Depósito(s) realizado(s) correctamente."), HttpStatus.OK);
    }
}
