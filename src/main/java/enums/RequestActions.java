package enums;

public enum RequestActions {
    LOGIN("login"),
    REGISTER("register"),
    REMOVE_USER("remove_user"),
    CHANGE_PROFILE_PICTURE("change_profile_pic"),
    GET_THIS_USER("get_this_user"),
    CHANGE_NICKNAME("change_nickname"),
    CHANGE_PASSWORD("change_password"),
    GET_GAME_MAP("get_game_map"),
    GET_INVITATIONS("get_invitations"),
    SEND_INVITATIONS("send_invitaions"),
    ARE_INVITATIONS_ACCEPTED("are_reqs_accepted"),
    ACCEPT_INVITAION("Accept_request"),
    GET_USER_BY_USERNAME("get_user_by_username"),
    PANEL_COMMAND("panel_command"),
    IS_MY_TURN("is_my_turn?"),
    NEW_GAME("new_game"),
    PASS_TURN("pass_turn"),
    GET_THIS_PLAYER("get_this_player");

    public final String code;

    RequestActions(String actionStr) {
        this.code = actionStr;
    }

}
