package enums;

public enum ParameterKeys {
    USERNAME("username"),
    PASSWORD("password"),
    NICKNAME("nickname"),
    ENUM("enum");

    public final String code;

    ParameterKeys(String code) {
        this.code = code;
    }
}
