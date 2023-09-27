package ExceptionArrayList;

public class Main {

    public static void main(String[] args)  {

        MyArrayList <Integer> myArrayList = new MyArrayList<>();
        try{
            for (int i =0;i<11; i++){
                Integer element = i;
                myArrayList.addToArrayList(i, element);
            }
        }catch (MyExceptionArrayList e) {
            System.err.println(e.getMessage());
            System.out.println("toString(): "+e.toString());
            System.out.println("getCause(): "+e.getCause());
            e.printStackTrace();
        }
    }
}
