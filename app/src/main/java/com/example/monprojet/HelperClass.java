package com.example.monprojet;

public class HelperClass {

    String name,surname, email, phone, password ,birthday,school,level;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname= surname;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBirthday(){
        return birthday;
    }

    public void setBirthday(String birthday){
        this.birthday=birthday;
    }

    public String  getSchool(){
        return school;
    }

    public void setSchool(String school){
        this.school=school;
    }

    public String getLevel(){
        return level;
    }

    public void setLevel(String level){
        this.level=level;
    }

    public HelperClass(String name, String surname, String email, String phone,String school, String level,String birthday) {
        this.name = name;
        this.surname=surname;
        this.email = email;
        this.phone = phone;
        this.level=level;
        this.school=school;
        this.birthday=birthday;
    }

    public HelperClass() {
    }
}
