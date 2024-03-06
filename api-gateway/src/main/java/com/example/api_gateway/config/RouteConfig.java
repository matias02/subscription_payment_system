package com.example.api_gateway.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RouteConfig {

    @RequestMapping(value = "/autenticacion/**", method = RequestMethod.GET)
    public String redirectAutenticacion() {
        return "forward:/autenticacionEndpointLocal";
    }

    @RequestMapping(value = "/usuarios/**", method = RequestMethod.GET)
    public String redirectUsuarios() {
        return "forward:/usuariosEndpointLocal";
    }

    @RequestMapping(value = "/pagos/**", method = RequestMethod.GET)
    public String redirectPagos() {
        return "forward:/pagosEndpointLocal";
    }
}
