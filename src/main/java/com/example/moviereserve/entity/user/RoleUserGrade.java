package com.example.moviereserve.entity.user;

import java.util.Arrays;

public enum RoleUserGrade {
    ROLE_ADMIN("관리자"),
    ROLE_VENUE_MANAGER("공연장 관리자"),
    ROLE_PERFORMANCE_MANAGER("공연 관리자"),
    ROLE_COMMON_MEMBER("일반 사용자");
    private String role;

    RoleUserGrade(String role){
        this.role = role;
    }

    // 유저 등급 권한을 찾을 때 사용
    // 전역으로 설정하여 언제든 클래스로 호출 할 수 있음
    public static RoleUserGrade findUserRole(String keyCode){
        return Arrays.stream(RoleUserGrade.values())
                .filter(roleUserGrade -> roleUserGrade.getGrade().equals(keyCode))
                .findAny()
                .orElseThrow();
    }

    public String getGrade(){
        return role;
    }
}
