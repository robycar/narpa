package it.rotechnology.narpa;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.rotechnology.narpa.exception.ApplicationException;
import it.rotechnology.narpa.service.ProfiloService;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ErrorMessageTests {

	@Autowired
	private ProfiloService profiloService;
	
	@Test
	public void testMessages() {
		
		ApplicationException exception = assertThrows(ApplicationException.class, () -> {
			profiloService.getProfiloById(-1L);
		});
		
		log.info(exception.getMessage());
	}
}
