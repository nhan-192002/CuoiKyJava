/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.server;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public class Room {
    public static int currentRoomID = 1;
    public int id;
    public String name;
    public List<String> users;

    public Room(int id, String name, List<String> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Room() {
    }
    
    
}
