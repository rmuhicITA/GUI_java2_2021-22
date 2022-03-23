package com.app_with_db;

import com.app_with_db.connection.ConnectionPool;
import com.app_with_db.dao.CityDao;
import com.app_with_db.gui.CityPanel;
import com.app_with_db.model.City;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class App {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createGui);
        /*try {
            ConnectionPool connectionPool = new ConnectionPool();
            CityDao cityDao = new CityDao(connectionPool);

            List<City> cityList = cityDao.retrieveAll();

            //cityList.forEach(System.out::println);

            for (City city : cityList) {
                System.out.println(city);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }*/
    }

    private static void createGui() {
        try {
            JFrame frame = new JFrame();
            frame.setTitle("Cities from mysql db");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            ConnectionPool connectionPool = new ConnectionPool();
            CityDao cityDao = new CityDao(connectionPool);
            CityPanel cityPanel = new CityPanel(cityDao);

            frame.setContentPane(cityPanel);
            frame.pack();
            frame.setVisible(true);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
