package org.ditalia.bbb.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {
	/*@Bean
	UserDetailsManager users(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		return users;
	}*/
	@Bean
	UserDetailsManager usersCustom(DataSource dataSource) {
			JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
			users.setUsersByUsernameQuery("select username,password,estatus from usuarios u where username=?");
			users.setAuthoritiesByUsernameQuery("select u.username,p.perfil from usuarioperfil up " + 
			"inner join usuarios u on u.id = up.idUsuario " + 
			"inner join perfiles p on p.id = up.idPerfil " + 
			"where u.username=?");
		return users;
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		// Los recursos estáticos no requieren autenticación
		.requestMatchers("/bootstrap/**","/css/**","/images/**","/tinymce/**","/logos/**").permitAll()
		// Las vistas públicas no requieren autenticación
		.requestMatchers("/", "/signup", "/guardar", "/acerca", "/contacto", "/mision", "/search", "/vestidos/detalle/**").permitAll()
		//asignar permisos a URL'S por roles
		.requestMatchers("/usuarios/**").hasAnyAuthority("Administrador")
		.requestMatchers("/categorias/**").hasAnyAuthority("Administrador")
		.requestMatchers("/vestidos/**").hasAnyAuthority("Administrador","Usuario")
		.requestMatchers("/accesorios/**").hasAnyAuthority("Administrador")
		.requestMatchers("/perfiles/**").hasAnyAuthority("Administrador")
		// Todas las demás URLs de la Aplicación requieren autenticación
		.anyRequest().authenticated()
		// El formulario de Login no requiere autenticacion
		.and().formLogin().loginPage("/login").permitAll();
		return http.build();
		}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}