public enum Tipo {
  Categoria categoria;
  public Tipo(Categoria categoria) {
    this.categoria = categoria;
  }

  method getCategoria() {
    return this.categoria;
  }

  constant ZAPATO = new Tipo(CALZADO)
  constant OJOTA = new Tipo(CALZADO)
  constant BUFANDA = new Tipo(ACCESORIO)
  constant PULCERA = new Tipo(ACCESORIO)
  constant REMERA = new Tipo(PARTE_SUPERIOR)
  constant  MUSCULOSA = new Tipo(PARTE_SUPERIOR)
  constant SWEATER = new Tipo(PARTE_SUPERIOR)
  constant REMERA = new Tipo(PARTE_SUPERIOR)
  constant REMERA = new Tipo(PARTE_SUPERIOR)
  constant PANTALON = new Tipo(PARTE_INFERIOR)
  constant POLLERA = new Tipo(PARTE_INFERIOR)
  constant SHORT = new Tipo(PARTE_INFERIOR)


}

