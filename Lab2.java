import java.util.ArrayList;
import java.util.List;

class Name {
    private final String firstName;   
    private final String lastName;   
    private final String patronymic;  

    public Name(String firstName) {
        this(firstName, null, null);
    }

    public Name(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public Name(String firstName, String lastName, String patronymic) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lastName != null && !lastName.isEmpty()) {
            sb.append(lastName).append(" ");
        }
        if (firstName != null && !firstName.isEmpty()) {
            sb.append(firstName).append(" ");
        }
        if (patronymic != null && !patronymic.isEmpty()) {
            sb.append(patronymic);
        }
        return sb.toString().trim(); 
    }
}


class House {
    private final int floors;

    public House(int floors) {
        if (floors <= 0) {
            throw new IllegalArgumentException("Количество этажей должно быть больше 0");
        }
        this.floors = floors;
    }

    @Override
    public String toString() {
        String ending;
        int lastDigit = floors % 10;
        int lastTwoDigits = floors % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) {
            ending = "этажами";
        } else if (lastDigit == 1) {
            ending = "этажом";
        } else {
            ending = "этажами";
        }

        return "дом с " + floors + " " + ending;
    }
}

class Department {
    public String name;
    public Employee manager; 
    public List<Employee> employees = new ArrayList<>(); 

    public Department(String name) {
        this.name = name;
    }
}

class Employee {
    public String name;
    public Department department;

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
        
        this.department.employees.add(this); 
    }

    public List<Employee> getColleagues() {
        return department.employees; 
    }

    @Override
    public String toString() {
        if (department.manager == this) {
            return name + " начальник отдела " + department.name;
        } 
        else {
            String managerName = (department.manager != null) ? department.manager.name : "не назначен";
            return name + " работает в отделе " + department.name + ", начальник которого " + managerName;
        }
    }
}

class Fraction {
    private final int numerator;   
    private final int denominator; 
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction sum(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction sum(int value) {
        return this.sum(new Fraction(value, 1));
    }

    public Fraction minus(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction minus(int value) {
        return this.minus(new Fraction(value, 1));
    }

    public Fraction multiply(Fraction other) {
        return new Fraction(this.numerator * other.numerator, this.denominator * other.denominator);
    }

    public Fraction multiply(int value) {
        return this.multiply(new Fraction(value, 1));
    }

    public Fraction div(Fraction other) {
        return new Fraction(this.numerator * other.denominator, this.denominator * other.numerator);
    }

    public Fraction div(int value) {
        return this.div(new Fraction(value, 1));
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}

public class Lab2 {
        public static void main(String[] args) {
        
        System.out.println("ЗАДАНИЕ 1. Задача 3. Имена");

        Name cleopatra = new Name("Клеопатра");
        Name pushkin = new Name("Александр", "Пушкин", "Сергеевич");
        Name mayakovsky = new Name("Владимир", "Маяковский");

        System.out.println("Результат вывода имен:");
        System.out.println(cleopatra);
        System.out.println(pushkin);
        System.out.println(mayakovsky);


        System.out.println("\n");
        System.out.println("ЗАДАНИЕ 1. Задача 5. Дом");
    
        House house1 = new House(1);
        House house5 = new House(5);
        House house23 = new House(23);

        System.out.println("Результат вывода домов:");
        System.out.println(house1);
        System.out.println(house5);
        System.out.println(house23);

        System.out.println("\n");
        System.out.println("ЗАДАНИЕ 2. Задача 4. Сотрудники и отделы");
       
        Department itDept = new Department("IT");
        Employee petrov = new Employee("Петров", itDept);
        Employee kozlov = new Employee("Козлов", itDept);
        Employee sidorov = new Employee("Сидоров", itDept);

        itDept.manager = kozlov;

        System.out.println("Список сотрудников после назначения начальника:");
        System.out.println(petrov);
        System.out.println(kozlov);
        System.out.println(sidorov);

        System.out.println("\n");
        System.out.println("ЗАДАНИЕ 3. Задача 4. Сотрудники и отделы (Расширение)");
        
        System.out.println("Запрос списка всех сотрудников отдела через ссылку на Петрова:");
        List<Employee> colleagues = petrov.getColleagues();
        for (Employee emp : colleagues) {
            System.out.println(" - " + emp.name + " (" + (emp.department.manager == emp ? "Начальник" : "Сотрудник") + ")");
        }

        System.out.println("\n");
        System.out.println("ЗАДАНИЕ 4. Задача 5. Создаем Имена (Новые конструкторы)");

        Name n4_1 = new Name("Клеопатра");                             
        Name n4_2 = new Name("Александр", "Пушкин", "Сергеевич");       
        Name n4_3 = new Name("Владимир", "Маяковский");                 
        Name n4_4 = new Name("Христофор", "Бонифатьевич");              

        System.out.println("Результат вывода созданных имен:");
        System.out.println("1. " + n4_1);
        System.out.println("2. " + n4_2);
        System.out.println("3. " + n4_3);
        System.out.println("4. " + n4_4);

        System.out.println("\n");
        System.out.println("ЗАДАНИЕ 5. Задача 5. Дроби");
        
        Fraction fracA = new Fraction(1, 2);
        Fraction fracB = new Fraction(1, 4);

        System.out.println("Демонстрация базовых методов:");
        System.out.println(fracA + " + " + fracB + " = " + fracA.sum(fracB));
        System.out.println(fracA + " - " + fracB + " = " + fracA.minus(fracB));
        System.out.println(fracA + " * " + fracB + " = " + fracA.multiply(fracB));
        System.out.println(fracA + " / " + fracB + " = " + fracA.div(fracB));

        Fraction f1 = new Fraction(1, 3);
        Fraction f2 = new Fraction(2, 3);
        System.out.println("\nПример из задания:");
        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));

        Fraction f3 = new Fraction(1, 2);
        Fraction finalResult = f1.sum(f2).div(f3).minus(5);
        System.out.println("\nВычисление финального выражения:");
        System.out.println("f1.sum(f2).div(f3).minus(5) = " + finalResult);
    }
}
