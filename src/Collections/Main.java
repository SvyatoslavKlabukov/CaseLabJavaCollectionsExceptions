package Collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {
    public static final int startPosition = 0;
    public static final int middlePosition = 500_000;
    public static final int finalPosition = -1;
    public static final int listCapacity = 1_000_000;
    public static final int quantityModified = 100_000;

    public static final int setCapacity = 10_000_000;
    public static final int quantityModifiedSet = 1_000_000;
    public static Integer element = 678_000;
    public static Integer elementSet = 1_000_001;
    public static Integer elementSetRemoved = 500_000;

    public static void main(String[] args) {
        //добавление(в начало, середину, конец), поиск, удаление(в начало, середину, конец)

        List<Integer> listArrayList = new ArrayList<>(listCapacity);
        LinkedList<Integer> listLinkedList = new LinkedList<>();
        for (int i = 0; i<listCapacity; i++){
            listArrayList.add(i);
            listLinkedList.add(i);
        }

        Set<Integer> treeSet = new TreeSet<>();
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i<setCapacity; i++){
            treeSet.add(i);
            hashSet.add(i);
        }

        //время 12046 мс - большое время из-за сдвига всех элементов вправо на один посредством копирования элементов
        addElement(listArrayList, startPosition, element, "Время добавления в начало списка ArrayList: ");

        //время 4 мс - не сдвигается список, переопределяются ссылка у первого элемента
        addElementLinkedList(listLinkedList, startPosition, element, "Время добавления в начало списка LinkedList: ");

        //время 7211 мс - сдвигаются все элементы вправо после места вставки
        addElement(listArrayList, middlePosition, element, "Время добавления в середину списка ArrayList: ");

        //время 398214 мс - сама вставка происходит быстро за счет переопределения ссылок соседних элементов, но
        //долго идет поиск места вставки
        addElementLinkedList(listLinkedList, middlePosition, element, "Время добавления в середину списка LinkedList: ");

        //время 3 мс - элементы не сдвигаются, быстро добавляется элемент в конец списка на готовое место в памяти
        addElement(listArrayList, finalPosition, element, "Время добавления в конец списка ArrayList: ");

        //время 2 мс - не сдвигается список, переопределяются ссылка у последнего элемента
        addElementLinkedList( listLinkedList, finalPosition, element, "Время добавления в конец списка LinkedList: ");

        //время 103675 мс - перебираются по порядку элементы (здесь не взятие по индексу!)
        searchElementList(listArrayList, element, "Время поиска элемента в ArrayList: ");

        //время 563665 мс - перебирается медленнее за счет более "тяжеловесных" элементов(из-за наличия ссылок)
        searchElementList(listLinkedList, element, "Время поиска элемента в LinkedList: ");

        //время 22243 мс - сдвигаются все элементы влево (аналогично добавлению в начало)
        removeElement(listArrayList, startPosition, "Время удаления в начале списка ArrayList: ");

        //время 6 мс - аналогично добавлению
        removeElementLinkedList(listLinkedList, startPosition, "Время удаления в начале списка LinkedList: ");

        //время 8471 мс - аналогично добавлению
        removeElement(listArrayList, middlePosition, "Время удаления в середине списка ArrayList: ");

        //время 495287 мс - аналогично добавлению
        removeElementLinkedList(listLinkedList, middlePosition, "Время удаления в середине списка LinkedList: ");

        //время 4 мс - аналогично добавлению
        removeElement(listArrayList, finalPosition, "Время удаления в конце списка ArrayList: ");

        //время 9 мс - аналогично добавлению
        removeElementLinkedList(listLinkedList, finalPosition, "Время удаления в конце списка LinkedList: ");

        //время 85 мс - количество элементов увеличено на порядок по сравнению со списками
        //Set работают быстрее List, HashSet быстрее TreeSet из-за того что TreeSet упорядочен, а HashSet нет
        addElementToSet(hashSet, elementSet, "Время добавления элемента в HashSet: ");

        //время 207 мс
        addElementToSet(treeSet, elementSet, "Время добавления элемента в TreeSet: ");

        //время 142 мс
        searchElementSet(hashSet, elementSet, "Время поиска элемента в HashSet: ");

        //время 190 мс
        searchElementSet(treeSet, elementSet, "Время поиска элемента в TreeSet: ");

        //время 56 мс
        removeElementSet(hashSet, elementSetRemoved, "Время удаления элемента в HashSet: ");

        //время 182 мс
        removeElementSet(treeSet, elementSetRemoved, "Время удаления элемента в TreeSet: ");


    }

    public static void addElement(List list, int position, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModified;i++) {
            if (position == finalPosition){
                list.add(element);
            } else {
                list.add(position, element);
            }
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }
    public static void addElementLinkedList(LinkedList list, int position, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModified;i++) {
            switch (position) {
                case finalPosition -> list.addLast(element);
                case startPosition -> list.addFirst(element);
                case middlePosition -> list.add(position, element);
            }
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void removeElement(List list, int position, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModified;i++) {
            switch (position) {
                case finalPosition -> list.remove(list.size()-1);
                case startPosition, middlePosition -> list.remove(position);
            }
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void removeElementLinkedList(LinkedList list, int position, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModified;i++) {
            switch (position) {
                case finalPosition -> list.removeLast();
                case startPosition -> list.removeFirst();
                case middlePosition -> list.remove(position);
            }
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void searchElementList(List list, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModified;i++) {
            int index = list.indexOf(element-i);
            //list.contains(element);
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void addElementToSet(Set set, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModifiedSet;i++) {
            set.add(element+i);
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void removeElementSet(Set set, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModifiedSet;i++) {
            set.remove(element+i);
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }

    public static void searchElementSet(Set set, Integer element, String str){
        long start = System.currentTimeMillis();
        for (int i = 0; i<quantityModifiedSet;i++) {
            set.contains(element-i);
        }
        System.out.println(str + (System.currentTimeMillis() - start));
    }
}