public final class AccuWeatherAPI {

  public final List<Map<String, Object>> getWeather(String ciudad) {
    return Arrays.asList(new HashMap<String, Object>(){{
      put("DateTime", "2019-05-03T01:00:00-03:00");
      put("EpochDateTime", 1556856000);
      put("WeatherIcon", 33);
      put("IconPhrase", "Clear");
      put("IsDaylight", false);
      put("PrecipitationProbability", 0);
      put("MobileLink", "http://m.accuweather.com/en/ar/villa-vil/7984/");
      put("Link", "http://www.accuweather.com/en/ar/villa-vil/7984");
      put("Temperature", new HashMap<String, Object>(){{
        put("Value", 57);
        put("Unit", "F");
        put("UnitType", 18);
      }});
    }});
  }
}public class ProveedorClima {
  AccuWeatherAPI apiClima = new AccuWeatherAPI();

  public AccuWeatherAPI getApiClima() {
    return apiClima;
  }

  public Double getPromedioTemperatura(String ciudad){
    List<Map<String,Object>> condicionesClimaticas = this.getApiClima().getWeather(ciudad);
    List<Double> temperaturas = new ArrayList<>();

    for(int i=0; i<12; i++){
      HashMap<String,Object> hora_i = (HashMap<String,Object>) condicionesClimaticas.get(i);
      HashMap<String,Object> temperature = (HashMap<String, Object>) hora_i.get("Temperature");
      temperaturas.add((Integer) temperature.get("Value"));
    }

    return temperaturas.
        stream().
        mapToDouble(d->d).
        average().
        getAsDouble();
  }

  public Double getTemperaturaActual(String ciudad){
    List<Map<String,Object>> condicionesClimaticas = this.getApiClima().getWeather(ciudad);
    HashMap<String,Object> horaCero = (HashMap<String,Object>)condicionesClimaticas.get(0);
    HashMap<String,Object> temperature = (HashMap<String, Object>)horaCero.get("Temperature");
    return ((Double) temperature.get("Value"));
  }

}