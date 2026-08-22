package com.ttplatform.matchmaking.infra.entity;

import java.util.List;
import java.util.Map;

public class SetInfo {
    private Integer setNumber;
    private List<GameInfo> games;
    private Map<String, Integer> score;
    private String winner;
}
