package MyException;
// 1. При первой итерации цикла с помощью new MyException() пытаемся создать экземпляр класса, переходим в
//класс MyException и выполняем static блок. Далее вызывается init(), в которой бросается исключение throw MyException(),
//далее в конструкторе инициализируется переменная s="Hello". В итоге при инициализации статического блока возникла ошибка,
//которую мы отлавливаеи в catch: e = java.lang.ExceptionInInitializerError

//2. Так как метод getCause() возвращает объект с информацией о предыдущей ошибке, то он вернет MyException и
//мы попадаем в if и печатаем: e instanceof MyException.MyException, s = Hello

//3. При второй итерации цикла и вызове new MyException() мы НЕ ПОПАДАЕМ в static блок (он выполняется только один
// раз при первой загрузке класса), поэтому класс не определен и получаем ошибку NoClassDefFoundError.
// В этой итерации цикла метод getCause() вернет предшествующую ошибку ExceptionInInitializerError, поэтому
//instanceof вернет false и в if мы не попадем.

public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
            try {
                new MyException();
            } catch (Throwable e) {
                System.out.println("e = " + e);
                if (e.getCause() instanceof MyException) {
                    MyException ex = (MyException) e.getCause();
                    System.out.println("e instanceof " + MyException.class.getName() + ", s = " + ex.get());
                }
            }
        }
    }
}
