package com.devpredator.projectwebservicesconsumer.client;

import java.time.LocalDateTime;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.devpredator.projectwebservicesconsumer.dto.EmpleadoDTO;

public class EmpleadoWSClient {

	
	public static void main(String[] args ) {
		
		
		//::::::::::::::::::::::::GET::::::::::::::::::::::::::::::
		
//				Client client = ClientBuilder.newClient();
//				WebTarget webTarget = 
//						client.target("http://localhost:8080/project-webservice/devpredator/empleadosWS")
//						.path("buscarEmpleadoPorId").path("1");
//		
//				Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//				Response response = invocationBuilder.get();
//				
//				if (response.getStatus() == 204) {
//					System.out.println("Empleado no encontrado");
//				}
//				
//				if (response.getStatus() == 200) {
//					EmpleadoDTO empleadoDTO = response.readEntity(EmpleadoDTO.class);
//					
//					System.out.println("Nombre empleado : " + empleadoDTO.getNombre());
//					System.out.println("Fecha empleado : " + empleadoDTO.getFechaCreacion());
//					
//					//Nombre empleado : julio
//					//Fecha empleado : 2022-06-19T03:20:28.963657800
//				}
		
		//::::::::::::::::::::::::POST::::::::::::::::::::::::::::::
		
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = 
				client.target("http://localhost:8080/project-webservice/devpredator/empleadosWS")
				.path("guardar");
		
		
		EmpleadoDTO empleadoDTO = new EmpleadoDTO();
		empleadoDTO.setId(5);
		empleadoDTO.setNombre("benja");
		empleadoDTO.setFechaCreacion(LocalDateTime.now());
		empleadoDTO.setApellidos("Azocar");
		empleadoDTO.setEdad(42);
		
		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(empleadoDTO, MediaType.APPLICATION_JSON));
		
		//imprimir error que arroja server
		if (response.getStatus() == 400) {
		String error = response.readEntity(String.class);
		System.out.println("Server error : " + error);
		}
		
		if (response.getStatus() != 200) {
		System.out.println("Empleado no guardado");
		}
	
		if (response.getStatus() == 200) {

			System.out.println("Nombre empleado : " + empleadoDTO.getNombre());
			System.out.println("Fecha empleado : " + empleadoDTO.getFechaCreacion());
			
			//Nombre empleado : julio
			//Fecha empleado : 2022-06-19T03:20:28.963657800
		}
		
		
	}
	
}
