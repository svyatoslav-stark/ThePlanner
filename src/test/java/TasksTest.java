import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {

    @Test
    void shouldReturnCorrectId() {
        Task task = new Task(5);

        assertEquals(5, task.getId());
    }

    @Test
    void shouldBeEqualIfIdsAreSame() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);

        assertTrue(task1.equals(task2));
    }

    @Test
    void shouldNotBeEqualIfIdsAreDifferent() {
        Task task1 = new Task(1);
        Task task2 = new Task(2);

        assertFalse(task1.equals(task2));
    }

    @Test
    void shouldNotBeEqualIfComparedWithNull() {
        Task task = new Task(1);

        assertFalse(task.equals(null));
    }

    @Test
    void shouldNotBeEqualIfComparedWithDifferentClass() {
        Task task = new Task(1);
        String notATask = "Not a Task";

        assertFalse(task.equals(notATask));
    }

    @Test
    void shouldHaveSameHashCodeIfIdsAreSame() {
        Task task1 = new Task(1);
        Task task2 = new Task(1);

        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void shouldHaveDifferentHashCodeIfIdsAreDifferent() {
        Task task1 = new Task(1);
        Task task2 = new Task(2);

        assertNotEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void shouldAlwaysReturnFalseForBaseMatchesImplementation() {
        Task task = new Task(1);

        assertFalse(task.matches("любой запрос"));
    }
}
