package DDD.framework.basic;

public class ExceptionParam {

    private String key;
    private Object value;

    public ExceptionParam(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public static ExceptionParam line(int line) {
        return new ExceptionParam("line", line);
    }

    public static ExceptionParam contentType(String contentType) {
        return new ExceptionParam("content-type", contentType);
    }

    public static ExceptionParam encoding(String encoding) {
        return new ExceptionParam("encoding", encoding);
    }

}
