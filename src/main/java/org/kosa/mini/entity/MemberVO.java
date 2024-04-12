package org.kosa.mini.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {

    private String member_id;
    private String user_id;
    private String user_password;
    private String user_name;
    private int user_age;
    private String user_address;
    private String user_phone;
    private String user_sex;

    // 비밀번호 확인
    public boolean isEqualsPwd(String pwd) {
        return this.user_password.equals(pwd);
    }

//    private String userPasswordCheck;
//
//    private String err;
//
//    //uuid
//    private String useruuid;
//
//    //자동로그인 여부
//    private String autologin;
//
//    //검색키
//    private String searchKey;

}
