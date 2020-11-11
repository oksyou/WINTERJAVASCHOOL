package entity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Сущность студента.
 */
public class Student {
    /**
     * Id студента.
     */
    private int id;
    /**
     * Имя.
     */
    private String name;
    /**
     * Дата поступления.
     */
    private LocalDate date_of_admission;
    /**
     * Является ли волшебником.
     */
    private boolean is_a_wizard;
    /**
     * Средний балл.
     */
    private double average_score;

    /**
     * Конструктор по умолчанию.
     */
    public Student() {
    }

    /**
     * Конструктор.
     *
     * @param id id.
     * @param name имя.
     * @param date_of_admission дата поступления.
     * @param is_a_wizard является ли вошебником.
     * @param average_score средний балл.
     */
    public Student( int id, String name, LocalDate date_of_admission,
                   boolean is_a_wizard, double average_score) {
        this.id=id;
        this.name = name;
        this.date_of_admission = date_of_admission;
        this.is_a_wizard = is_a_wizard;
        this.average_score = average_score;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate_of_admission() {
        return date_of_admission;
    }

    public void setDate_of_admission(LocalDate date_of_admission) {
        this.date_of_admission = date_of_admission;
    }

    public boolean get_wizard() {
        return is_a_wizard;
    }

    public void set_wizard(boolean is_a_wizard) {
        this.is_a_wizard = is_a_wizard;
    }

    public double getAverage_score() {
        return average_score;
    }

    public void setAverage_score(double average_score) {
        this.average_score = average_score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date_of_admission=" + date_of_admission +
                ", is_a_wizard=" + is_a_wizard +
                ", average_score=" + average_score +
                '}';
    }

    /**
     * Определение равенства студентов, не учитывая их id.
     *
     * @param o студент.
     * @return равны или нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return
                is_a_wizard == student.is_a_wizard &&
                Double.compare(student.average_score, average_score) == 0 &&
                Objects.equals(name, student.name) &&
                Objects.equals(date_of_admission, student.date_of_admission);
    }

}
