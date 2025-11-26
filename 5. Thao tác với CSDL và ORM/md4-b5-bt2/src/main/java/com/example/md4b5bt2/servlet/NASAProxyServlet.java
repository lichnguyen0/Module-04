package com.example.md4b5bt2.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet("/nasa-proxy")
public class NASAProxyServlet extends HttpServlet {
    private static final String NASA_API_URL = "https://api.nasa.gov/planetary/apod";
    private static final String API_KEY = "r4o7dnrcJqC9SQAWv17IV7eYGqoxfchZ7kjx7ynk";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String url = NASA_API_URL + "?api_key=" + API_KEY;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            // Set CORS headers để cho phép frontend gọi
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "GET");
            resp.setContentType("application/json");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            resp.getWriter().write(response.toString());
        } catch (Exception e) {
            try {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"Failed to fetch NASA data\"}");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}