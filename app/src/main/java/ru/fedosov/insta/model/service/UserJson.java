package ru.fedosov.insta.model.service;

/**
 * Created by fedosov on 9/6/16.
 */

import java.util.List;

/**{
 "login": "asd",
 "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dpbiI6ImFzZCIsInBhc3N3b3JkIjoiYXNkIiwiaWF0IjoxNDczMTczODkzLCJleHAiOjE0NzMxNzc0OTN9.psYQv10GXGVNHJ47UKJ8qQ0Tmws5FEuIKpqWZkjuigk",
 "insta_data": [
 "57bc6626167f784647847320",
 "57bc6630167f784647847321",
 "57beea5af37e5f3934a68ab4",
 "57beea8c1bdc7f67341bb5b0",
 "57beec38e20b885335b2f561",
 "57beec9d2381868c35166f93",
 "57beecf7e33fefe1353841af",
 "57beed09e33fefe1353841b0",
 "57beed0ae33fefe1353841b1",
 "57beed0be33fefe1353841b2",
 "57beed0be33fefe1353841b3",
 "57beed0be33fefe1353841b4",
 "57beed0ce33fefe1353841b5",
 "57beed0ce33fefe1353841b6",
 "57beed0ce33fefe1353841b7",
 "57beed0ce33fefe1353841b8",
 "57beed0ce33fefe1353841b9",
 "57beed0de33fefe1353841ba",
 "57beed0de33fefe1353841bb",
 "57beed0de33fefe1353841bc",
 "57beed0de33fefe1353841bd",
 "57beed0de33fefe1353841be",
 "57bef2d00f55a034383c76cb"
 ]
 }**/
public class UserJson {
    String login;
    String token;
    List<String> insta_data;

    public UserJson(String login, String token, List<String> insta_data) {
        this.login = login;
        this.token = token;
        this.insta_data = insta_data;
    }

    @Override
    public String toString() {
        return "UserJson{" +
                "login='" + login + '\'' +
                ", token='" + token + '\'' +
                ", insta_data=" + insta_data +
                '}';
    }

    public String getToken() {
        return token;
    }
}
