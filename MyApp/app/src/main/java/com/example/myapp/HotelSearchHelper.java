package com.example.myapp;

import android.util.Log;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class HotelSearchHelper {

    // Google Maps Geocoding API key (used to get coordinates from city name)
    private static final String GOOGLE_API_KEY = "AIzaSyDtKygX50O9YMc-iGd1TqXC3iEvGQm18aQ";

    // RapidAPI key for accessing the Booking.com API
    private static final String RAPIDAPI_KEY = "7370eb07b8mshb7849cba0f07efbp127cbbjsnd35d7480b4af";

    // OkHttpClient instance for making HTTP requests
    private static final OkHttpClient client = new OkHttpClient();

    // Callback interface to return results asynchronously
    public interface HotelSearchCallback {
        void onHotelsFound(List<Hotel> hotels);  // Called when hotel list is successfully retrieved
        void onError(String errorMessage);       // Called on failure
    }

    /**
     * Step 1: Get coordinates (lat, lng) of a city using Google Maps Geocoding API
     */
    public static void searchHotelsInCity(String city, String arrivalDate, String departureDate,
                                          int adults, String childrenAge, int roomQty,
                                          String currencyCode, String languageCode, HotelSearchCallback callback) {

        // Build URL for Google Geocoding API
        String geoUrl = String.format(Locale.US,
                "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
                city, GOOGLE_API_KEY);

        // Make asynchronous request to Google API
        Request request = new Request.Builder().url(geoUrl).build();

        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError("Geocoding API failed");
                    return;
                }

                String responseBody = response.body().string();
                try {
                    // Parse response JSON to extract latitude and longitude
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray results = json.getJSONArray("results");
                    JSONObject location = results.getJSONObject(0)
                            .getJSONObject("geometry")
                            .getJSONObject("location");

                    double latitude = location.getDouble("lat");
                    double longitude = location.getDouble("lng");

                    // Step 2: Use lat/lng to fetch nearby hotels
                    fetchHotelsByCoordinates(latitude, longitude, arrivalDate, departureDate,
                            adults, childrenAge, roomQty, currencyCode, languageCode, callback);

                } catch (Exception e) {
                    callback.onError("Failed to parse geocoding result");
                }
            }

            public void onFailure(Call call, IOException e) {
                callback.onError("Request failed: " + e.getMessage());
            }
        });
    }

    /**
     * Step 2: Fetch hotel listings from Booking API using latitude & longitude
     */
    private static void fetchHotelsByCoordinates(double latitude, double longitude, String arrivalDate, String departureDate,
                                                 int adults, String childrenAge, int roomQty, String currencyCode, String languageCode,
                                                 HotelSearchCallback callback) {

        // Build URL with query parameters for Booking API via RapidAPI
        HttpUrl url = HttpUrl.parse("https://booking-com15.p.rapidapi.com/api/v1/hotels/searchHotelsByCoordinates")
                .newBuilder()
                .addQueryParameter("latitude", String.valueOf(latitude))
                .addQueryParameter("longitude", String.valueOf(longitude))
                .addQueryParameter("arrival_date", arrivalDate)
                .addQueryParameter("departure_date", departureDate)
                .addQueryParameter("adults", String.valueOf(adults))
                .addQueryParameter("children_age", childrenAge)
                .addQueryParameter("room_qty", String.valueOf(roomQty))
                .addQueryParameter("units", "metric")
                .addQueryParameter("page_number", "1")
                .addQueryParameter("temperature_unit", "c")
                .addQueryParameter("languagecode", languageCode)
                .addQueryParameter("currency_code", currencyCode)
                .addQueryParameter("location", "US")
                .build();

        // Set headers and initiate the request
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "booking-com15.p.rapidapi.com")
                .addHeader("x-rapidapi-key", RAPIDAPI_KEY)
                .build();

        // Make asynchronous request to Booking API
        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError("Hotel API failed");
                    return;
                }

                String responseBody = response.body().string();
                try {
                    // Parse the response JSON
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray hotels = json.getJSONObject("data").getJSONArray("result");

                    List<Hotel> hotelList = new ArrayList<>();

                    // Loop through the first 5 hotel results
                    for (int i = 0; i < Math.min(5, hotels.length()); i++) {
                        JSONObject hotel = hotels.getJSONObject(i);

                        // Extract hotel info
                        String hotelName = hotel.optString("hotel_name", "N/A");
                        String city = hotel.optString("city", "N/A");

                        // Extract price info if available
                        JSONObject priceBreakdown = hotel.optJSONObject("composite_price_breakdown");
                        JSONObject grossAmount = priceBreakdown != null ? priceBreakdown.optJSONObject("gross_amount_hotel_currency") : null;

                        double price = grossAmount != null ? grossAmount.optDouble("value", 0.0) : 0.0;
                        String currency = grossAmount != null ? grossAmount.optString("currency", "") : "";
                        String priceWithCurrency = String.format("%s %.2f", currency, price);

                        // Check hotel availability
                        boolean isAvailable = !"1".equals(hotel.optString("soldout", "0"));

                        // Calculate distance from user-specified coordinates
                        double distance = calculateDistance(latitude, longitude,
                                hotel.optDouble("latitude", latitude),
                                hotel.optDouble("longitude", longitude));
                        String formattedDistance = String.format("Distance: %.2f km", distance);

                        // Add hotel to result list
                        hotelList.add(new Hotel(hotelName, city, priceWithCurrency,
                                isAvailable ? "Available" : "Sold Out", formattedDistance));
                    }

                    // Pass results to callback
                    callback.onHotelsFound(hotelList);

                } catch (Exception e) {
                    callback.onError("Error parsing hotel data");
                }
            }

            public void onFailure(Call call, IOException e) {
                callback.onError("Hotel request failed: " + e.getMessage());
            }
        });
    }

    /**
     * Utility method: Calculate distance between two geographic coordinates
     * Uses Haversine formula
     */
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of Earth in kilometers
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Return distance in kilometers
    }
}
