package com.project.test;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.MOCK,
		classes = TestApplication.class)
@AutoConfigureMockMvc
class TestApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void testCrear() throws Exception{
		String requestBody = "{\"nombre\":\"Juan\",\"apellido\":\"Perez\",\"telefono\":\"523223252\",\"email\":\"jperez@loqsea.com\",\"curp\":\"fsdgf2343\",\"rfc\":\"dsfdfs3423\",\"nombreTarea\":\"Tarea uno\",\"descripcion\":\"\",\"fechaInicio\":\"2024-01-01T00:00:00.000+00:00\",\"fechaFin\":\"2024-01-01T00:00:00.000+00:00\",\"estado\":\"PAGADO\",\"valor\":11.25}";
		mvc.perform(post("/tarea/crear")
						.content(requestBody)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.nombre", Matchers.is("Juan")));
	}

	@Test
	void testListarTareasMes() throws Exception{
		mvc.perform(get("/tarea/listartareasmes")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].nombre", Matchers.is("Juan")));
	}

	@Test
	void testListarTareasMesPorEstado() throws Exception{
		mvc.perform(get("/tarea/listartareasmes/PAGADO")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].valor", Matchers.is(10.25)));
	}

	@Test
	void testSetTareaPagada() throws Exception{
		mvc.perform(put("/tarea/cambiarpagada/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	void testPromedio() throws Exception{
		mvc.perform(get("/tarea/promedio/DIA")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(content().string(Matchers.notNullValue()));
	}

}
