package com.fatec.feriado;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;



class Req01ConsultarFeriados {
	record Feriado(String date, String name, String type, String level) {};
	
	@Test
	void ct01_cosulta_feriados_com_sucesso() {
			 String urlBase = "";
			 RestTemplate restTemplate = new RestTemplate();
			 HttpHeaders headers = new HttpHeaders();
			 headers.setContentType(MediaType.APPLICATION_JSON);
			 record Feriado(String date, String name, String type, String level) {};
			 HttpEntity request = new HttpEntity<>(headers); 
			// make an HTTP GET request with headers
			 ResponseEntity<String> response = restTemplate.exchange(urlBase, HttpMethod.GET, request, String.class);
			// validar o status retornado
			 assertEquals("200 OK", response.getStatusCode().toString());
			// validar o body
			 System.out.println(response.getBody());
			// validar o headers retornado
			 assertEquals("application/json" , response.getHeaders().getContentType().toString());
	}
	
	@Test
	void ct02_cosulta_feriados_com_autorizacao_invalida() {
			ResponseEntity<String> response = null;
			String urlBase = "";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity request = new HttpEntity<>(headers); 
			try{
				// make an HTTP GET request with headers
				response = restTemplate.exchange(urlBase, HttpMethod.GET, request, String.class);
			}catch(HttpClientErrorException e) {
				// validar o status retornado
				assertEquals("401 UNAUTHORIZED", e.getStatusCode().toString());
			 	}
	}
	
	public void converteUTF8(String str) {
		Gson gson = new Gson();
		try {
			// converte string para byte utf8
			String listaa = str;
			byte[] listab = listaa.getBytes("UTF-8");
			String str2 = new String(listab, "UTF-8");
			//converte gson para objto java array lista de feriados
			record Feriado(String date, String name, String type, String level) {}
			Feriado[] lista = gson.fromJson(str, Feriado[].class);
			System.out.println(lista[0]);
			assertEquals(17, lista.length);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	void ct03_cosulta_feriados_com_ano_invalido() {
			ResponseEntity<String> response = null;
			String urlBase = "";
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity request = new HttpEntity<>(headers); 
			try{
				// make an HTTP GET request with headers
				response = restTemplate.exchange(urlBase, HttpMethod.GET, request, String.class);
			}catch(HttpClientErrorException e) {
				// validar o status retornado
				assertEquals("404 NOT FOUND", e.getStatusCode().toString());
			 	}
	}

}