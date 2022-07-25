package com.football.footballstanding.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final String REQUEST_PARAM_API_KEY = "APIkey";
    public static final String REQUEST_PARAM_ACTION = "action";
    public static final String REQUEST_PARAM_LEAGUE_ID = "league_id";
    public static final String ACTION_GET_STANDING = "get_standings";
    public static final String AUTHENTICATION_FAILED_RESPONSE = "Authentification failed!";
    public static final String NO_LEAGUE_FOUND_RESPONSE = "No league found (please check your plan)!!";
}
