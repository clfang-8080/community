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
}
