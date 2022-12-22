/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author naser
 */
public class Teacher {
    private String instName;
    private String username;
    private String password;
    
    
    //intializing the instructor
    public Teacher(String instName, String username, String password) {
        this.instName = instName;
        this.username = username;
        this.password = password;
    }
    
    //creating setters &getters
    public String getInstName() {
        return instName;
    }

    public void setInstName(String instName) {
        this.instName = instName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
