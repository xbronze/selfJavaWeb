package com.dingli;

import java.util.Arrays;

/**
 * @author: xbronze
 * @date: 2024-10-14 10:39
 * @description: TODO
 */
public class Investigate {

    private String username;
    private String gender;
    private String[] hobbies;
    private String evaluation;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    @Override
    public String toString() {
        return "Investigate{" +
                "username='" + username + '\'' +
                ", gender='" + gender + '\'' +
                ", hobbies=" + Arrays.toString(hobbies) +
                ", evaluation='" + evaluation + '\'' +
                '}';
    }
}
