package ExceptionArrayList;

public class MyExceptionArrayList extends Exception{
    private String message;
    private String customCause = "my default customCause";

    public MyExceptionArrayList() {}

    public MyExceptionArrayList(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return (message != null) ?  message : customCause;
    }

    @Override
    public synchronized Throwable getCause() {
        if (super.getCause() != null) {
            return this;
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (message == null) {
            message = customCause;
        }
        return (message != null) ? (s + ": " + message) : s;
    }

    @Override
    public String getLocalizedMessage() {
        return "You cannot add more than 10 elements to an array";
    }
}
