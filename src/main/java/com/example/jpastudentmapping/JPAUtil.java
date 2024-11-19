package com.example.jpastudentmapping;

import java.sql.*;

public class JPAUtil {

    // Database configuration
    public static String url = "jdbc:mysql://localhost:3306/mx";
    public static String dbdriver = "com.mysql.cj.jdbc.Driver";
    public static String username = "root";
    public static String password = "admin";
    static Statement st;
    static Connection conn;

    static {
        try {
            Class.forName(dbdriver);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void setup(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    public static void createStatement() {
        try {
            Class.forName(dbdriver);
            conn = DriverManager.getConnection(url, username, password);
            st = conn.createStatement();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            e.printStackTrace();
            System.exit(0);
        }
    }

    // Drop table if exists
    public static void droptable(String sql) {
        try {
            createStatement();
            st.executeUpdate(sql);
        } catch (Exception e) {
        }
    }

    public void executeSQLCommand(String sql) throws Exception {
        st.executeUpdate(sql);
    }

    public static void checkData(String sql) {
        String[] starray = sql.split(" ");
        System.out.println("\n******** Table: " + starray[starray.length - 1] + " *******");
        try {
            ResultSet rs = st.executeQuery(sql);
            outputResultSet(rs);
            // conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void outputResultSet(ResultSet rs) throws Exception {
        ResultSetMetaData metadata = rs.getMetaData();

        int numcols = metadata.getColumnCount();
        String[] labels = new String[numcols];
        int[] colwidths = new int[numcols];
        int[] colpos = new int[numcols];
        int linewidth;

        linewidth = 1;
        for (int i = 0; i < numcols; i++) {
            colpos[i] = linewidth;
            labels[i] = metadata.getColumnLabel(i + 1); // get its label
            int size = metadata.getColumnDisplaySize(i + 1);
            if (size > 30 || size == -1) {
                size = 30;
            }
            int labelsize = labels[i].length();
            if (labelsize > size) {
                size = labelsize;
            }
            colwidths[i] = size + 1; // save the column the size
            linewidth += colwidths[i] + 2; // increment total size
        }

        StringBuffer divider = new StringBuffer(linewidth);
        StringBuffer blankline = new StringBuffer(linewidth);
        for (int i = 0; i < linewidth; i++) {
            divider.insert(i, '-');
            blankline.insert(i, " ");
        }
        // Put special marks in the divider line at the column positions
        for (int i = 0; i < numcols; i++) {
            divider.setCharAt(colpos[i] - 1, '+');
        }
        divider.setCharAt(linewidth - 1, '+');

        // Begin the table output with a divider line
        System.out.println(divider);
    }
}
