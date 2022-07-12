package com.gateway.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
public class SpringSecurityConfig {

    @Autowired
    private JwtAutheticationFilter autheticationFilter;

    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity httpSecurity){
        return httpSecurity.authorizeExchange()
                .pathMatchers("/api/security/oauth/**").permitAll()
                .pathMatchers(HttpMethod.GET,"/api/maestra/persona/","/api/maestra/comunidad/"
                ,"/api/tipo/familia/","/api/tipo/tipo/"
                ,"/api/negocio/proyecto/","/api/negocio/programa/","/api/negocio/taller/",
                        "/api/negocio/entidadProyecto/","/api/negocio/lugarProyecto/","/api/negocio/personaPrograma/","/api/negocio/personaTaller/"
                ,"/api/ubigeo/departamento/","/api/ubigeo/provincia/","/api/ubigeo/distrito"
                ,"/api/cuestionario/cuestionario/","/api/cuestionario/pregunta/"
                ,"/api/entidad/entidad/","/api/entidad/personaEntidad/"
                ,"/api/participante/PPTaller/","/api/participante/PPTCuestionario/","/api/participante/respuesta/"
                ,"/api/sg/usuarios","/api/sg/usuarios/{idUsuario}","/api/sg/usuarios/search/identificacion","/api/negocio/taller/?nombre","/api/tipo/tipo/{id}",
                        "/api/maestra/persona/{id}").permitAll()
                .pathMatchers(HttpMethod.GET,"/api/maestra/persona/{id}"
                ,"/api/maestra/persona/dni/{dni}","/api/negocio/proyecto/{id}","/api/negocio/programa/{id}"
                ,"/api/negocio/programa/?esPrograma","/api/negocio/programa/?proyecto","/api/negocio/taller/{id}"
                ,"/api/negocio/taller/?esTaller","/api/negocio/taller/?programa","/api/cuestionario/cuestionario?nombre"
                        ,"/api/cuestionario/cuestionario/{id}","/api/cuestionario/pregunta/{id}"
                ,"/api/entidad/entidad/{id}","/api/entidad/personaEntidad/{id}").hasAnyRole("DOCENTE","ESTUDIANTE","ADMIN")
                .pathMatchers("/api/entidad/entidad/**",
                        "/api/maestra/persona/**","/api/negocio/proyecto/**","/api/negocio/programa/**"
                ,"/api/negocio/taller/**","/api/negocio/entidadProyecto/**","/api/negocio/personaPrograma/**"
                ,"/api/negocio/lugarProyecto/**","/api/cuestionario/cuestionario/**","/api/cuestionario/pregunta/**"
                ,"/api/entidad/entidad/**","/api/entidad/personaEntidad/**"
                ,"/api/participante/PPTaller/**","/api/participante/PPTCuestionario/**","/api/participante/respuesta/**"
                ,"/api/sg/usuarios/**").hasAnyRole("DOCENTE","ADMIN")
                .pathMatchers(HttpMethod.POST,"/api/participante/respuesta/","").hasRole("ESTUDIANTE")
                .pathMatchers(HttpMethod.PUT,"/api/participante/respuesta/{id}","/api/sg/usuarios/{idUsuario}").hasRole("ESTUDIANTE")
                .anyExchange().authenticated()
                .and().addFilterAt(autheticationFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .csrf()
                .disable()
                .build();
    }
}
