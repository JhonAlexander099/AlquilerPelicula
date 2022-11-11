package com.example.alquilerpelicula;

public class Pelicula {
    private String id;
    private String poster;
    private String titulo;
    private String genero;
    private String sinopsis;

    public Pelicula(String id, String poster, String titulo, String genero, String sinopsis) {
        this.id = id;
        this.poster = poster;
        this.titulo = titulo;
        this.genero = genero;
        this.sinopsis = sinopsis;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
