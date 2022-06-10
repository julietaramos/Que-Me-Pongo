public class Recomendador {
  public Atuendo recomendar(String ciudad, Usuario usuario) {
    this.verificarSiTieneUsosDisponibles(usuario);
    ProveedorClima proveedorClima = new ProveedorClima();
    Double temperaturaEnLaCiudad = proveedorClima.getPromedioTemperatura(ciudad);
    List<Prenda> todaLaRopaDelUsuario = usuario.todaSuRopa();
    List<Prenda> prendasRecomendadas = prendasPorTemperatura(todaLaRopaDelUsuario, temperaturaEnLaCiudad);
    return atuendoDeListaPrendas(prendasRecomendadas);
  }

  private void verificarSiTieneUsosDisponibles(Usuario usuario) {
    if (usuario.usosServicioAccuWeatherUltimoDia() > 9) {
      throw new RuntimeException("Has usado AccuWeather la cantidad de veces maxima.");
    }
    usuario.usoServicioAccuWeather();
  }


  private Atuendo atuendoDeListaPrendas(List<Prenda> prendasRecomendadas) {
    Prenda parteSuperior = elegirUnaDe(prendasRecomendadas, Categoria.PARTE_SUPERIOR);
    Prenda parteInferior = elegirUnaDe(prendasRecomendadas, Categoria.PARTE_INFERIOR);
    Prenda accesorio = elegirUnaDe(prendasRecomendadas, Categoria.ACCESORIO);
    Prenda calzado = elegirUnaDe(prendasRecomendadas, Categoria.CALZADO);
    Atuendo atuendo = new Atuendo(parteSuperior, parteInferior, calzado, accesorio);
    return atuendo;
  }

  private Prenda elegirUnaDe(List<Prenda> prendas, Categoria categoria) {
    return prendas.
        stream().
        filter(prenda -> prenda.getCategoria() == categoria).
        collect(Collectors.toList()).
        get(0);
  }


  private List<Prenda> prendasPorTemperatura(List<Prenda> prendas, Double temperatura) {
    return prendas.
        stream().
        filter(prenda -> prenda.getTemperaturaDeUso().esTemperaturaValida(temperatura)).
        collect(Collectors.toList());
  }


}