package exercise.controller;

import org.junit.jupiter.api.Test;
import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper om;

    @Autowired
    private TaskRepository taskRepository;

    // Вспомогательный метод для создания задачи
    private Task createTask() {
        Task task = new Task();
        task.setTitle(faker.lorem().sentence(3));
        task.setDescription(faker.lorem().sentence(10));
        return taskRepository.save(task);
    }

    @Test
    public void testWelcomePage() throws Exception {
        var result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThat(body).contains("Welcome to Spring!");
    }

    @Test
    public void testIndex() throws Exception {
        var result = mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andReturn();

        var body = result.getResponse().getContentAsString();
        assertThatJson(body).isArray();
    }

    @Test
    public void testShowTask() throws Exception {
        // Создаем задачу
        Task task = createTask();

        // Выполняем GET-запрос
        var result = mockMvc.perform(get("/tasks/{id}", task.getId()))
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем JSON-ответ
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("id").isEqualTo(task.getId()),
                a -> a.node("title").isEqualTo(task.getTitle()),
                a -> a.node("description").isEqualTo(task.getDescription())
        );
    }

    @Test
    public void testCreateTask() throws Exception {
        // Подготовка данных
        var data = new HashMap<String, String>();
        data.put("title", faker.lorem().sentence(3));
        data.put("description", faker.lorem().sentence(10));

        // Выполняем POST-запрос
        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andReturn();

        // Проверяем JSON-ответ
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("title").isEqualTo(data.get("title")),
                a -> a.node("description").isEqualTo(data.get("description"))
        );

        // Проверяем, что задача сохранена в базе
        Task savedTask = taskRepository.findByTitle(data.get("title")).orElse(null);
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getDescription()).isEqualTo(data.get("description"));
    }

    @Test
    public void testUpdateTask() throws Exception {
        // Создаем задачу
        Task task = createTask();

        // Подготовка данных для обновления
        var data = new HashMap<String, String>();
        data.put("title", faker.lorem().sentence(4));
        data.put("description", faker.lorem().sentence(12));

        // Выполняем PUT-запрос
        var request = put("/tasks/{id}", task.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(data));

        var result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();

        // Проверяем JSON-ответ
        var body = result.getResponse().getContentAsString();
        assertThatJson(body).and(
                a -> a.node("id").isEqualTo(task.getId()),
                a -> a.node("title").isEqualTo(data.get("title")),
                a -> a.node("description").isEqualTo(data.get("description"))
        );

        // Проверяем, что задача обновлена в базе
        Task updatedTask = taskRepository.findById(task.getId()).orElse(null);
        assertThat(updatedTask).isNotNull();
        assertThat(updatedTask.getTitle()).isEqualTo(data.get("title"));
        assertThat(updatedTask.getDescription()).isEqualTo(data.get("description"));
    }

    @Test
    public void testDeleteTask() throws Exception {
        // Создаем задачу
        Task task = createTask();

        // Выполняем DELETE-запрос
        mockMvc.perform(delete("/tasks/{id}", task.getId()))
                .andExpect(status().isOk());

        // Проверяем, что задача удалена из базы
        assertThat(taskRepository.findById(task.getId())).isEmpty();
    }
}