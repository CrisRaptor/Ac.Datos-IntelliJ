package com.iesbelen.dam.apirest.apithymeleaf.vista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

//Clase de CRUD lado cliente, no se utiliza en el programa
public class GestorEmpleados {

    public void getRequest() {
        HttpURLConnection conn = null;

        try {
            URL url = new URL("http://localhost:8080/empleados");

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    System.out.println(jsonObject.getInt("id")
                            + " - " + jsonObject.getString("nombre"));
                }
            } else {
                System.out.println("Error al conectar: " + responseCode);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public void postRequest() {
        HttpURLConnection conn = null;
        String jsonInputString = null;
        try {
            jsonInputString = new JSONObject()
                    .put("id", 1234)
                    .put("nombre", "Diez")
                    .put("puesto", "Dependiente")
                    .put("departamento", new JSONObject()
                            .put("id", 20)
                            .put("nombre", "Marketing")
                            .put("ubicacion", "Barcelona")
                            .toString()).toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        try {
            URL url = new URL("http://localhost:8080/empleados");


            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200)
                System.out.println("Empleado insertado");
            else {
                System.out.println("Fallo en la conexion");
                Scanner scanner = new Scanner(conn.getErrorStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONObject jsonObject = new JSONObject(response)
                        .getJSONArray("errors").getJSONObject(0);
                System.out.println(jsonObject.get("defaultMessage"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public void deleteRequest(String id) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("http://localhost:8080/empleados/"+id);

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Empleado eliminado");
            } else {
                System.out.println("Fallo en la conexion");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }

    public void updateRequest() {
        HttpURLConnection conn = null;
        String jsonInputString = null;
        try {
            URL url = new URL("http://localhost:8080/empleados/1234");

            jsonInputString = new JSONObject()
                    .put("id", 1234)
                    .put("nombre", "Dieces")
                    .put("puesto", "Dependiente")
                    .put("departamento", new JSONObject()
                            .put("id", 20)
                            .put("nombre", "Marketing")
                            .put("ubicacion", "Barcelona")
                            .toString()).toString();

            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Empleado actualizado");
            } else {
                System.out.println("Fallo en la conexion");
                Scanner scanner = new Scanner(conn.getErrorStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();

                JSONObject jsonObject = new JSONObject(response)
                        .getJSONArray("errors").getJSONObject(0);
                System.out.println(jsonObject.get("defaultMessage"));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                conn.disconnect();
        }
    }
}
