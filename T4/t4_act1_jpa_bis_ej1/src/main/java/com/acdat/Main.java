package com.acdat;

import com.acdat.dao.UserDAO;

public class Main {
    public static void main(String[] args) {
        UserDAO dao = new UserDAO();
        dao.listar();
    }
}