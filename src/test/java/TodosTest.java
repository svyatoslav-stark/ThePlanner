import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = { "Молоко", "Яйца", "Хлеб" };
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = todos.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldSearchTasksByQuery() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Meeting meeting = new Meeting(3, "Выкатка приложения", "НетоБанк", "Во вторник");

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] result = todos.search("молоко");
        Task[] expected = {simpleTask, epic};
        assertArrayEquals(expected, result);
    }

    @Test
    void shouldHandleEmptyTodosList() {
        Todos todos = new Todos();

        Task[] allTasks = todos.findAll();
        Task[] expectedAllTasks = {};
        assertArrayEquals(expectedAllTasks, allTasks);

        Task[] searchResult = todos.search("молоко");
        Task[] expectedSearchResult = {};
        assertArrayEquals(expectedSearchResult, searchResult);
    }

    @Test
    void shouldAddAndFindSingleTask() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить хлеб");

        Todos todos = new Todos();
        todos.add(simpleTask);

        Task[] allTasks = todos.findAll();
        Task[] expectedAllTasks = {simpleTask};
        assertArrayEquals(expectedAllTasks, allTasks);

        Task[] searchResult = todos.search("хлеб");
        Task[] expectedSearchResult = {simpleTask};
        assertArrayEquals(expectedSearchResult, searchResult);
    }

    @Test
    void shouldSearchCaseInsensitive() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить МОЛОКО");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(2, subtasks);

        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);

        Task[] result = todos.search("молоко");
        Task[] expected = {simpleTask, epic};
        assertArrayEquals(expected, result);
    }

    @Test
    void shouldCreateSimpleTaskWithCorrectIdAndTitle() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");

        assertEquals(1, task.getId());
        assertEquals("Купить молоко", task.getTitle());
    }

    @Test
    void shouldReturnCorrectTitle() {
        SimpleTask task = new SimpleTask(1, "Купить хлеб");

        assertEquals("Купить хлеб", task.getTitle());
    }

    @Test
    void shouldMatchSimpleTaskByTitle() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");

        assertTrue(task.matches("молоко"));
    }

    @Test
    void shouldNotMatchSimpleTaskByTitle() {
        SimpleTask task = new SimpleTask(1, "Купить хлеб");

        assertFalse(task.matches("молоко"));
    }

    @Test
    void shouldMatchSimpleTaskCaseInsensitive() {
        SimpleTask task = new SimpleTask(1, "Купить МОЛОКО");

        assertTrue(task.matches("молоко"));
    }

    @Test
    void shouldMatchSimpleTaskWithPartialQuery() {
        SimpleTask task = new SimpleTask(1, "Купить молоко и хлеб");

        assertTrue(task.matches("молоко"));
    }

    @Test
    void shouldNotMatchSimpleTaskWithEmptyQuery() {
        SimpleTask task = new SimpleTask(1, "");

        assertFalse(task.matches("молоко"));
    }

    @Test
    void shouldBeEqualIfIdsAreSame() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(1, "Купить хлеб");

        assertTrue(task1.equals(task2));
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferent() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Купить молоко");

        assertFalse(task1.equals(task2));
    }

    @Test
    void shouldHaveSameHashCodeIfIdsAreSame() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(1, "Купить хлеб");

        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void shouldHaveDifferentHashCodeIfIdsAreDifferent() {
        SimpleTask task1 = new SimpleTask(1, "Купить молоко");
        SimpleTask task2 = new SimpleTask(2, "Купить молоко");

        assertNotEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void shouldBeEqualWhenComparedWithItself() {
        Task task = new Task(1);
        assertTrue(task.equals(task));
    }

    @Test
    void shouldHandleEmptyTitle() {
        SimpleTask task = new SimpleTask(1, "");

        assertEquals("", task.getTitle());
        assertFalse(task.matches("молоко"));
    }

    @Test
    void shouldCreateMeetingWithCorrectFields() { // Тестирование конструктора Meeting
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertEquals(1, meeting.getId());
        assertEquals("Выкатка приложения", meeting.getTopic());
        assertEquals("НетоБанк", meeting.getProject());
        assertEquals("Во вторник", meeting.getStart());
    }

    @Test
    void shouldReturnCorrectFields() {
        Meeting meeting = new Meeting(1, "Обсуждение проекта", "НетоБанк", "Сегодня");

        assertEquals("Обсуждение проекта", meeting.getTopic());
        assertEquals("НетоБанк", meeting.getProject());
        assertEquals("Сегодня", meeting.getStart());
    }

    @Test
    void shouldMatchMeetingByTopic() {
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertTrue(meeting.matches("Выкатка"));
    }

    @Test
    void shouldMatchMeetingByProject() {
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertTrue(meeting.matches("НетоБанк"));
    }

    @Test
    void shouldNotMatchMeetingIfNoMatchesFound() {
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertFalse(meeting.matches("сок"));
    }

    @Test
    void shouldMatchMeetingCaseInsensitive() {
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertTrue(meeting.matches("Выкатка"));
        assertTrue(meeting.matches("НетоБанк"));
    }

    @Test
    void shouldMatchMeetingWithPartialQuery() {
        Meeting meeting = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertTrue(meeting.matches("прилож"));
    }

    @Test
    void shouldBeEqualIfIdsAreSameMeeting() {
        Meeting meeting1 = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");
        Meeting meeting2 = new Meeting(1, "Другая тема", "Другой проект", "Завтра");

        assertTrue(meeting1.equals(meeting2));
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferentMeeting() {
        Meeting meeting1 = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");
        Meeting meeting2 = new Meeting(2, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertFalse(meeting1.equals(meeting2));
    }

    @Test
    void shouldHaveSameHashCodeIfIdsAreSameMeeting() {
        Meeting meeting1 = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");
        Meeting meeting2 = new Meeting(1, "Другая тема", "Другой проект", "Завтра");

        assertEquals(meeting1.hashCode(), meeting2.hashCode());
    }

    @Test
    void shouldHaveDifferentHashCodeIfIdsAreDifferentMeeting() {
        Meeting meeting1 = new Meeting(1, "Выкатка приложения", "НетоБанк", "Во вторник");
        Meeting meeting2 = new Meeting(2, "Выкатка приложения", "НетоБанк", "Во вторник");

        assertNotEquals(meeting1.hashCode(), meeting2.hashCode());
    }

    @Test
    void shouldHandleEmptyFields() {
        Meeting meeting = new Meeting(1, "", "", "");

        assertEquals("", meeting.getTopic());
        assertEquals("", meeting.getProject());
        assertEquals("", meeting.getStart());

        assertFalse(meeting.matches("текст"));
    }

    // Тестирование конструктора Epic
    @Test
    void shouldCreateEpicWithCorrectFields() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertEquals(1, epic.getId());
        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    // Тестирование геттера getSubtasks
    @Test
    void shouldReturnCorrectSubtasks() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertArrayEquals(subtasks, epic.getSubtasks());
    }

    // Тестирование метода matches (положительный сценарий: совпадение в одной из подзадач)
    @Test
    void shouldMatchEpicBySubtask() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertTrue(epic.matches("молоко"));
    }

    // Тестирование метода matches (отрицательный сценарий: запрос отсутствует во всех подзадачах)
    @Test
    void shouldNotMatchEpicIfNoMatchesFound() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertFalse(epic.matches("сок"));
    }

    // Тестирование метода matches (регистронезависимый поиск)
    @Test
    void shouldMatchEpicCaseInsensitive() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertTrue(epic.matches("молоко"));
        assertTrue(epic.matches("ЯЙЦА"));
    }

    // Тестирование метода matches (частичное совпадение)
    @Test
    void shouldMatchEpicWithPartialQuery() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);

        assertTrue(epic.matches("мол"));
    }

    // Тестирование метода equals (одинаковые эпики)
    @Test
    void shouldBeEqualIfIdsAreSameEpic() {
        Epic epic1 = new Epic(1, new String[]{"Молоко", "Яйца"});
        Epic epic2 = new Epic(1, new String[]{"Хлеб"});

        assertTrue(epic1.equals(epic2));
    }

    // Тестирование метода equals (разные эпики)
    @Test
    void shouldNotBeEqualIfIdsAreDifferentEpic() {
        Epic epic1 = new Epic(1, new String[]{"Молоко", "Яйца"});
        Epic epic2 = new Epic(2, new String[]{"Молоко", "Яйца"});

        assertFalse(epic1.equals(epic2));
    }

    // Тестирование метода hashCode (одинаковые эпики)
    @Test
    void shouldHaveSameHashCodeIfIdsAreSameEpic() {
        Epic epic1 = new Epic(1, new String[]{"Молоко", "Яйца"});
        Epic epic2 = new Epic(1, new String[]{"Хлеб"});

        assertEquals(epic1.hashCode(), epic2.hashCode());
    }

    // Тестирование метода hashCode (разные эпики)
    @Test
    void shouldHaveDifferentHashCodeIfIdsAreDifferentEpic() {
        Epic epic1 = new Epic(1, new String[]{"Молоко", "Яйца"});
        Epic epic2 = new Epic(2, new String[]{"Молоко", "Яйца"});

        assertNotEquals(epic1.hashCode(), epic2.hashCode());
    }

    // Тестирование граничных случаев (пустой массив подзадач)
    @Test
    void shouldHandleEmptySubtasks() {
        Epic epic = new Epic(1, new String[]{});

        assertArrayEquals(new String[]{}, epic.getSubtasks());
        assertFalse(epic.matches("текст"));
    }
}
