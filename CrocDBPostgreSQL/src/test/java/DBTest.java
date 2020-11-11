import DAO.StudentDAOImpl;
import entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class DBTest {

    /**
     * Тестирование класса StudentDAOImpl по работе с таблицей student.
     */
    @Test
    public void testDB(){
        StudentDAOImpl studentDAO=new StudentDAOImpl();

        //создаем таблицу, если она не создана
        studentDAO.createTable();
        //тестируем создание студента(используем id=0, так как в бд id автоматически увеличивается)
        //используем получение по имени, так как получение с помощью количества записей
        //некорректно при удалении пользователей
        Student studentkostya=new Student(0, "Konstantin", LocalDate.now(), false, 2.1);
        studentDAO.create(studentkostya);
        Assertions.assertTrue(studentkostya.equals(
                studentDAO.getByName("Konstantin").get(0)));

        //создаем нового студента и тестируем его обновление.
        Student studentmax=new Student(0, "Maxim", LocalDate.now(), true, 4.6);
        studentDAO.create(studentmax);
        Student updateMax=new Student(0, "Maxiiim", LocalDate.now(), true, 4.2);
        studentDAO.update(studentDAO.size(), updateMax);
        Assertions.assertTrue(updateMax.equals(studentDAO.getByName("Maxiiim").get(0)));

        //тестируем получение списка всех студентов
        Assertions.assertEquals(studentDAO.size(), studentDAO.getStudentList().size());

        //тестируем удаление студента
        int sizeBeforeDelete=studentDAO.size();
        studentDAO.delete(1);
        Assertions.assertEquals(sizeBeforeDelete-1, studentDAO.size());

        //не знаю, как тестировать(но удаление видно в pgAdmin и во вкладке database в IDEA)
        studentDAO.dropTable();
    }



}
