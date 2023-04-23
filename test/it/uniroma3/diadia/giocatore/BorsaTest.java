package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {

	@Test
	void testAddAttrezzo() {
		Borsa b = new Borsa();
		assertEquals(0,b.getNumeroAttrezzi());
		assertArrayEquals(new Attrezzo[10],b.getAttrezzi());
		assertFalse(b.addAttrezzo(null));
		assertEquals(0,b.getNumeroAttrezzi());
		assertArrayEquals(new Attrezzo[10],b.getAttrezzi());
		for(int i = 0; i<b.getAttrezzi().length-1;i++) {
			assertTrue(b.addAttrezzo(new Attrezzo(String.valueOf(i),i/5)));
		}
		assertFalse(b.addAttrezzo(new Attrezzo("11",7)));
		assertTrue(b.addAttrezzo(new Attrezzo("11",0)));
	}

	@Test
	void testGetAttrezzo() {
		Borsa b = new Borsa();
		assertNull(b.getAttrezzo(null));
		for(int i = 0; i<b.getAttrezzi().length;i++) {
			b.addAttrezzo(new Attrezzo(String.valueOf(i),i/5));
		}
		assertNull(b.getAttrezzo(null));
		assertNotNull(b.getAttrezzo("5"));
		assertNull(b.getAttrezzo("11"));
	}

	@Test
	void testRemoveAttrezzo() {
		Borsa b = new Borsa();
		assertNull(b.removeAttrezzo(null));
		for(int i = 0; i<b.getAttrezzi().length;i++) {
			b.addAttrezzo(new Attrezzo(String.valueOf(i),i/5));
		}
		assertNull(b.removeAttrezzo(null));
		assertNotNull(b.removeAttrezzo("5"));
        assertEquals("Contenuto borsa (4kg/10kg): 0 (0kg) 1 (0kg) 2 (0kg) 3 (0kg) 4 (0kg) 6 (1kg) 7 (1kg) 8 (1kg) 9 (1kg) ",b.toString());
		assertNotNull(b.removeAttrezzo("4"));
        assertEquals("Contenuto borsa (4kg/10kg): 0 (0kg) 1 (0kg) 2 (0kg) 3 (0kg) 6 (1kg) 7 (1kg) 8 (1kg) 9 (1kg) ",b.toString());

	}

}
