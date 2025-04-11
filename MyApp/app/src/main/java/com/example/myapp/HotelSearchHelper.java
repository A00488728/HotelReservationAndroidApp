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

    private static final String GOOGLE_API_KEY = "AIzaSyDtKygX50O9YMc-iGd1TqXC3iEvGQm18aQ"; // Add your Google API key here
    private static final String RAPIDAPI_KEY = "7370eb07b8mshb7849cba0f07efbp127cbbjsnd35d7480b4af"; // Add your RapidAPI key here

    private static final OkHttpClient client = new OkHttpClient();

    public interface HotelSearchCallback {
        void onHotelsFound(List<Hotel> hotels);  // Now returns a list of HotelItem objects
        void onError(String errorMessage);
    }

    // Step 1: Get coordinates of the city
    public static void searchHotelsInCity(String city, String arrivalDate, String departureDate,
                                          int adults, String childrenAge, int roomQty,
                                          String currencyCode, String languageCode, HotelSearchCallback callback) {

        // Google API URL to get the coordinates
        String geoUrl = String.format(Locale.US,
                "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s",
                city, GOOGLE_API_KEY);

        // Make the request to Google Maps API
        Request request = new Request.Builder().url(geoUrl).build();

        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError("Geocoding API failed");
                    return;
                }

                String responseBody = response.body().string();
                try {
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray results = json.getJSONArray("results");
                    JSONObject location = results.getJSONObject(0)
                            .getJSONObject("geometry")
                            .getJSONObject("location");

                    double latitude = location.getDouble("lat");
                    double longitude = location.getDouble("lng");

                    // Step 2: Fetch hotels by coordinates
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

    // Step 2: Booking API - Fetch hotels by coordinates
    private static void fetchHotelsByCoordinates(double latitude, double longitude, String arrivalDate, String departureDate,
                                                 int adults, String childrenAge, int roomQty, String currencyCode, String languageCode,
                                                 HotelSearchCallback callback) {

        // URL for Booking API (using RapidAPI)
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

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-host", "booking-com15.p.rapidapi.com")
                .addHeader("x-rapidapi-key", RAPIDAPI_KEY) // Add your RapidAPI key here
                .build();

        // Make the request to Booking API
        client.newCall(request).enqueue(new Callback() {
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    callback.onError("Hotel API failed");
                    return;
                }

                String responseBody = response.body().string();
                try {
                    JSONObject json = new JSONObject(responseBody);
                    JSONArray hotels = json.getJSONObject("data").getJSONArray("result");

                    List<Hotel> hotelList = new ArrayList<>();
                    for (int i = 0; i < Math.min(5, hotels.length()); i++) {
                        JSONObject hotel = hotels.getJSONObject(i);

                        // Extracting hotel details
                        String hotelName = hotel.optString("hotel_name", "N/A");
                        String city = hotel.optString("city", "N/A");

                        JSONObject priceBreakdown = hotel.optJSONObject("composite_price_breakdown");
                        Log.d("HotelDebug", "priceBreakdown: " + (priceBreakdown != null ? priceBreakdown.toString() : "null"));

                        JSONObject grossAmount = priceBreakdown != null ? priceBreakdown.optJSONObject("gross_amount_hotel_currency") : null;
                        Log.d("HotelDebug", "grossAmount: " + (grossAmount != null ? grossAmount.toString() : "null"));

                        double price = grossAmount != null ? grossAmount.optDouble("value", 0.0) : 0.0;
                        Log.d("HotelDebug", "Extracted price: " + price);

                        String currency = grossAmount != null ? grossAmount.optString("currency", "") : "";
                        Log.d("HotelDebug", "Extracted currency: " + currency);

                        String priceWithCurrency = String.format("%s %.2f", currency, price);
                        Log.d("HotelDebug", "Formatted priceWithCurrency: " + priceWithCurrency);



                        boolean isAvailable = !"1".equals(hotel.optString("soldout", "0"));
                        double distance = calculateDistance(latitude, longitude, hotel.optDouble("latitude", latitude), hotel.optDouble("longitude", longitude));

                        String formattedDistance = String.format("Distance: %.2f km", distance);
                        hotelList.add(new Hotel(hotelName, city, priceWithCurrency, "Available", formattedDistance));
                    }

                    callback.onHotelsFound(hotelList); // Send the list back to callback

                } catch (Exception e) {
                    callback.onError("Error parsing hotel data");
                }
            }

            public void onFailure(Call call, IOException e) {
                callback.onError("Hotel request failed: " + e.getMessage());
            }
        });
    }

    // Haversine formula to calculate the distance between two geographical points
    private static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Earth radius in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Result in km
    }
}
