public class Recomendaciones {
  Usuario ian;
  Color negro;
  Color blanco;
  Color rojo;
  Color verde;
  Color azul;
  RangoDeTemperaturas frio;
  RangoDeTemperaturas calor;

  @BeforeEach
  public void setup() throws Exception {
    negro = new Color(256,256,256);
    blanco = new Color(0,0,0);
    rojo = new Color(256,0,0);
    verde = new Color(0,256,0);
    azul = new Color(0,0,256);
    frio = new RangoDeTemperaturas(-10.00,20.00);
    calor = new RangoDeTemperaturas(20.00,100.00);

    julieta = new Usuario();

    Guardarropa guardaRopa1 = new Guardarropa(julieta);

    Prenda camperaInvernal = new Prenda(Categoria.PARTE_SUPERIOR, Tipo.CAMPERA, new Tela(Material.FRIZA),frio, negro);
    Prenda musculosaRoja = new Prenda(Categoria.PARTE_SUPERIOR, Tipo.MUSCULOSA, new Tela(Material.ALGODON), calor, rojo);
    Prenda sweaterVerde = new Prenda(Categoria.PARTE_SUPERIOR, Tipo.SWEATER, new Tela(Material.LANA), frio, verde);
    Prenda bermudaAzul = new Prenda(Categoria.PARTE_INFERIOR, Tipo.BERMUDA, new Tela(Material.JEAN), calor, azul);
    Prenda jeanNegro = new Prenda(Categoria.PARTE_INFERIOR, Tipo.PANTALON, new Tela(Material.JEAN), frio, negro);
    Prenda shortBlanco = new Prenda(Categoria.PARTE_INFERIOR, Tipo.SHORT, new Tela(Material.ALGODON), calor, blanco);
    Prenda zapatosDeCuero = new Prenda(Categoria.CALZADO, Tipo.ZAPATO, new Tela(Material.CUERO), new RangoDeTemperaturas(), negro);
    Prenda ojotasBlancas = new Prenda(Categoria.CALZADO, Tipo.OJOTA, new Tela(Material.PLASTICO), calor, blanco);
    Prenda bufandaNegra = new Prenda(Categoria.ACCESORIO, Tipo.BUFANDA, new Tela(Material.LANA), frio, negro);
    Prenda anillo = new Prenda(Categoria.ACCESORIO, Tipo.ANILLO, new Tela(Material.SEDA), new RangoDeTemperaturas(), negro);

    julieta.agregarGuardarropa(guardaRopa1);
    julieta.agregarPrendas(guardaRopa1, camperaInvernal,musculosaRoja,sweaterVerde,bermudaAzul,jeanNegro,shortBlanco,zapatosDeCuero,ojotasBlancas,bufandaNegra,anillo);
  }

  @Test
  public void crearPrendaDesdeBorrador() throws Exception {
    Prenda prendaQueDeberiaTener = new Prenda(Categoria.PARTE_SUPERIOR,Tipo.REMERA,new Tela(Material.ALGODON),new RangoDeTemperaturas(20.0,100.0),negro);

    julieta.setearRangoTemperatura(calor);
    julieta.setearTela(new Tela(Material.ALGODON));
    julieta.setearColorPrimario(negro);
    julieta.setearTipo(Tipo.REMERA);
    julieta.setearCategoria(Categoria.PARTE_SUPERIOR);
    julieta.guardarPrenda(0);
    int posicionUltimaPrenda = julieta.getGuardarropa(0).getPrendas().size() - 1;
    Prenda prendaQueTiene = julieta.getGuardarropa(0).getPrendas().get(posicionUltimaPrenda);

    Assert.assertEquals(prendaQueDeberiaTener.getCategoria(), prendaQueTiene.getCategoria());
    Assert.assertEquals(prendaQueDeberiaTener.getTipo(), prendaQueTiene.getTipo());
    Assert.assertEquals(prendaQueDeberiaTener.getColorPrimario(), prendaQueTiene.getColorPrimario());
    Assert.assertEquals(prendaQueDeberiaTener.getTela().getMaterial(), prendaQueTiene.getTela().getMaterial());
    Assert.assertEquals(prendaQueDeberiaTener.getTela().getTrama(), prendaQueTiene.getTela().getTrama());
    Assert.assertEquals(prendaQueDeberiaTener.getTemperaturaDeUso().getTemperaturaMaxima(), prendaQueTiene.getTemperaturaDeUso().getTemperaturaMaxima());
    Assert.assertEquals(prendaQueDeberiaTener.getTemperaturaDeUso().getTemperaturaMinima(), prendaQueTiene.getTemperaturaDeUso().getTemperaturaMinima());
  }

  @Test
  public void recomendarAtuendoAIanEnCABA(){
    Recomendador recomendador = new Recomendador();
    Atuendo atuendo = recomendador.recomendar("Ciudad Autonoma de Buenos Aires", ian);
    System.out.println("1) La recomendacion para " + atuendo.getPrendaParteArriba().getCategoria()+ " un " + atuendo.getPrendaParteArriba().getTipo() + " de material "+ atuendo.getPrendaParteArriba().getTela().getMaterial() + " ,trama " +atuendo.getPrendaParteArriba().getTela().getTrama() + " y color " + atuendo.getPrendaParteArriba().getColorPrimario().descripcion() );
    System.out.println("2) La recomendacion para " + atuendo.getPrendaParteAbajo().getCategoria()+ " un " + atuendo.getPrendaParteAbajo().getTipo() + " de material "+ atuendo.getPrendaParteAbajo().getTela().getMaterial() + " ,trama " +atuendo.getPrendaParteAbajo().getTela().getTrama() + " y color " + atuendo.getPrendaParteAbajo().getColorPrimario().descripcion() );
    System.out.println("3) La recomendacion para " + atuendo.getCalzado().getCategoria()+ " un " + atuendo.getCalzado().getTipo() + " de material "+ atuendo.getCalzado().getTela().getMaterial() + " ,trama " +atuendo.getCalzado().getTela().getTrama() + " y color " + atuendo.getCalzado().getColorPrimario().descripcion() );
    System.out.println("4) La recomendacion para " + atuendo.getAccesorio().getCategoria()+ " un " + atuendo.getAccesorio().getTipo() + " de material "+ atuendo.getAccesorio().getTela().getMaterial() + " ,trama " +atuendo.getAccesorio().getTela().getTrama() + " y color " + atuendo.getAccesorio().getColorPrimario().descripcion() );

    Assert.assertTrue(atuendo.getPrendaParteArriba().esParaCalor());
  }

  @Test
  public void pedirRecomendacionMasDe10VecesElMismoDiaHabiendoPedidoMesesAntes(){
    Recomendador recomendador = new Recomendador();
    List<Atuendo> atuendos = new ArrayList<>();
    ian.getUsosServicioAccuWeather().add(LocalDate.of(2021,5,18));
    ian.getUsosServicioAccuWeather().add(LocalDate.of(2021,3,28));
    int i = 0;
    while (i<10){
      atuendos.add(recomendador.recomendar("CABA", julieta));
      i++;
    }
    Exception exception = assertThrows(RuntimeException.class,() -> recomendador.recomendar("Ciudad Autonoma de Buenos Aires", julieta));
    assertEquals("Has usado AccuWeather la cantidad de veces maxima para tu tipo de usuario.", exception.getMessage());
    assertEquals(10,atuendos.size());
  }

}