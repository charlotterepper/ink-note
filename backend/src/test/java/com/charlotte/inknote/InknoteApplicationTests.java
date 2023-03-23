package com.charlotte.inknote;

import com.charlotte.inknote.controller.NoteController;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InknoteApplicationTests {

	@Autowired
	private NoteController noteController;

	@Autowired
	private NoteService noteService;

	static {
		System.setProperty("PSQL_USERNAME", "charlotte");
		System.setProperty("PSQL_PASSWORD", "databaseabc");
	}

	@Test
	void contextLoads() {
		assertThat(noteController).isNotNull();
		assertThat(noteService).isNotNull();
	}

}
