public class Program {

    public static void main(String[] args) {

        // 1. Создание объектов и работа с полями
        Person tom = new Person();
        System.out.println("--- Значения полей по умолчанию ---");
        System.out.println(tom.name);   // null
        System.out.println(tom.age);    // 0

        tom.name = "Tom";
        tom.age = 41;

        Person bob = new Person();
        bob.name = "Bob";
        bob.age = 46;

        // 2. Вызов методов
        System.out.println("\n--- Методы ---");
        tom.print();
        bob.print();

        // 3. Метод, вызывающий другие методы класса
        System.out.println("\n--- Вызов методов внутри класса ---");
        tom.printFull();

        // 4. Метод с параметром
        System.out.println("\n--- Параметры метода ---");
        tom.say("Hello work");
        bob.say("Hello gold");

        // 5. Калькулятор: обычные параметры и параметры переменной длины
        System.out.println("\n--- Calculator ---");
        Calculator calc = new Calculator();
        calc.sum(1, 2);                 // 3
        calc.sum(5, 6);                 // 11
        calc.sumAll(1, 2, 3);           // 6
        calc.sumAll(1, 2, 3, 4, 5);     // 15
        calc.sumAll();                  // 0
        calc.sumWithMessage("Sum of {1, 2, 3}: ", 1, 2, 3);
        calc.sumWithMessage("Zero sum: ");

        // 6. Передача примитива в метод (копия значения)
        System.out.println("\n--- Примитив как параметр ---");
        int num = 22;
        calc.twice(num);
        System.out.printf("num in main (after twice): %d\n", num);   // 22

        // 7. Передача объекта в метод (копия ссылки)
        System.out.println("\n--- Объект как параметр (меняем поле) ---");
        Person p1 = new Person();
        p1.age = 22;
        System.out.printf("p1.age in main (before twice): %d\n", p1.age);
        calc.twice(p1);
        System.out.printf("p1.age in main (after twice): %d\n", p1.age);   // 44

        // 8. Передача объекта, внутри метода создаётся новый объект
        System.out.println("\n--- Объект как параметр (пересоздаём объект) ---");
        Person p2 = new Person();
        p2.age = 22;
        System.out.printf("p2.age in main (before reset): %d\n", p2.age);
        calc.reset(p2);
        System.out.printf("p2.age in main (after reset): %d\n", p2.age);   // 22
    }
}

class Person {
    String name;
    int age;

    void print() {
        System.out.printf("Name: %s \tAge: %d\n", name, age);
    }

    void printName() {
        System.out.printf("Name: %s\n", name);
    }

    void printAge() {
        System.out.printf("Age: %d\n", age);
    }

    void printFull() {
        printName();
        printAge();
    }

    void say(String message) {
        System.out.println(name + " says: " + message);
    }
}

class Calculator {

    void sum(int a, int b) {
        int result = a + b;
        System.out.println(result);
    }

    void sumAll(int... nums) {
        int result = 0;
        for (int n : nums)
            result += n;
        System.out.println(result);
    }

    void sumWithMessage(String message, int... nums) {
        System.out.print(message);
        int result = 0;
        for (int n : nums)
            result += n;
        System.out.println(result);
    }

    // примитив: меняется только копия
    void twice(int n) {
        n = n + n;
        System.out.printf("n in twice: %d\n", n);
    }

    // объект: меняется тот же объект через копию ссылки
    void twice(Person p) {
        p.age = p.age + p.age;
        System.out.printf("p.age in twice: %d\n", p.age);
    }

    // объект: ссылка внутри метода указывает на новый объект
    void reset(Person p) {
        p = new Person();
        p.age = 44;
        System.out.printf("p.age in reset: %d\n", p.age);
    }
}