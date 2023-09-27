package ExceptionArrayList;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList<T> {
    private int number;
    private List<T> listArrayList;

    public MyArrayList() {
        number = 0;
        listArrayList = new ArrayList<>();
    }

    public void addToArrayList(int index, T element) throws MyExceptionArrayList {
        if (number < 10) {
            listArrayList.add(index, element);
            number++;
        } else {
            throw new MyExceptionArrayList("You try to add more than 10 elements to ArrayList");
        }
    }
}
