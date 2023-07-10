package com.example.norza.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }
    //registrationId==goolgle(이것만 써서 if문 필요X)
    public static OAuthAttributes of(String registrationId,String userNameAttributeName,Map<String,Object>attributes) {
        return ofGoogle(userNameAttributeName,attributes);
    }

    //구글에서 받아온 attribute를 나의 형태(String)으로 바꾸는과정
    public static OAuthAttributes ofGoogle(String userNameAttributeName,Map<String,Object> attributes){
        return OAuthAttributes.builder()
                .name((String)attributes.get("name"))
                .email((String)attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    //기존 멤버가 없을때만 이렇게 만듦
    public User toEnttity() {
        String nickName = generateRandomId();
        return User.builder()
                .email(email)
                .nickname(nickName)
                .build();
    }

    public static String generateRandomId() {
        StringBuilder sb = new StringBuilder();
        sb.append("익명 ");
        for (int i = 0; i < 8; i++) {
            int digit = (int) (Math.random() * 10);
            sb.append(digit);
        }
        return sb.toString();
    }
}
