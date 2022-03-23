package com.app_with_db.gui;

import com.app_with_db.dao.CityDao;
import com.app_with_db.model.City;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class CityPanel extends JPanel {

    private JTable cityTable;
    private CityDao cityDao;
    private JButton jButton;

    public CityPanel(CityDao cityDao) {
        this.cityDao = cityDao;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        cityTable = new JTable();
        Vector<String> columnNames = getColumnNames();
        Vector<Vector<Object>> data = createTableData(cityDao);
        CityModel cityModel = new CityModel(columnNames, data);
        cityTable.setModel(cityModel);
        cityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jButton = new JButton("Obrisi");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(cityTable.getSelectedRow() != -1) {
                    // remove selected row from the model
                    cityModel.removeRow(cityTable.getSelectedRow());
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });
        add(new JScrollPane(cityTable));
        add(jButton);
    }

    private Vector<String> getColumnNames() {
        /*Vector<String> columnNames = new Vector<>();
        columnNames.add("ID");
        columnNames.add("City name");
        columnNames.add("Country ID");*/

        return cityDao.getColumnNames();
    }

    private Vector<Vector<Object>> createTableData(CityDao cityDao) {
        Vector<Vector<Object>> tableData = new Vector<>();
        try {
            List<City> cityList = cityDao.retrieveAll();
            for (City city : cityList) {
                Vector<Object> rowData = new Vector<>();
                rowData.addElement(city.getCityId());
                rowData.addElement(city.getCity());
                rowData.addElement(city.getCountry_id());
                rowData.addElement(city.getLast_update());
                tableData.addElement(rowData);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tableData;
    }

    private class CityModel extends AbstractTableModel {

        private Vector<String> columnNames;
        private Vector<Vector<Object>> tableData;

        public CityModel(Vector<String> columnNames, Vector<Vector<Object>> data) {
            this.columnNames = columnNames;
            this.tableData = data;
        }

        @Override
        public int getRowCount() {
            return tableData.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Vector<Object> rowData = tableData.get(rowIndex);
            Object value = rowData.get(columnIndex);
            //System.out.println(value);
            return value;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames.get(column);
        }

        public void removeRow(int row) {
            tableData.removeElementAt(row);
            try {
                cityDao.delete(cityDao.retrieve(row+1));
                fireTableRowsDeleted(row,row);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return columnIndex == 1;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Vector<Object> rowData = tableData.get(rowIndex);
            rowData.remove(columnIndex);
            rowData.add(columnIndex, aValue);
            fireTableCellUpdated(rowIndex, columnIndex);
            int cityId = (int) rowData.get(0);
            String cityName = (String) aValue;
            City city = new City();
            try {
                city = cityDao.retrieve(cityId);
                city.setCity(cityName);
                cityDao.update(city);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }

        }
    }
}
