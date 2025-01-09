package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();
    private KeyValueStorage storage;

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    @BeforeEach
    public void setUp() {
        var initialMap = Map.of("key1", "value1", "key2", "value2");
        var mutableMap = new HashMap<>(initialMap);
        storage = new FileKV(filepath.toString(), mutableMap);

    }

    // BEGIN
    @Test
    public void testGetExistingKey() {
        assertEquals("value1", storage.get("key1", "default"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertEquals("default", storage.get("nonExistingKey", "default"));
    }

    @Test
    public void testPutNewKey() {
        storage.set("key3", "value3");
        assertEquals("value3", storage.get("key3", "default"));
    }

    @Test
    public void testUpdateExistingKey() {
        storage.set("key1", "newValue1");
        assertEquals("newValue1", storage.get("key1", "default"));
    }

    @Test
    public void testRemoveKey() {
        storage.unset("key2");
        assertEquals("default", storage.get("key2", "default"));
    }

    @Test
    public void testReadAll() {

        Map<String, String> allData = storage.toMap();
        assertEquals(2, allData.size());
        assertTrue(allData.containsKey("key1"));
        assertTrue(allData.containsKey("key2"));
    }
}

