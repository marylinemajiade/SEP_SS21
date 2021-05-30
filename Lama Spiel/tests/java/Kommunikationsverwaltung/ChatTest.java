package Kommunikationsverwaltung;

import javafx.util.converter.LocalDateTimeStringConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ChatTest {

    @Test
    void zeigeNachricht() {
        Nachricht msg = new Nachricht(1,"Maryline","Hello, this is my message", "2021-05-25-11h30");
        Chat chat = new Chat(msg);
        assertTrue(chat.sendeNachricht(msg));
        assertTrue(chat.nachrichten.contains(msg));
        assertNotNull(chat.nachrichten);
        assertTrue(chat.nachrichten.size()==1);

        assertTrue(chat.zeigeNachricht()==msg);

        assertEquals(1,msg.nachrichtId);
        assertEquals("Maryline",msg.spielerName);
        assertEquals("Hello, this is my message",msg.message);
        assertEquals("2021-05-25-11h30",msg.dateTime);

        Nachricht msg2 = new Nachricht(2,"John Doe","Hello, Maryline", "2021-05-25-11h35");
        assertEquals(1,msg.compareTo(msg2));
        msg2.nachrichtId=0;
        assertEquals(-1,msg.compareTo(msg2));
        msg2.nachrichtId=1;
        assertEquals(0,msg.compareTo(msg2));

    }
}
