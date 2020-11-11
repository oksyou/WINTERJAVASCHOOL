package DAO;

import entity.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static connection.ConnectionToDB.getDBConnection;

/**
 * Класс обработки запросов к таблице student.
 */
public class StudentDAOImpl implements StudentDAO {
    /**
     * Создание таблицы.
     */
    @Override
    public void createTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS student(id SERIAL NOT NULL PRIMARY KEY," +
                "name varchar NOT NULL," +
                "date_of_admission date," +
                "is_a_wizard boolean," +
                "average_score double precision" +
                ")";

        try (Connection dbConnection = getDBConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(createTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаление таблицы.
     */
    @Override
    public void dropTable() {
        String dropTableSQL = "DROP TABLE student";

        try (Connection dbConnection = getDBConnection();
             Statement statement = dbConnection.createStatement()) {
            statement.execute(dropTableSQL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Создание студента.
     *
     * @param s студент.
     */
    @Override
    public void create(Student s) {
        String createStudent =
                "INSERT INTO student (name, date_of_admission, is_a_wizard, average_score) VALUES (?, ?, ?, ?)";

        try (Connection dbConnection = getDBConnection();
             PreparedStatement st = dbConnection.prepareStatement(createStudent)) {
                st.setString(1, s.getName());
                st.setObject(2, s.getDate_of_admission());
                st.setBoolean(3, s.get_wizard());
                st.setDouble(4, s.getAverage_score());
                st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Получение студента по его id.
     *
     * @param id id студента.
     * @return студент.
     */
    @Override
    public Student get(int id) {
        String getStudent =
                "SELECT name, date_of_admission, is_a_wizard, average_score " +
                        "FROM student WHERE id="+id;

        Student student=null;
        try (Connection dbConnection = getDBConnection();
             Statement st = dbConnection.createStatement();
             ResultSet rs=st.executeQuery(getStudent)) {
            while ( rs.next() ) {
                String name = rs.getString(1);
                LocalDate date = rs.getObject(2,LocalDate.class);
                boolean wizard=rs.getBoolean(3);
                double score  = rs.getDouble(4);
                student=new Student(id, name, date, wizard, score);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    /**
     * Получение списка всех студентов.
     *
     * @return список студентов.
     */
    @Override
    public List<Student> getStudentList() {
        String getStudents =
                "SELECT id, name, date_of_admission, is_a_wizard, average_score " +
                        "FROM student";

        List<Student> studentList=new ArrayList<>();
        try (Connection dbConnection = getDBConnection();
             Statement st = dbConnection.createStatement();
             ResultSet rs=st.executeQuery(getStudents)) {
            while ( rs.next() ) {
                int id=rs.getInt(1);
                String name = rs.getString(2);
                LocalDate date = rs.getObject(3,LocalDate.class);
                boolean wizard=rs.getBoolean(4);
                double score  = rs.getDouble(5);
                studentList.add(new Student(id, name, date, wizard, score));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

    /**
     * Обновление данных о студенте.
     *
     * @param id id студента.
     * @param s новые данные о студенте.
     */
    @Override
    public void update(int id, Student s) {
        String setStudent =
                "UPDATE student SET name=?, date_of_admission=?, is_a_wizard=?, average_score=? " +
                        "WHERE id=?";
        try (Connection dbConnection = getDBConnection();
             PreparedStatement st = dbConnection.prepareStatement(setStudent)) {
            st.setString(1, s.getName());
            st.setObject(2, s.getDate_of_admission());
            st.setBoolean(3, s.get_wizard());
            st.setDouble(4, s.getAverage_score());
            st.setInt(5, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Удаление студента.
     *
     * @param id id студента.
     */
    @Override
    public void delete(int id) {
        String deleteStudent =
                "DELETE FROM student WHERE id="+id;
        try (Connection dbConnection = getDBConnection();
             Statement st = dbConnection.createStatement();
             ResultSet rs=st.executeQuery(deleteStudent)) {
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Получение количества записей в таблице student.
     *
     * @return количество записей.
     */
    @Override
    public int size() {
        String sizeTable =
                "SELECT COUNT(*) FROM student";
        int countId=0;

        try (Connection dbConnection = getDBConnection();
             Statement st = dbConnection.createStatement();
             ResultSet rs=st.executeQuery(sizeTable)) {
            rs.next();
                countId=rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countId;
    }

    /**
     * Получение студентов по имени.
     *
     * @param name имя.
     * @return студент.
     */
    @Override
    public List<Student> getByName(String name) {
        String getByName =
                "SELECT id, date_of_admission, is_a_wizard, average_score " +
                        "FROM student WHERE name=\'"+name+"\'";

        List<Student> studentList=new ArrayList<>();
        try (Connection dbConnection = getDBConnection();
             Statement st = dbConnection.createStatement();
             ResultSet rs=st.executeQuery(getByName)) {
            while ( rs.next() ) {
                int id=rs.getInt(1);
                LocalDate date = rs.getObject(2,LocalDate.class);
                boolean wizard=rs.getBoolean(3);
                double score  = rs.getDouble(4);
                studentList.add(new Student(id, name, date, wizard, score));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return studentList;
    }

}
