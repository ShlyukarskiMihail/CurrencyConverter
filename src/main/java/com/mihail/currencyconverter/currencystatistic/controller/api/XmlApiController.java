package com.mihail.currencyconverter.currencystatistic.controller.api;

import com.mihail.currencyconverter.currencystatistic.controller.request.XmlCommandRequest;
import com.mihail.currencyconverter.currencystatistic.controller.response.XmlCommandResponse;
import com.mihail.currencyconverter.base.GatewayService;
import com.sun.jdi.request.DuplicateRequestException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/xml_api")
public class XmlApiController {

    private final GatewayService gatewayService;

    @PostMapping(value = "/command", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> handleCommand(@RequestBody @Valid XmlCommandRequest request) {
        try {
            return ResponseEntity.ok(gatewayService.handleXmlCommand(request));
        } catch (DuplicateRequestException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(XmlCommandResponse.builder()
                    .requestId(request.getId())
                    .status("error")
                    .message(e.getMessage())
                    .build());
        }
    }
}