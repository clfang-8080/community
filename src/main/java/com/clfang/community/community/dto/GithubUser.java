package com.clfang.community.community.dto;


/**
 * ClassName:GithubUser
 * Package:com.clfang.community.community.dto
 * Description:
 *
 * @Date:2020/3/5 0:22
 * @Author:CLFang
 */
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatar_url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
