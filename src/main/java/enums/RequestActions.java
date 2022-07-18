package enums;

public enum RequestActions {
    LOGIN("login"), REGISTER("register"), REMOVE_USER("remove_user"), CHANGE_PROFILE_PICTURE("change_profile_pic"), GET_THIS_USER("get_this_user");

    public final String code;

    RequestActions(String actionStr) {
        this.code = actionStr;
    }

}
