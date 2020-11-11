package DAO;

import entity.Student;

import java.util.List;

/**
 * Интерфейс для обработки запросов к таблице student.
 */
public interface StudentDAO {
    /**
     * Создание таблицы.
     */
    public void createTable();

    /**
     * Удаление таблицы.
     */
    public void dropTable();

    /**
     * Создание студента.
     *
     * @param s студент.
     */
    public void create(Student s);

    /**
     * Получение студента.
     *
     * @param id id студента.
     * @return студент.
     */
    public Student get(int id);

    /**
     * Получение списка всех студентов.
     *
     * @return список студентов.
     */
    public List<Student> getStudentList();

    /**
     * Обновление информации о студенте.
     *
     * @param id id студента.
     * @param student новые данные о студенте.
     */
    public void update(int id, Student student);

    /**
     * Удаление студента.
     *
     * @param id id студента.
     */
    public void delete(int id);

    /**
     * Получение количества записей в таблице student.
     *
     * @return количество записей.
     */
    public int size();

    /**
     * Получение студентов по имени.
     *
     * @param name имя.
     * @return студент.
     */
    public List<Student> getByName(String name);
}
